package com.huafen.tablet.model.chat;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("呼叫会议基本信息")
public class CallBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8928553964392702374L;

	@ApiModelProperty(value="会议室ID")
	private String roomID;
	@ApiModelProperty(value="会议室名称")
	private String roomName;
	@ApiModelProperty(value="会议ID")
	private String meetID;
	@ApiModelProperty(value="会议名称")
	private String meetName;
	@ApiModelProperty(value="会议内容")
	private String meetInfo;
	@ApiModelProperty(value="会议状态")
	private String meetStat;
	@ApiModelProperty(value="用户ID")
	private String userID;
	@ApiModelProperty(value="会议室地址")
	private String roomAddr; 
	@ApiModelProperty(value="所在楼层")
	private String roomFloor; 
	@ApiModelProperty(value="会议室图片")
	private String roomImg;
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
	public String getMeetID() {
		return meetID;
	}
	public void setMeetID(String meetID) {
		this.meetID = meetID;
	}
	public String getMeetName() {
		return meetName;
	}
	public void setMeetName(String meetName) {
		this.meetName = meetName;
	}
	public String getMeetInfo() {
		return meetInfo;
	}
	public void setMeetInfo(String meetInfo) {
		this.meetInfo = meetInfo;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getRoomID() {
		return roomID;
	}
	public void setRoomID(String roomID) {
		this.roomID = roomID;
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
	public String getRoomFloor() {
		return roomFloor;
	}
	public void setRoomFloor(String roomFloor) {
		this.roomFloor = roomFloor;
	}
	public String getRoomImg() {
		return roomImg;
	}
	public void setRoomImg(String roomImg) {
		this.roomImg = roomImg;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}
