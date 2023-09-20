package com.huafen.tablet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import com.huafen.tablet.mqtt.MqttAcceptClient;
import com.huafen.tablet.mqtt.MqttCondition;
@Configuration
public class MqttConfig {
	@Autowired
    private MqttAcceptClient mqttAcceptClient;
	
    @Conditional(MqttCondition.class)
    @Bean
    public MqttAcceptClient getMqttAcceptClient(){
        mqttAcceptClient.connect();
        return mqttAcceptClient;
    }
}
