package com.huafen.tablet.model.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("查询会议室会议状态参数")
public class CallRmParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3663940060571042293L;
	
	@ApiModelProperty(value="会议室ID",example = "8297093077499904",required = true)
	private String roomID;

	@ApiModelProperty(value="会议ID",example = "8922950040190976",required = true)
	private String meetID;

	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public String getMeetID() {
		return meetID;
	}

	public void setMeetID(String meetID) {
		this.meetID = meetID;
	}
	
	
	
}
