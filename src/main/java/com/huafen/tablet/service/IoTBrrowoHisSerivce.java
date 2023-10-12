package com.huafen.tablet.service;

import java.util.List;

import com.huafen.tablet.model.his.IotBorrowHisDTO;
import com.huafen.tablet.model.param.BorrowHisParam;
import com.huafen.tablet.model.req.ReposeDTO;

public interface IoTBrrowoHisSerivce {

	
	ReposeDTO<List<IotBorrowHisDTO>> queryBorrowHisRetultInfo(BorrowHisParam borrowHisParam);
}
