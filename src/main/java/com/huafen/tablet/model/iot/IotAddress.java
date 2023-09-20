package com.huafen.tablet.model.iot;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("首页会议室地址")
public class IotAddress implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7513089659002866677L;
	
	@ApiModelProperty(value="地址",example = "会议中心")
	private String address; 
	@ApiModelProperty(value="顺序",example = "1")
	private Integer order;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	} 
	
	
}
