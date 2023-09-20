package com.huafen.tablet.model.chat;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "呼叫消息信息")
public class CallChatDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7559197328928042895L;
	
	@ApiModelProperty(value="会议室ID",required = true,example = "8646084058906624")
	private String roomID;
	@ApiModelProperty(value="会议ID",required = true,example = "8646084058906624")
	private String meetID;
	@ApiModelProperty(value="用户信息",required = true)
	private CallUser callUser;
	@ApiModelProperty(value="聊天内容",required = true,example = "需要麦克风")
	private String chatMsg;
	@ApiModelProperty(value="聊天时间",required = true,example = "2023-08-07 10:22:54")
	private String chatTime;
	@ApiModelProperty(value="消息状态",required = true,example = "已读、未读")
	private String state;
	@ApiModelProperty(value="消息类型",required = true,example = "init")
	private String type;
	
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
	public CallUser getCallUser() {
		return callUser;
	}
	public void setCallUser(CallUser callUser) {
		this.callUser = callUser;
	}
	public String getChatMsg() {
		return chatMsg;
	}
	public void setChatMsg(String chatMsg) {
		this.chatMsg = chatMsg;
	}
	public String getChatTime() {
		return chatTime;
	}
	public void setChatTime(String chatTime) {
		this.chatTime = chatTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	

}
