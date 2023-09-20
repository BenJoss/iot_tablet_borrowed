package com.huafen.tablet.model.chat;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("会议室消息显示")
public class CallRmLtShowDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8576103518256523747L;

	@ApiModelProperty(value="会议室ID",example = "8646084058906624")
	private String roomID;
	@ApiModelProperty(value="会议室名称",example = "A1-1619")
	private String roomName;
	@ApiModelProperty(value="会议室图片",example = "../A1.png")
	private String roomImg;
	@ApiModelProperty(value="会议ID",example = "8803044486774784")
	private String meetID;
	@ApiModelProperty(value="会议名称",example = "人资部会议")
	private String meetName;
	@ApiModelProperty(value="消息状态",example = "已读,未读")
	private String chatState;
	@ApiModelProperty(value="消息时间",example = "2023-08-07 10:22:54")
	private String chatTime;
	@ApiModelProperty(value="最新时间",example = "2023-08-07 10:22:54")
	private Long createTime;
	@ApiModelProperty(value="会议最新消息",example = "好的,马上到")
	private String lastChat;
	

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
	public String getRoomImg() {
		return roomImg;
	}
	public void setRoomImg(String roomImg) {
		this.roomImg = roomImg;
	}
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
	
	public String getChatState() {
		return chatState;
	}
	public void setChatState(String chatState) {
		this.chatState = chatState;
	}
	public String getChatTime() {
		return chatTime;
	}
	public void setChatTime(String chatTime) {
		this.chatTime = chatTime;
	}
	
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public String getLastChat() {
		return lastChat;
	}
	public void setLastChat(String lastChat) {
		this.lastChat = lastChat;
	}
	
	
	
}
