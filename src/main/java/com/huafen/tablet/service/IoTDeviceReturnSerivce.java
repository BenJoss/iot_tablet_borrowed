package com.huafen.tablet.service;

import com.huafen.tablet.model.apply.IotBorroFlowDTO;
import com.huafen.tablet.model.apply.IotDeviReturnDTO;
import com.huafen.tablet.model.param.TabletMageParam;
import com.huafen.tablet.model.param.TabletRevertParam;
import com.huafen.tablet.model.req.RepDTO;

public interface IoTDeviceReturnSerivce {

	public RepDTO queryReturnInfoSerivce(TabletRevertParam tabletRevertParam); 
	
	public RepDTO checkReturnInfoSerivce(TabletRevertParam tabletRevertParam); 
	
	public RepDTO queryTabletInfoSerivce(TabletMageParam tabletRevertParam); 
	
	public RepDTO pushDeviceTopicByRedis(IotBorroFlowDTO iotBorroFlowDTO);
	
	public RepDTO bindDeviceTopicRedisCahce(IotBorroFlowDTO iotBorroFlowDTO);
	
	public RepDTO resetDeviceBindInfo(IotBorroFlowDTO iotBorroFlowDTO);
	
	public RepDTO returnIotTablBorroInfo(IotDeviReturnDTO iotDeviReturnDTO);
}
