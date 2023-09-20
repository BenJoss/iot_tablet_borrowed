package com.huafen.tablet.model.chat;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("显示呼叫消息历史记录")
public class CallMsgDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4709928065182178804L;
	@ApiModelProperty(value="用户ID",example = "1111111")
	private String userID;
	@ApiModelProperty(value="用户名称",example = "张三")
	private String userName;
	@ApiModelProperty(value="聊天内容",example = "需要茶水")
	private String chatMsg;
	@ApiModelProperty(value="聊天时间",example = "2023-08-07 10:22:54")
	private String chatTime;
	@ApiModelProperty(value="消息状态",example = "已读、未读")
	private String state;
	private Long createTime;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	

}
