package com.huafen.tablet.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
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
import com.huafen.tablet.model.apply.IotDeviReturnDTO;
import com.huafen.tablet.model.apply.IotOperLogDTO;
import com.huafen.tablet.model.apply.IotTablBorroHisDTO;
import com.huafen.tablet.model.iot.IotTabletDTO;
import com.huafen.tablet.model.param.TabletMageParam;
import com.huafen.tablet.model.param.TabletRevertParam;
import com.huafen.tablet.model.req.RepDTO;
import com.huafen.tablet.service.IoTDeviceBorrReturnRecodeSerivce;
import com.huafen.tablet.service.IoTDeviceReturnSerivce;
import com.huafen.tablet.util.IoTDevUtil;
import com.huafen.tablet.util.RedisUtil;
import com.huafen.tablet.websocket.WSIotServer;

@Service("ioTDeviceReturnSerivce")
public class IoTDeviceReturnSerivceImpl implements IoTDeviceReturnSerivce{
	
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(IoTDeviceReturnSerivceImpl.class);
	@Resource
    private RedissonClient redissonClient;
	@Autowired
	private DeviceMapper deviceMapper;
	@Autowired
	private WSIotServer webSocket;
	@Autowired
	@Qualifier("ioTDeviceBorrReturnRecodeSerivce")
	private IoTDeviceBorrReturnRecodeSerivce ioTDeviceBorrReturnRecodeSerivce;
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
			if (redissonClient.getBucket(IoTDevUtil.RETURN_TOKEN).isExists()) {
				Collection<Object> topicTokenList =  this.getZSetMembers(IoTDevUtil.RETURN_TOKEN);
				if (!topicTokenList.isEmpty()) {
					 for (Object  topicToken : topicTokenList) {
						 if ( topicToken instanceof String) {
							   String topic  =  (String) topicToken;
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
		} catch (Exception e) {
			logger.error("异常"+ e.getMessage());
		}
		return repDTO;
	
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
    
    
	@Override
	public RepDTO bindDeviceTopicRedisCahce(IotBorroFlowDTO iotBorroFlowDTO) {
		RepDTO  repDTO = new RepDTO();
		try {
			String topic = iotBorroFlowDTO.getTopic();
			String verifyCode = iotBorroFlowDTO.getVerifyCode();
			if (!redissonClient.getBucket(topic).isExists()) {
				 RBucket<String> bucket = redissonClient.getBucket(topic, StringCodec.INSTANCE);
				 bucket.trySet(verifyCode, RedisUtil.DEFAULT_EXPIRE_TIME_SECONDS, TimeUnit.SECONDS);
			}
		} catch (Exception e) {
			logger.error("异常"+ e.getMessage());
		}
		return repDTO;
	}

	@Override
	public RepDTO queryReturnInfoSerivce(TabletRevertParam tabletRevertParam) {
		RepDTO  repDTO = new RepDTO();
		try {
			IotTablBorroHisDTO iotTablBorroHisDTO = deviceMapper.queryBindBorrowInfo(tabletRevertParam);
			 if (iotTablBorroHisDTO != null ) {
				 if (iotTablBorroHisDTO.getBorrowEndTime() == null) {
					 iotTablBorroHisDTO.setBorrowEndTime("");
				}
				 if (iotTablBorroHisDTO.getBorrowStartTime() == null) {
					 iotTablBorroHisDTO.setBorrowStartTime("");
				}
				 if (iotTablBorroHisDTO.getMeetEndTime() == null) {
					 iotTablBorroHisDTO.setMeetEndTime("");
				}
				 if (iotTablBorroHisDTO.getMeetStartTime() == null) {
					 iotTablBorroHisDTO.setMeetStartTime("");
				}
				 if (iotTablBorroHisDTO.getMeetName() == null) {
					 iotTablBorroHisDTO.setMeetName("");
				}
				 if (iotTablBorroHisDTO.getRoomName() == null) {
					 iotTablBorroHisDTO.setRoomName("");
				}
				 if (iotTablBorroHisDTO.getUserName() == null) {
					 iotTablBorroHisDTO.setUserName("");
				}
				 repDTO.setRepCode(RepCode.SUCCESS_CODE);
				 repDTO.setResult(iotTablBorroHisDTO);
			}else {
				repDTO.setRepCode(RepCode.ERROR_CODE);
				repDTO.setRepMsg("借还码不存在,请重新输入");
			}
		} catch (Exception e) {
			logger.error("异常"+ e.getMessage());
		}
		return repDTO;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED ,rollbackFor = Exception.class)
	public RepDTO returnIotTablBorroInfo(IotDeviReturnDTO iotDeviReturnDTO) {
		RepDTO  repDTO = new RepDTO();
		try {
			List<IotBindTabletDTO> iotBindTabletList = iotDeviReturnDTO.getIotBindTabletList();
			if (!iotBindTabletList.isEmpty()) {
				Iterator<IotBindTabletDTO>  iterator = iotBindTabletList.iterator();
				while (iterator.hasNext()) {
					  IotBindTabletDTO item = iterator.next();
					  String borrowedStatus = item.getBorrowedStatus();
					  if (IoTDevUtil.RETURN_STATE.equals(borrowedStatus)||IoTDevUtil.IDLE_STATE.equals(borrowedStatus)) {
						  iterator.remove();
					}
				}
			}
			if (!iotBindTabletList.isEmpty()) {
				 // 更新借用状态
				 String verifyCode = iotDeviReturnDTO.getVerifyCode();
				 int returnNum = iotBindTabletList.size();
				 int bindNum = iotDeviReturnDTO.getBorrowNum();
				 IotBindTabAllDTO iotBindTabAllDTO = new IotBindTabAllDTO();
				 iotBindTabAllDTO.setVerifyCode(verifyCode);
				 // 新增归还记录
				 TabletRevertParam tabletRevertParam = new TabletRevertParam();
				 tabletRevertParam.setVerifyCode(verifyCode);
				 Integer count = deviceMapper.countReturnNum(tabletRevertParam);
				 if (count == null || count == 0) {
					 iotDeviReturnDTO.setReturnNum(returnNum);
					 deviceMapper.insertDeviceReturn(iotDeviReturnDTO);
				 }else {
					 returnNum = returnNum + count;
					 iotDeviReturnDTO.setReturnNum(returnNum);
					 deviceMapper.updateDeviceReturnTable(iotDeviReturnDTO);
				}
				 if (returnNum == bindNum) {
					 iotBindTabAllDTO.setBorrowedStatus(IoTDevUtil.FINISH_BORROWED);
				 }else if (returnNum != bindNum) {
					 iotBindTabAllDTO.setBorrowedStatus(IoTDevUtil.EXCEPTION_BORROWED);
				 }
				 deviceMapper.updateIotTablBorro(iotBindTabAllDTO);
				 // 新增日志
				 IotOperLogDTO iotOperLogDTO = new IotOperLogDTO();
				 iotOperLogDTO.setOperateId(iotBindTabAllDTO.getVerifyCode());
				 iotOperLogDTO.setOperateType("1");
				 iotOperLogDTO.setOperateCont("归还"+returnNum+"平板");
				 deviceMapper.insertIotOperLog(iotOperLogDTO);
				//更新数据库及redis缓存平板状态为空闲中
				 for (IotBindTabletDTO item : iotBindTabletList) {
					  item.setBorrowedStatus(IoTDevUtil.IDLE_STATE);
					  deviceMapper.updateIotTabletInfo(item);
					  deviceMapper.setIotTabletCodeNull(item);
					  String tabletID = item.getTabletID();
					  RBucket<IotTabletDTO>   rBucket = redissonClient.getBucket(tabletID);
					  IotTabletDTO  cacheBindTabletD = rBucket.get();
					  if(cacheBindTabletD != null) {
						  cacheBindTabletD.setBorrowedStatus(IoTDevUtil.IDLE_STATE);
						  rBucket.set(cacheBindTabletD,RedisUtil.DEFAULT_TABLET,TimeUnit.DAYS);
					  }
				}
				//更新借用操作记录
				 List<IotTabletDTO> iotTabletList = new ArrayList<IotTabletDTO>();
				 for (IotBindTabletDTO item : iotBindTabletList) {
					  IotTabletDTO  iotTablet =  new IotTabletDTO();
					  iotTablet.setBorrowedStatus(IoTDevUtil.RETURN_STATE);
					  iotTablet.setVerifyCode(verifyCode);
					  iotTablet.setTabletID(item.getTabletID());
					  iotTabletList.add(iotTablet);
				 }
				 ioTDeviceBorrReturnRecodeSerivce.updateBorroReturnOperLog(iotTabletList);
				//删除借还码对应的zset缓存
				if (redissonClient.getBucket(verifyCode).isExists()) {
					redissonClient.getBucket(verifyCode).delete();
				}
				String topic = iotDeviReturnDTO.getTopic();
				if(topic != null) {
					// 删除 topic 对应 verifyCode
					if (redissonClient.getBucket(topic).isExists()) {
						redissonClient.getBucket(topic).delete();
					}
					// 删除 RETURN_TOKEN 推送topic
					this.removeZSetMember(IoTDevUtil.RETURN_TOKEN, topic);
					//
				}
				repDTO.setRepCode(RepCode.SUCCESS_CODE);
			}
		} catch (Exception e) {
			logger.error("异常"+ e.getMessage());
			repDTO.setRepCode(RepCode.ERROR_CODE);
			repDTO.setRepMsg(e.getMessage());
		}
		return repDTO;
	}

	@Override
	public RepDTO queryTabletInfoSerivce(TabletMageParam tabletRevertParam) {
		RepDTO  repDTO = new RepDTO();
		try {
			List<IotTabletDTO> iotTabletList = deviceMapper.queryIotEditTabletInfo( tabletRevertParam);
			//
			repDTO.setResult(iotTabletList);
			repDTO.setRepCode(RepCode.SUCCESS_CODE);
		} catch (Exception e) {
			logger.error("异常"+ e.getMessage());
			repDTO.setRepCode(RepCode.ERROR_CODE);
			repDTO.setRepMsg(e.getMessage());
		}
		return repDTO;
	}

	@Override
	public RepDTO checkReturnInfoSerivce(TabletRevertParam tabletRevertParam) {
		RepDTO  repDTO = new RepDTO();
		try {
			List<String> borrowedStatusList = new ArrayList<String>();
			borrowedStatusList.add(IoTDevUtil.IN_BORROWED);
			borrowedStatusList.add(IoTDevUtil.EXCEPTION_BORROWED);
			tabletRevertParam.setBorrowedStatusList(borrowedStatusList);
			IotTablBorroHisDTO iotTablBorroHisDTO = deviceMapper.queryBindBorrowInfo(tabletRevertParam);
			 if (iotTablBorroHisDTO != null ) {
				 repDTO.setRepCode(RepCode.SUCCESS_CODE);
				 repDTO.setResult(iotTablBorroHisDTO);
				 String token = IoTDevUtil.getToken();
				 repDTO.setRepMsg(token);
				 this.zscoreAdd(IoTDevUtil.RETURN_TOKEN, 0L, token, 1L);
				repDTO.setRepCode(RepCode.SUCCESS_CODE);
			}else {
				repDTO.setRepCode(RepCode.ERROR_CODE);
				repDTO.setRepMsg("借还码不存在或已失效,请重新输入");
			}
		} catch (Exception e) {
			logger.error("异常"+ e.getMessage());
			repDTO.setRepCode(RepCode.ERROR_CODE);
			repDTO.setRepMsg(e.getMessage());
		}
		return repDTO;
	}

	@Override
	public RepDTO resetDeviceBindInfo(IotBorroFlowDTO iotBorroFlowDTO) {
		RepDTO  repDTO = new RepDTO();
	    try {
	    	String verifyCode = iotBorroFlowDTO.getVerifyCode();
	    	//删除借还码对应的zset缓存
	    	if (verifyCode != null ) {
	    		if (redissonClient.getBucket(verifyCode) != null && redissonClient.getBucket(verifyCode).isExists()) {
					redissonClient.getBucket(verifyCode).delete();
				}
			}
			String topic = iotBorroFlowDTO.getTopic();
			if(topic != null) {
				// 删除 topic 对应 verifyCode
				if (redissonClient.getBucket(topic) != null && redissonClient.getBucket(topic).isExists()) {
					redissonClient.getBucket(topic).delete();
				}
				// 删除 RETURN_TOKEN 推送topic
				this.removeZSetMember(IoTDevUtil.RETURN_TOKEN, topic);
				//
				webSocket.removeWsMsg(topic);
			}
			repDTO.setRepCode(RepCode.SUCCESS_CODE);
		} catch (Exception e) {
			logger.error("异常"+ e.getMessage());
			repDTO.setRepCode(RepCode.ERROR_CODE);
			repDTO.setRepMsg(e.getMessage());
		}
		return repDTO;
	}

}
