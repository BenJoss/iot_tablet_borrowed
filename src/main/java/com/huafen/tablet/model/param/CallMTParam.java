package com.huafen.tablet.model.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class CallMTParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1139936738641032625L;
	@ApiModelProperty(value="会议室ID",example = "8646084058906624")
	private String roomID;
	@ApiModelProperty(value="会议ID")
	private String meetID;
	private String source;
	@ApiModelProperty(value="楼层号",example = "1F")
	private String floor;
	@ApiModelProperty(value="会议室名称",example = "A2-310")
	private String roomName;
	@ApiModelProperty(value="会议室地址",example = "会议中心")
	private String roomAddr;
	
	
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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomAddr() {
		return roomAddr;
	}

	public void setRoomAddr(String roomAddr) {
		this.roomAddr = roomAddr;
	}
	
	

}
