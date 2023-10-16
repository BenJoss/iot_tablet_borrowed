package com.huafen.tablet.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huafen.tablet.config.RepCode;
import com.huafen.tablet.mapper.DeviceMapper;
import com.huafen.tablet.model.apply.IotBrorroTabDTO;
import com.huafen.tablet.model.apply.IotOperLogDTO;
import com.huafen.tablet.model.apply.IotTablBorroDTO;
import com.huafen.tablet.model.apply.IotTablBorroHisDTO;
import com.huafen.tablet.model.apply.IotTableCancleDTO;
import com.huafen.tablet.model.config.RedisProperties;
import com.huafen.tablet.model.param.TabletApCoParam;
import com.huafen.tablet.model.param.TabletApplayParam;
import com.huafen.tablet.model.req.RepDTO;
import com.huafen.tablet.msg.DeviceException;
import com.huafen.tablet.service.IDistributedLock;
import com.huafen.tablet.service.IoTDeviBorroSerivce;
import com.huafen.tablet.util.IoTDevUtil;
@Service("ioTDeviBorroSerivce")
public class IoTDeviBorroSerivceImpl implements IoTDeviBorroSerivce {

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(IoTDeviBorroSerivceImpl.class);
	@Autowired
	private DeviceMapper deviceMapper;
	@Resource
	private IDistributedLock distributedLock;
	@Autowired
	private RedisProperties redisProperties;
	@Resource
    private RedissonClient redissonClient;
	
	private IotBrorroTabDTO getHalfOfDayTabletApplayNum(IotTablBorroDTO iotTablBorroDTO) {
		try {
			String startTime = iotTablBorroDTO.getStartTime();
			String dateStr = startTime.substring(0,startTime.indexOf(IoTDevUtil.DATE_UINX));
			TabletApplayParam tabletApplayParam = new TabletApplayParam();
			String alldayTime = dateStr+" "+ IoTDevUtil.ZERO_UINX;
			String mornTime = dateStr+" " + IoTDevUtil.MORN_UINX;
			String afterTime = dateStr+" " + IoTDevUtil.AFTER_UINX;
			tabletApplayParam.setAlldayTime(alldayTime);
			
		    switch (iotTablBorroDTO.getBorrowTime()) {
					case IoTDevUtil.MORN_DAY:
						tabletApplayParam.setMornTime(mornTime);
						break;
					case IoTDevUtil.AFTER_DAY:
						tabletApplayParam.setAfterTime(afterTime);
						break;				
					default:
						break;
			}
		 return	deviceMapper.getTabletApplayNum(tabletApplayParam);
		} catch (Exception e) {
			logger.error("异常", e.getMessage());
			DeviceException exception = new DeviceException();
			exception.setMsg("查询借用总数失败");
			throw exception;
		}
	}
	
	@Override
	public boolean applyHalfOfDayTabletSerivce(IotTablBorroDTO iotTablBorroDTO) {
		String key = redisProperties.getLockName();
		RLock lock = null;
		try {
			 lock = distributedLock.tryLock(key, redisProperties.getTryTime(), 0L,
					TimeUnit.MILLISECONDS, false);
			if (Objects.isNull(lock)) {
				logger.error(Thread.currentThread().getName() + " get lock key: " + key + " timeout!");
				DeviceException exception = new DeviceException();
				exception.setMsg(Thread.currentThread().getName() + " get lock key: " + key + " timeout!");
				throw exception;
			}
			// 业务代码
			int num = 1;
			while (num <= IoTDevUtil.TRY_NUM) {
				// 判断申请借出数量与差值大小
				if (this.judgeHalfOfDayTabletNum(iotTablBorroDTO)) {
					// 生成借还码
					this.applyTabletVerifyCode(iotTablBorroDTO);
					// 往借出记录表插入一条记录
					this.saveApplyTabletInfoSerivce(iotTablBorroDTO);
					return true;
				}else {
					num ++;
				}
			}
			return false;
		} catch (Exception e) {
			logger.error("异常", e.getMessage());
			DeviceException exception = new DeviceException();
			exception.setMsg(e.getMessage());
			throw exception;
		}finally {
			if (null != lock) {
				distributedLock.unLock(lock);
			}
		}
		
	}

	
	private void applyTabletVerifyCode(IotTablBorroDTO iotTablBorroDTO) {
		try {
			 String verifyCode = this.prodVerifyCode();
			 iotTablBorroDTO.setVerifyCode(verifyCode);
		} catch (Exception e) {
			logger.error("异常", e.getMessage());
			DeviceException exception = new DeviceException();
			exception.setMsg(e.getMessage());
			throw exception;
		}
	}
	private String  prodVerifyCode() {
		StringBuilder verifyCode = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			 double randPosition = Math.floor(Math.random() * IoTDevUtil.arr.length);
			 if(i==0){
				 if (randPosition==0){
					  randPosition+=1;
					}
			 }
			String code = String.valueOf(randPosition);
			code = code.substring(0, code.indexOf("."));
			verifyCode.append(IoTDevUtil.arr[Integer.parseInt(code)]);
		}
		return verifyCode.toString();
	}
	
	private boolean judgeHalfOfDayTabletNum(IotTablBorroDTO iotTablBorroDTO) {
		try {
			int borrowNum = iotTablBorroDTO.getBorrowNum();
			// 缓存数量
		    RBucket<Integer> bucketSum = redissonClient.getBucket(IoTDevUtil.TABLET_SUM);
			int TABLET_SUM =(bucketSum == null ? 0 : bucketSum.get());
			IotBrorroTabDTO iotBrorroTabDTO = this.getHalfOfDayTabletApplayNum(iotTablBorroDTO);
			if (iotBrorroTabDTO == null) {
				if (borrowNum <= TABLET_SUM) {
					return true;
				}else {
					return false;
				}				
			}
			Integer allDayNum = iotBrorroTabDTO.getAllDayNum() == null ? 0:iotBrorroTabDTO.getAllDayNum();
			Integer mornDayNum = iotBrorroTabDTO.getMornDayNum()== null ? 0:iotBrorroTabDTO.getMornDayNum();
			Integer afterDayNum = iotBrorroTabDTO.getAfterDayNum()== null ? 0:iotBrorroTabDTO.getAfterDayNum();
			int overNum = 0; 
			switch (iotTablBorroDTO.getBorrowTime()) {
					case IoTDevUtil.MORN_DAY:						
						overNum = TABLET_SUM - (allDayNum + mornDayNum) ;
						break;
					case IoTDevUtil.AFTER_DAY:
						overNum = TABLET_SUM - (allDayNum + afterDayNum) ;
						break;				
					default:
						break;
			}
			if (borrowNum <= overNum) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			logger.error("异常", e.getMessage());
			DeviceException exception = new DeviceException();
			exception.setMsg(e.getMessage());
			throw exception;
		}
	}
	
	private IotBrorroTabDTO getAllOfDayTabletApplayNum(IotTablBorroDTO iotTablBorroDTO) {
		try {
			String startTime = iotTablBorroDTO.getStartTime();
			String dateStr = startTime.substring(0,startTime.indexOf(IoTDevUtil.DATE_UINX));
			// 全天
			String alldayTime = dateStr+" "+ IoTDevUtil.ZERO_UINX;
			// 上午
			String mornTime = dateStr+" " + IoTDevUtil.MORN_UINX;
			// 下午
			String afterTime = dateStr+" " + IoTDevUtil.AFTER_UINX;
			TabletApplayParam tabletApplayParam = new TabletApplayParam();
			tabletApplayParam.setAlldayTime(alldayTime);
			tabletApplayParam.setMornTime(mornTime);
			tabletApplayParam.setAfterTime(afterTime);
		 return	deviceMapper.getTabletApplayNum(tabletApplayParam);
		} catch (Exception e) {
			logger.error("异常", e.getMessage());
			DeviceException exception = new DeviceException();
			exception.setMsg("查询借用总数失败");
			throw exception;
		}
	}
	
	private boolean judgeALLOfDayTabletNum(IotTablBorroDTO iotTablBorroDTO) {
		try {
			int borrowNum = iotTablBorroDTO.getBorrowNum();
			// 缓存数量
		    RBucket<Integer> bucketSum = redissonClient.getBucket(IoTDevUtil.TABLET_SUM);
			int TABLET_SUM =(bucketSum == null ? 0 : bucketSum.get());
			IotBrorroTabDTO iotBrorroTabDTO = this.getAllOfDayTabletApplayNum(iotTablBorroDTO);
			if (iotBrorroTabDTO == null) {
				if (borrowNum <= TABLET_SUM) {
					return true;
				}else {
					return false;
				}
				
			}
			Integer allDayNum = iotBrorroTabDTO.getAllDayNum() == null ? 0:iotBrorroTabDTO.getAllDayNum();
			Integer mornDayNum = iotBrorroTabDTO.getMornDayNum()== null ? 0:iotBrorroTabDTO.getMornDayNum();
			Integer afterDayNum = iotBrorroTabDTO.getAfterDayNum()== null ? 0:iotBrorroTabDTO.getAfterDayNum();
			int max = Math.max(mornDayNum, afterDayNum);
			int overNum = TABLET_SUM - (allDayNum + max);	
			if (borrowNum <= overNum) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			logger.error("异常", e.getMessage());
			DeviceException exception = new DeviceException();
			exception.setMsg(e.getMessage());
			throw exception;
		}
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED ,rollbackFor = Exception.class)
	public void saveApplyTabletInfoSerivce(IotTablBorroDTO iotTablBorroDTO) {
		 try {
			 iotTablBorroDTO.setBorrowedStatus(IoTDevUtil.TO_BE_BORROWED);
			 deviceMapper.insertIotTablBorro(iotTablBorroDTO);
			 IotOperLogDTO iotOperLogDTO = new IotOperLogDTO();
			 iotOperLogDTO.setOperateId(iotTablBorroDTO.getVerifyCode());
			 iotOperLogDTO.setOperateType("1");
			 iotOperLogDTO.setOperateCont("借用申请"+iotTablBorroDTO.getBorrowNum()+"平板");
			 deviceMapper.insertIotOperLog(iotOperLogDTO);
		} catch (Exception e) {
			logger.error("异常", e.getMessage());
			DeviceException exception = new DeviceException();
			exception.setMsg(e.getMessage());
			throw exception;
		}
		
	}

	@Override
	public boolean applyALLOfDayTabletSerivce(IotTablBorroDTO iotTablBorroDTO) {
		String key = redisProperties.getLockName();
		RLock lock = null;
		try {
			 lock = distributedLock.tryLock(key, redisProperties.getTryTime(), 0L,
						TimeUnit.MILLISECONDS, false);
			if (Objects.isNull(lock)) {
				logger.error(Thread.currentThread().getName() + " get lock key: " + key + " timeout!");
				DeviceException exception = new DeviceException();
				exception.setMsg(Thread.currentThread().getName() + " get lock key: " + key + " timeout!");
				throw exception;
			}
			// 业务代码
			int num = 1;
			while (num <= IoTDevUtil.TRY_NUM) {
				// 判断申请借出数量与差值大小
				if (this.judgeALLOfDayTabletNum(iotTablBorroDTO)) {
					// 生成借还码
					this.applyTabletVerifyCode(iotTablBorroDTO);
					// 往借出记录表插入一条记录
					this.saveApplyTabletInfoSerivce(iotTablBorroDTO);
					return true;
				}else {
					num ++;
				}
			}
			return false;
		} catch (Exception e) {
			logger.error("异常", e.getMessage());
			DeviceException exception = new DeviceException();
			exception.setMsg(e.getMessage());
			throw exception;
		}finally {
			if (null != lock) {
				distributedLock.unLock(lock);
			}
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED ,rollbackFor = Exception.class)
	public boolean cancelApplyTabletInfo(IotTableCancleDTO iotTableCancleDTO) {
		try {
			 int bindNum = iotTableCancleDTO.getBorrowNum();
			 String verifyCode = iotTableCancleDTO.getVerifyCode();
			 iotTableCancleDTO.setBorrowNum(0);
			 deviceMapper.updateCancelIotTablBorro(iotTableCancleDTO);
			 IotOperLogDTO iotOperLogDTO = new IotOperLogDTO();
			 iotOperLogDTO.setOperateId(verifyCode);
			 iotOperLogDTO.setOperateType("1");
			 iotOperLogDTO.setOperateCont("取消申请的"+bindNum+"平板");
			 deviceMapper.insertIotOperLog(iotOperLogDTO);
			return true;
		} catch (Exception e) {
			logger.error("异常", e.getMessage());
			DeviceException exception = new DeviceException();
			exception.setMsg(e.getMessage());
			throw exception;
		}
	}

	@Override
	public RepDTO queryBorrowVerifyCode(TabletApCoParam tabletApCoParam) {
		RepDTO  repDTO = new RepDTO();
		try {
			 List<IotTablBorroHisDTO> iotTablBorroHisList = deviceMapper.queryBorrowVerifyCode(tabletApCoParam);
			 repDTO.setRepCode(RepCode.SUCCESS_CODE);
			 repDTO.setResult(iotTablBorroHisList);
		} catch (Exception e) {
			logger.error("异常", e.getMessage());
			repDTO.setRepCode(RepCode.ERROR_CODE);
			repDTO.setRepMsg(e.getMessage());
		}
		return repDTO;
	}

	
	

}
