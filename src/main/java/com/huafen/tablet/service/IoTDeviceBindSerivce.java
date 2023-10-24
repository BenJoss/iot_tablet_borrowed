package com.huafen.tablet.service;

import com.huafen.tablet.model.apply.IotBindTabAllDTO;
import com.huafen.tablet.model.apply.IotBorroFlowDTO;
import com.huafen.tablet.model.param.TabletRevertParam;
import com.huafen.tablet.model.req.RepDTO;

public interface IoTDeviceBindSerivce {
	
	public RepDTO queryBindInfoSerivce(TabletRevertParam tabletRevertParam); 
	
	public RepDTO checkBindInfoSerivce(TabletRevertParam tabletRevertParam); 
	
	public RepDTO bindDeviceTopicRedisCahce(IotBorroFlowDTO iotBorroFlowDTO);
	
	public RepDTO resetBindDeviceInfoSerivce(IotBorroFlowDTO iotBorroFlowDTO);
	
	public RepDTO pushDeviceTopicByRedis(IotBorroFlowDTO iotBorroFlowDTO);
	
	public void loadDeviceInfo();
		
	public RepDTO bindIotTablBorroInfo(IotBindTabAllDTO iotBindTabAllDTO);
}
