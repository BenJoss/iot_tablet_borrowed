package com.huafen.tablet.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huafen.tablet.config.RepCode;
import com.huafen.tablet.mapper.DeviceBorrowMapper;
import com.huafen.tablet.model.his.IotBorrowHisDTO;
import com.huafen.tablet.model.param.BorrowHisParam;
import com.huafen.tablet.model.req.ReposeDTO;
import com.huafen.tablet.service.IoTBrrowoHisSerivce;

@Service("ioTBrrowoHisSerivce")
public class IoTBrrowoHisSerivceImpl implements IoTBrrowoHisSerivce{

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(IoTBrrowoHisSerivceImpl.class);
	@Autowired
	private DeviceBorrowMapper deviceBorrowMapper;
	
	
	@Override
	public ReposeDTO<List<IotBorrowHisDTO>> queryBorrowHisRetultInfo(BorrowHisParam borrowHisParam) {
		ReposeDTO<List<IotBorrowHisDTO>>  reposeDTO = new ReposeDTO<List<IotBorrowHisDTO>>();
		try {
			List<IotBorrowHisDTO>  iotBorrowHisList =	deviceBorrowMapper.queryBorrowHisRetultInfo(borrowHisParam);
			reposeDTO.setRepCode(RepCode.SUCCESS_CODE);
			reposeDTO.setResult(iotBorrowHisList);
		} catch (Exception e) {
			logger.error("异常", e.getMessage());
			reposeDTO.setRepCode(RepCode.ERROR_CODE);
			reposeDTO.setRepMsg(e.getMessage());
		}
		return reposeDTO;
	}
	
	
	
}
