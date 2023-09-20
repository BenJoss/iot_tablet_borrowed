package com.huafen.tablet.model.iot;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("首页会议室所在楼层")
public class IotFloor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4111113784433133837L;
	
	@ApiModelProperty(value="楼层",example = "1F")
	private String floor; 
	@ApiModelProperty(value="顺序",example = "1")
	private Integer order;
	
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	} 
	

}
