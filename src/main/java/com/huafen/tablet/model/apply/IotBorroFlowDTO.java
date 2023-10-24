package com.huafen.tablet.model.apply;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("启动平板借取流程参数")
public class IotBorroFlowDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5094074821909560054L;
	@ApiModelProperty(value="借还验证码",required = true,example = "8484")
	private String verifyCode;
	@ApiModelProperty(value="打开设备topic",required = true,example = "A2-206/206-RFID-DOWN")
	private String openTopic;
	@ApiModelProperty(value="关闭设备topic",required = true,example = "A2-206/206-RFID-DOWN")
	private String closeTopic;
	@ApiModelProperty(value="扫描设备topic",required = true,example = "A2-206/206-RFID-UP")
	private String topic;
	@ApiModelProperty(value="平板ID",example = "E280689400004020F535A17F")
	private String tabletID;
	@ApiModelProperty(value="消息",required = true,example = "ON")
  	private String message;
	@ApiModelProperty(value="1:持久",example = "0")
  	private int qos;
	@ApiModelProperty(value="是否",example = "false")
  	private Boolean retained = false;
	
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	
	public String getTabletID() {
		return tabletID;
	}
	public void setTabletID(String tabletID) {
		this.tabletID = tabletID;
	}
	public String getOpenTopic() {
		return openTopic;
	}
	public void setOpenTopic(String openTopic) {
		this.openTopic = openTopic;
	}
	public String getCloseTopic() {
		return closeTopic;
	}
	public void setCloseTopic(String closeTopic) {
		this.closeTopic = closeTopic;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getQos() {
		return qos;
	}
	public void setQos(int qos) {
		this.qos = qos;
	}
	public Boolean getRetained() {
		return retained;
	}
	public void setRetained(Boolean retained) {
		this.retained = retained;
	}
	
	
}
