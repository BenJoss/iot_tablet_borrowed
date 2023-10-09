package com.huafen.tablet.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.redisson.api.RAtomicLong;
import org.redisson.api.RBucket;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.huafen.tablet.config.RepCode;
import com.huafen.tablet.mapper.DeviceMapper;
import com.huafen.tablet.model.apply.IotBindTabAllDTO;
import com.huafen.tablet.model.apply.IotBindTabletDTO;
import com.huafen.tablet.model.apply.IotBorroFlowDTO;
import com.huafen.tablet.model.apply.IotOperLogDTO;
import com.huafen.tablet.model.apply.IotTablBorroHisDTO;
import com.huafen.tablet.model.iot.IotBorRetuDTO;
import com.huafen.tablet.model.iot.IotTabletDTO;
import com.huafen.tablet.model.param.TabletRevertParam;
import com.huafen.tablet.model.req.RepDTO;
import com.huafen.tablet.service.IoTDeviceBindSerivce;
import com.huafen.tablet.service.IoTDeviceBorrReturnRecodeSerivce;
import com.huafen.tablet.util.IoTDevUtil;
import com.huafen.tablet.util.RedisUtil;
import com.huafen.tablet.websocket.WSIotServer;

@Service("ioTDeviceBindSerivce")
public class IoTDeviceBindSerivceImpl implements IoTDeviceBindSerivce{

	
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(IoTDeviceBindSerivceImpl.class);
	@Resource
    private RedissonClient redissonClient;
	@Autowired
	private DeviceMapper deviceMapper;
	@Autowired
	private WSIotServer webSocket;
	
	@Autowired
	@Qualifier("ioTDeviceBorrReturnRecodeSerivce")
	private IoTDeviceBorrReturnRecodeSerivce ioTDeviceBorrReturnRecodeSerivce;
	
	@Override
	public RepDTO queryBindInfoSerivce(TabletRevertParam tabletRevertParam) {
		RepDTO  repDTO = new RepDTO();
		try {
			IotTablBorroHisDTO iotTablBorroHisDTO = deviceMapper.queryBindBorrowInfo(tabletRevertParam);
			 if (iotTablBorroHisDTO != null ) {
				 repDTO.setRepCode(RepCode.SUCCESS_CODE);
				 repDTO.setResult(iotTablBorroHisDTO);
			}else {
				repDTO.setRepCode(RepCode.ERROR_CODE);
				repDTO.setRepMsg("借还码不存在,请重新输入");
			}
		} catch (Exception e) {
			logger.error("异常", e.getMessage());
		}
		return repDTO;
	}

	@Override
	public RepDTO bindDeviceTopicRedisCahce(IotBorroFlowDTO iotBorroFlowDTO) {
		RepDTO  repDTO = new RepDTO();
		try {
			String topic = iotBorroFlowDTO.getTopic();
			String verifyCode = iotBorroFlowDTO.getVerifyCode();
			RBucket<String> bucket = redissonClient.getBucket(topic, StringCodec.INSTANCE);
		    bucket.trySet(verifyCode, RedisUtil.DEFAULT_EXPIRE_TIME_SECONDS, TimeUnit.SECONDS);
		} catch (Exception e) {
			logger.error("异常", e.getMessage());
		}
		return repDTO;
	}

    /**
     * 读取指定 key 下 member 的 score
     * 返回null 表示不存在
     */
    public Double getZSetMemberScore(String key, String member) {
        RScoredSortedSet<Object> scoredSortedSet = redissonClient.getScoredSortedSet(key);
        if (!scoredSortedSet.isExists()) {
            return null;
        }
        return scoredSortedSet.getScore(member);
    }
	 /**
     * 新增ZSet元素,存在则刷新
     *
     * @param refreshExpire 过期时间,不为null则重新赋值
     */
    public <T> void zscoreAdd(String key, double score, T member, Long refreshExpire) {
        RScoredSortedSet<Object> scoredSortedSet = redissonClient.getScoredSortedSet(key);
        if (null != refreshExpire) {
            scoredSortedSet.expire(RedisUtil.DEFAULT_EXPIRED,TimeUnit.SECONDS);
        }
        scoredSortedSet.add(score, member);
    }
    
    /**
     * 读取指定 key 下所有 member, 按照 score 升序(默认)
     */
    public Collection<Object> getZSetMembers(String key) {
        RScoredSortedSet<Object> scoredSortedSet = redissonClient.getScoredSortedSet(key);
        return scoredSortedSet.valueRange(0, RedisUtil.DEFAULT_END_INDEX);
    }
    
    private long getRedisAtomicLog() {
    	RAtomicLong  rAtomicLong  =	redissonClient.getAtomicLong(RedisUtil.ATOMIC_KEY);
    	rAtomicLong.expire(RedisUtil.ATOMIC_KEY_TIME_OUT, TimeUnit.SECONDS);
    	return rAtomicLong.incrementAndGet();
    }
    
    /**
     * 删除指定 ZSet 中的指定 memberName 元素
     */
    public void removeZSetMember(String key, String memberName) {
        RScoredSortedSet<Object> scoredSortedSet = redissonClient.getScoredSortedSet(key);
        if (!scoredSortedSet.isExists()) {
            return;
        }
        scoredSortedSet.remove(memberName);
    }
    
	@Override
	public RepDTO pushDeviceTopicByRedis(IotBorroFlowDTO iotBorroFlowDTO) {
		RepDTO  repDTO = new RepDTO();
		try {
			if (redissonClient.getBucket(IoTDevUtil.BORRO_TOKEN).isExists()) {
				Collection<Object> topicTokenList =  this.getZSetMembers(IoTDevUtil.BORRO_TOKEN);
				if (!topicTokenList.isEmpty()) {
					 for (Object  topicToken : topicTokenList) {
						 if ( topicToken instanceof String) {
							   String topic  =  (String) topicToken;
							   if (redissonClient.getBucket(topic).isExists()) {
									 RBucket<String> bucket = redissonClient.getBucket(topic, StringCodec.INSTANCE);
									 String verifyCode = bucket.get();
									 // 通过绑定主题推送的设备id从redis中取出缓存的平板信息
									 String tabletID = iotBorroFlowDTO.getTabletID();
									 //组装成以借还码为key,field 为设备id 平板信息为value zset结构存入redis中
									 Double  score = this.getZSetMemberScore(verifyCode,tabletID);
									 if (null == score) {
										 long  atomicScore = this.getRedisAtomicLog();
										 this.zscoreAdd(verifyCode, atomicScore, tabletID, RedisUtil.DEFAULT_EXPIRED); 
									}
									 //通过绑定主题取对应的借还码 通过码取扫描平板信息
									 Collection<Object> tabletList =  this.getZSetMembers(verifyCode);
									 //
									 if (!tabletList.isEmpty()) {
										 Map<String, Object> resultMap = new HashMap<String, Object>();
									     List<IotTabletDTO> list = new ArrayList<IotTabletDTO>();
										 for (Object id : tabletList) {
											 if (id instanceof String) {
												String deviceID  =  (String) id;
												RBucket<IotTabletDTO> buckeTablet = redissonClient.getBucket(deviceID);
												IotTabletDTO tablet = buckeTablet.get();
												if (null != tablet) {
													list.add(tablet);
												}
											}
										}
										resultMap.put(IoTDevUtil.IOTDEV_RESULT, list);
									    String message = JSON.toJSONString(resultMap);
									    webSocket.sendOneMessage(topic,message);
									}
								}
						}
					}
			    }
				
			}
					
			
		} catch (Exception e) {
			logger.error("异常", e.getMessage());
		}
		return repDTO;
	}

	@Override
	public void loadDeviceInfo() {
		try {
			List<IotTabletDTO> iotTabletDTO = deviceMapper.queryIotTabletInfo(null);
			for (IotTabletDTO item : iotTabletDTO) {
				  String tabletID = item.getTabletID();
				  RBucket<IotTabletDTO> bucket = redissonClient.getBucket(tabletID);
				  if (!redissonClient.getBucket(tabletID).isExists()) {
					   bucket.set(item,RedisUtil.DEFAULT_TABLET,TimeUnit.DAYS);
				  }
			}
		} catch (Exception e) {
			logger.error("异常", e.getMessage());
		}
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED ,rollbackFor = Exception.class)
	public RepDTO bindIotTablBorroInfo(IotBindTabAllDTO iotBindTabAllDTO) {
		RepDTO  repDTO = new RepDTO();
		try {
			List<IotBindTabletDTO> iotBindTabletList = iotBindTabAllDTO.getIotBindTabletList();
			if (!iotBindTabletList.isEmpty()) {
				 int bindNum = iotBindTabletList.size();
				 String verifyCode = iotBindTabAllDTO.getVerifyCode();
				 iotBindTabAllDTO.setBindNum(bindNum);
				 deviceMapper.updateIotTablBorro(iotBindTabAllDTO);
				 IotOperLogDTO iotOperLogDTO = new IotOperLogDTO();
				 iotOperLogDTO.setOperateId(iotBindTabAllDTO.getVerifyCode());
				 iotOperLogDTO.setOperateType("1");
				 iotOperLogDTO.setOperateCont("借用绑定"+bindNum+"平板");
				 deviceMapper.insertIotOperLog(iotOperLogDTO);
				//更新数据库及redis缓存平板状态为使用中
				 for (IotBindTabletDTO item : iotBindTabletList) {
					  item.setBorrowedStatus(IoTDevUtil.USEING_STATUS);
					  item.setVerifyCode(verifyCode);
					  deviceMapper.updateIotTabletInfo(item);
					  String tabletID = item.getTabletID();
					  RBucket<IotTabletDTO>   rBucket = redissonClient.getBucket(tabletID);
					  IotTabletDTO  cacheBindTabletD = rBucket.get();
					  cacheBindTabletD.setBorrowedStatus(IoTDevUtil.USEING_STATUS);
					  rBucket.set(cacheBindTabletD,RedisUtil.DEFAULT_TABLET,TimeUnit.DAYS);
				}
				// 录入绑定的平板记录
				 List<IotBorRetuDTO> iotBorRetuList = new ArrayList<IotBorRetuDTO>();
				 for (IotBindTabletDTO item : iotBindTabletList) {
					  IotBorRetuDTO iotBorRetuDTO = new IotBorRetuDTO();
					  iotBorRetuDTO.setVerifyCode(verifyCode);
					  iotBorRetuDTO.setTabletID(item.getTabletID());
					  iotBorRetuDTO.setBorrowedStatus(IoTDevUtil.USEING_STATUS);
					  iotBorRetuList.add(iotBorRetuDTO);
					  ioTDeviceBorrReturnRecodeSerivce.saveDeviceBorrReturnRecodes(iotBorRetuList);
				 }
				//删除借还码对应的zset缓存
				if (redissonClient.getBucket(verifyCode).isExists()) {
					redissonClient.getBucket(verifyCode).delete();
				}
				String topic = iotBindTabAllDTO.getTopic();
				// 删除 topic 对应 verifyCode
				if (redissonClient.getBucket(topic).isExists()) {
					redissonClient.getBucket(topic).delete();
				}
				//删除BORRO_TOKEN 推送topic
				this.removeZSetMember(IoTDevUtil.BORRO_TOKEN, topic);
				//
				repDTO.setRepCode(RepCode.SUCCESS_CODE);
				
			}
		} catch (Exception e) {
			logger.error("异常", e.getMessage());
			repDTO.setRepCode(RepCode.ERROR_CODE);
			repDTO.setRepMsg(e.getMessage());
		}
		return repDTO;
	}

	@Override
	public RepDTO checkBindInfoSerivce(TabletRevertParam tabletRevertParam) {
		RepDTO  repDTO = new RepDTO();
		try {
			IotTablBorroHisDTO iotTablBorroHisDTO = deviceMapper.queryBindBorrowInfo(tabletRevertParam);
			 if (iotTablBorroHisDTO != null ) {
				 repDTO.setRepCode(RepCode.SUCCESS_CODE);
				 repDTO.setResult(iotTablBorroHisDTO);
				 String token = IoTDevUtil.getToken();
				 repDTO.setRepMsg(token);
				 this.zscoreAdd(IoTDevUtil.BORRO_TOKEN, 0L, token, 1L);
			}else {
				repDTO.setRepCode(RepCode.ERROR_CODE);
				repDTO.setRepMsg("借还码不存在,请重新输入");
			}
		} catch (Exception e) {
			logger.error("异常", e.getMessage());
			repDTO.setRepCode(RepCode.ERROR_CODE);
			repDTO.setRepMsg(e.getMessage());
		}
		return repDTO;
	}
}
