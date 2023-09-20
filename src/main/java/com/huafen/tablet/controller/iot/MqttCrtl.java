package com.huafen.tablet.controller.iot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huafen.tablet.config.RepCode;
import com.huafen.tablet.model.iot.IotCtrlDAO;
import com.huafen.tablet.model.req.RepDTO;
import com.huafen.tablet.mqtt.MqttProviderConfig;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {"物联MQTT控制"})
@RestController
@RequestMapping("/MqttCrtl")
public class MqttCrtl {

    @Autowired
    private MqttProviderConfig providerClient;
    
    @ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "物联MQTT控制请求",notes = "控制某个会议室某个主题动作")
    @PostMapping("/crtIotopic")
    @ResponseBody
    public RepDTO  crtIotopic(@RequestBody IotCtrlDAO iotCtrlDAO){
    	RepDTO  repDTO = new RepDTO();
    	try {
        	String pubTopic = iotCtrlDAO.getPubTopic();
        	int qos = iotCtrlDAO.getQos();
        	String message = iotCtrlDAO.getMessage();
        	providerClient.publish(qos, false, pubTopic, message);
        	repDTO.setRepCode(RepCode.SUCCESS_CODE);
        	repDTO.setRepMsg("");
		} catch (RuntimeException e) {
			repDTO.setRepCode(RepCode.ERROR_CODE);
        	repDTO.setRepMsg(e.getMessage());
		}
    	return	repDTO;
    	
    }
    
}
