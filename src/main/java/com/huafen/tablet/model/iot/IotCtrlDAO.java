package com.huafen.tablet.model.iot;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("物联控制参数")
public class IotCtrlDAO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8552484445580061829L;
	
	@ApiModelProperty(value="主题",example = "A2-228/228-power")
  	private String pubTopic;
	@ApiModelProperty(value="消息",example = "ON")
  	private String message;
	@ApiModelProperty(value="1:持久",example = "0")
  	private int qos;
	@ApiModelProperty(value="是否",example = "false")
  	private Boolean retained = false;
	
	public String getPubTopic() {
		return pubTopic;
	}
	public void setPubTopic(String pubTopic) {
		this.pubTopic = pubTopic;
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
