package com.huafen.tablet.model.chat;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("呼叫消息信息")
public class CallChat implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5427379808589396671L;

	@ApiModelProperty(value="会议室ID")
	private String roomID;
	@ApiModelProperty(value="会议室名称")
	private String roomName;
	@ApiModelProperty(value="会议ID")
	private String meetID;
	@ApiModelProperty(value="用户ID")
	private String userID;
	@ApiModelProperty(value="聊天内容")
	private String chatMsg;
	@ApiModelProperty(value="时间")
	private String time;
	
	@ApiModelProperty(value="呼叫服务用户信息")
	private CallUser  callUser ;
	
	@ApiModelProperty(value="呼叫会议基本信息")
	private CallBase  callBase ;
	

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
	public String getMeetID() {
		return meetID;
	}
	public void setMeetID(String meetID) {
		this.meetID = meetID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getChatMsg() {
		return chatMsg;
	}
	public void setChatMsg(String chatMsg) {
		this.chatMsg = chatMsg;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public CallUser getCallUser() {
		return callUser;
	}
	public void setCallUser(CallUser callUser) {
		this.callUser = callUser;
	}
	public CallBase getCallBase() {
		return callBase;
	}
	public void setCallBase(CallBase callBase) {
		this.callBase = callBase;
	}
	
	
	
	
	
}
