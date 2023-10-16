package com.huafen.tablet.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huafen.tablet.config.RepCode;
import com.huafen.tablet.mapper.DeviceBorrowMapper;
import com.huafen.tablet.mapper.DeviceMapper;
import com.huafen.tablet.model.apply.IotTablBorroCode;
import com.huafen.tablet.model.his.IotBorrowHisDTO;
import com.huafen.tablet.model.his.IotCurHisAllDTO;
import com.huafen.tablet.model.his.IotCurentHisDTO;
import com.huafen.tablet.model.iot.IotBorRetuDTO;
import com.huafen.tablet.model.iot.IotTabletDTO;
import com.huafen.tablet.model.iot.PageBean;
import com.huafen.tablet.model.param.IotCurParam;
import com.huafen.tablet.model.param.TabletRevertParam;
import com.huafen.tablet.model.req.RepDTO;
import com.huafen.tablet.model.req.ReposeDTO;
import com.huafen.tablet.service.IoTDeviceBorrReturnRecodeSerivce;
import com.huafen.tablet.util.IoTDevUtil;

@Service("ioTDeviceBorrReturnRecodeSerivce")
public class IoTDeviceBorrReturnRecodeSerivceImpl implements IoTDeviceBorrReturnRecodeSerivce {

	
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(IoTDeviceBorrReturnRecodeSerivceImpl.class);
	
	@Resource
    private RedissonClient redissonClient;
	
	@Autowired
	private DeviceBorrowMapper deviceBorrowMapper;
	
	@Autowired
	private DeviceMapper deviceMapper;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED ,rollbackFor = Exception.class)
	public RepDTO saveDeviceBorrReturnRecodes(List<IotBorRetuDTO> iotBorRetuList) {
		RepDTO  repDTO = new RepDTO();
		try {
			if (iotBorRetuList != null && !iotBorRetuList.isEmpty()) {
			     for (IotBorRetuDTO item: iotBorRetuList) {
			    	  Integer count = deviceBorrowMapper.countBorroReturnOperNum(item);
			    	 if (count == null || count == 0) {
			    		 deviceBorrowMapper.insertBorroReturnOperLog(item);
					}else {
						IotTabletDTO iotTabletDTO = new IotTabletDTO();
						iotTabletDTO.setBorrowedStatus(item.getBorrowedStatus());
						iotTabletDTO.setTabletID(item.getTabletID());
						iotTabletDTO.setVerifyCode(item.getVerifyCode());
						deviceBorrowMapper.updateBorroReturnOperLog(iotTabletDTO);
					}
				}
			}
			repDTO.setRepCode(RepCode.SUCCESS_CODE);
		} catch (Exception e) {
			logger.error("异常", e.getMessage());
			repDTO.setRepCode(RepCode.ERROR_CODE);
			repDTO.setRepMsg(e.getMessage());
		}
		return repDTO;
	}

	@Override
	public RepDTO queryBorrowRetultInfoSerivce(TabletRevertParam tabletRevertParam) {
		RepDTO  repDTO = new RepDTO();
		try {
			List<IotTabletDTO>  iotBorRetuList = deviceBorrowMapper.queryBorrowRetultInfo(tabletRevertParam);
			repDTO.setRepCode(RepCode.SUCCESS_CODE);
			repDTO.setResult(iotBorRetuList);
		} catch (Exception e) {
			logger.error("异常", e.getMessage());
			repDTO.setRepCode(RepCode.ERROR_CODE);
			repDTO.setRepMsg(e.getMessage());
		}
		return repDTO;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED ,rollbackFor = Exception.class)
	public RepDTO updateBorroReturnOperLog(List<IotTabletDTO> iotTabletList) {
		RepDTO  repDTO = new RepDTO();
		try {
			for(IotTabletDTO item :  iotTabletList) {
				deviceBorrowMapper.updateBorroReturnOperLog(item);
			}
			repDTO.setRepCode(RepCode.SUCCESS_CODE);
		} catch (Exception e) {
			logger.error("异常", e.getMessage());
			repDTO.setRepCode(RepCode.ERROR_CODE);
			repDTO.setRepMsg(e.getMessage());
		}
		return repDTO;
	}

	@Override
	public PageBean<IotTablBorroCode> queryCodeInfoByPage(PageBean<IotTablBorroCode> pageBean) {
		try {
			List<String> borrowedStatusList = new ArrayList<String>();
			borrowedStatusList.add(IoTDevUtil.TO_BE_BORROWED);
			borrowedStatusList.add(IoTDevUtil.IN_BORROWED);
			borrowedStatusList.add(IoTDevUtil.EXCEPTION_BORROWED);
			
				
			int totalRecord = 0;
			if (pageBean.getTotalRecord() == 0) {
				totalRecord = deviceBorrowMapper.countCodeInfoPage(pageBean); 
			}else {
				totalRecord = pageBean.getTotalRecord();
			}
			PageBean<IotTablBorroCode> pageParam = new PageBean<>
			(pageBean.getPageNum(),pageBean.getPageSize(),totalRecord);
			pageParam.setBorrowedStatusList(borrowedStatusList);
			pageParam.setUserID(pageBean.getUserID());
			List<IotTablBorroCode>  dataList = deviceBorrowMapper.queryCodeInfoPageList(pageParam);
			pageParam.setTotalRecord(totalRecord);
			pageParam.setData(dataList);
			return pageParam;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return pageBean;
	}

	@Override
	public Map<String, Object> queryBorrowinfoSerivce(TabletRevertParam tabletRevertParam) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			//已还
			Integer count = deviceMapper.countReturnNum(tabletRevertParam);
			if (count == null) {
				count = 0;
			}
			resultMap.put(IoTDevUtil.RETURN_STATE_NUM, count);
			tabletRevertParam.setBorrowedStatus(null);
			List<IotTabletDTO>  iotBorRetuList = deviceBorrowMapper.queryBorrowRetultInfo(tabletRevertParam);
			resultMap.put(IoTDevUtil.IOTDEV_RESULT, iotBorRetuList);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return resultMap;
	}

	@Override
	public PageBean<IotBorrowHisDTO> queryBorrowInfoPageList(PageBean<IotBorrowHisDTO> pageBean) {
			try {
				int totalRecord = 0;
				if (pageBean.getTotalRecord() == 0) {
					totalRecord = deviceBorrowMapper.countBorrowByPage(pageBean); 
				}else {
					totalRecord = pageBean.getTotalRecord();
				}
				PageBean<IotBorrowHisDTO> pageParam = new PageBean<>(pageBean.getPageNum(),pageBean.getPageSize(),totalRecord);
				pageParam.setBorrowedStatus(pageBean.getBorrowedStatus());
				pageParam.setUserName(pageBean.getUserName());
				pageParam.setStartTime(pageBean.getStartTime());
				pageParam.setEndTime(pageBean.getEndTime());
				List<IotBorrowHisDTO>  dataList = deviceBorrowMapper.queryBorrowInfoPageList(pageParam);
				pageParam.setTotalRecord(totalRecord);
				pageParam.setData(dataList);
				return pageParam;
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			return pageBean;
	}

	@Override
	public ReposeDTO<IotCurHisAllDTO> queryCurentHisRetulInfoSerivce(IotCurParam iotCurParam) {
		ReposeDTO<IotCurHisAllDTO>  reposeDTO = new ReposeDTO<IotCurHisAllDTO>();
	    try {
	    	IotCurHisAllDTO iotCurHisAllDTO = new IotCurHisAllDTO();
	    	IotCurHisAllDTO  iotCurHisAll = deviceBorrowMapper.countCurentHisRetulInfo(iotCurParam);
			// 缓存数量
		    RBucket<Integer> bucketSum = redissonClient.getBucket(IoTDevUtil.TABLET_SUM);
			int TABLET_SUM =(bucketSum == null ? 0 : bucketSum.get());
			if (TABLET_SUM > 0 ) {
				int  alldayHisNum = iotCurHisAll.getAlldayNum();
				int  mornHisNum = iotCurHisAll.getMornNum();
				int  afterHisNum = iotCurHisAll.getAfterNum();
				int max = Math.max(mornHisNum, afterHisNum);
				int overNum = TABLET_SUM - (alldayHisNum + max);
				iotCurHisAllDTO.setAlldayNum(overNum >= 0 ? overNum:0);
				//
				int overNextNum = TABLET_SUM - (alldayHisNum + mornHisNum) ;
		    	iotCurHisAllDTO.setMornNum(overNextNum >= 0 ? overNextNum:0);
		    	iotCurHisAllDTO.setAfterNum(overNextNum >= 0 ? overNextNum:0);
			}
	    	List<IotCurentHisDTO>  dataList = deviceBorrowMapper.queryCurentHisRetulInfo(iotCurParam);
	    	iotCurHisAllDTO.setData(dataList);
	    	reposeDTO.setResult(iotCurHisAllDTO);
	    	reposeDTO.setRepCode(RepCode.SUCCESS_CODE);
		} catch (Exception e) {
			reposeDTO.setRepCode(RepCode.ERROR_CODE);
			logger.error(e.getMessage());
		}
		return reposeDTO;
	}

}
