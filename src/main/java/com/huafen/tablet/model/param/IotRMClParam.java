package com.huafen.tablet.model.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("首页会议室发布页查询参数")
public class IotRMClParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4544199768175277298L;
	
	@ApiModelProperty(value="会议室ID",example = "3000001504019772",required = true)
	private String roomID;

	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}
	
	

}
