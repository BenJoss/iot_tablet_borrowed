package com.huafen.tablet.service;

import com.huafen.tablet.model.apply.IotBorroFlowDTO;

public interface IoTDeviceTopicSerivce {

	
	public void OpenScannDevice(IotBorroFlowDTO iotBorroFlowDTO);
	
	public void CloseScannDevice(IotBorroFlowDTO iotBorroFlowDTO);
}
