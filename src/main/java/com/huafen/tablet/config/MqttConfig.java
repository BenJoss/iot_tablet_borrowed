package com.huafen.tablet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.huafen.tablet.model.config.MqttProperties;
import com.huafen.tablet.mqtt.MqttAcceptClient;
import com.huafen.tablet.mqtt.MqttCondition;


@Configuration
@Order(1000)
public class MqttConfig {
	@Autowired
    private MqttAcceptClient mqttAcceptClient;
	@Autowired
	private MqttProperties mqttProperties;
    @Conditional(MqttCondition.class)
    @Bean
    public MqttAcceptClient getMqttAcceptClient(){
        mqttAcceptClient.setClientId(mqttProperties.getClientId());
        mqttAcceptClient.connect();
        return mqttAcceptClient;
    }
}
