package com.huafen.tablet.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huafen.tablet.config.RepCode;
import com.huafen.tablet.mapper.DeviceBorrowMapper;
import com.huafen.tablet.model.iot.IotBorRetuDTO;
import com.huafen.tablet.model.iot.IotTabletDTO;
import com.huafen.tablet.model.param.TabletRevertParam;
import com.huafen.tablet.model.req.RepDTO;
import com.huafen.tablet.service.IoTDeviceBorrReturnRecodeSerivce;

@Service("ioTDeviceBorrReturnRecodeSerivce")
public class IoTDeviceBorrReturnRecodeSerivceImpl implements IoTDeviceBorrReturnRecodeSerivce {

	
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(IoTDeviceBorrReturnRecodeSerivceImpl.class);
	
	@Autowired
	private DeviceBorrowMapper deviceBorrowMapper;
	
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

}
