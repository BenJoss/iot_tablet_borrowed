package com.huafen.tablet.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huafen.tablet.config.RepCode;
import com.huafen.tablet.mapper.DeviceBorrowMapper;
import com.huafen.tablet.mapper.DeviceMapper;
import com.huafen.tablet.model.apply.IotTablBorroCode;
import com.huafen.tablet.model.iot.IotBorRetuDTO;
import com.huafen.tablet.model.iot.IotTabletDTO;
import com.huafen.tablet.model.iot.PageBean;
import com.huafen.tablet.model.param.TabletRevertParam;
import com.huafen.tablet.model.req.RepDTO;
import com.huafen.tablet.service.IoTDeviceBorrReturnRecodeSerivce;
import com.huafen.tablet.util.IoTDevUtil;

@Service("ioTDeviceBorrReturnRecodeSerivce")
public class IoTDeviceBorrReturnRecodeSerivceImpl implements IoTDeviceBorrReturnRecodeSerivce {

	
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(IoTDeviceBorrReturnRecodeSerivceImpl.class);
	
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

}
