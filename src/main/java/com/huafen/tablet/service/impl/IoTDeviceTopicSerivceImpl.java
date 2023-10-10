package com.huafen.tablet.service.impl;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huafen.tablet.model.apply.IotBorroFlowDTO;
import com.huafen.tablet.mqtt.MqttProviderConfig;
import com.huafen.tablet.service.IoTDeviceTopicSerivce;

@Service("ioTDeviceTopicSerivce")
public class IoTDeviceTopicSerivceImpl implements IoTDeviceTopicSerivce {
    
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(IoTDeviceTopicSerivceImpl.class);
	
	@Autowired
    private MqttProviderConfig providerClient;
    
	@Override
	public void OpenScannDevice(IotBorroFlowDTO iotBorroFlowDTO) {
		try {
        	String openTopic = iotBorroFlowDTO.getOpenTopic();
        	int qos = iotBorroFlowDTO.getQos();
        	String message = iotBorroFlowDTO.getMessage();
			providerClient.publish(qos, false, openTopic, message);
		} catch (Exception e) {
			log.error("异常",e.getMessage());
		}
		
	}

}
