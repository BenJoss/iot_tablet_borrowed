package com.huafen.tablet.model.his;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("平板借还首页数据显示")
public class IotBorrowHisDTO implements Serializable{

	private static final long serialVersionUID = -3506631705514949405L;
	@ApiModelProperty(value="会议室ID",example = "8646084058906624")
	private String roomID;
	@ApiModelProperty(value="会议室名称",example = "A1-1617")
	private String roomName;
	@ApiModelProperty(value="会议ID",example = "8891520444825600")
	private String meetID; 
	@ApiModelProperty(value="会议名称",example = "激发企业青年创新创效动能，助力高质量发展交流座谈会")
	private String meetName; 
	@ApiModelProperty(value="借还验证码",example = "8484")
	private String verifyCode;
	@ApiModelProperty(value="借用数量",example = "2")
	private int borrowNum;
	@ApiModelProperty(value="借用状态",example = "1：待借用、2：借用中、3：完结、4：异常、5：取消",required = false)
	private String borrowedStatus;
	@ApiModelProperty(value="归还数量",example = "2")
	private int returnNum;
	@ApiModelProperty(value="借出用户名称",example = "维康")
	private String userName;
	@ApiModelProperty(value="借用开始时间",example = "2023-09-14 08:00:00")
	private String startTime;
	@ApiModelProperty(value="借用时间",example = "2023-09-14 08:00:00")
	private String borrowTime;
	@ApiModelProperty(value="借用结束时间",example = "2023-09-14 08:00:00")
	private String endTime;
	@ApiModelProperty(value="归还时间",example = "2023-09-14 08:00:00")
	private String returnTime;
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
	public String getMeetName() {
		return meetName;
	}
	public void setMeetName(String meetName) {
		this.meetName = meetName;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public int getBorrowNum() {
		return borrowNum;
	}
	
	public String getBorrowedStatus() {
		return borrowedStatus;
	}
	public void setBorrowedStatus(String borrowedStatus) {
		this.borrowedStatus = borrowedStatus;
	}
	public void setBorrowNum(int borrowNum) {
		this.borrowNum = borrowNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getBorrowTime() {
		return borrowTime;
	}
	public void setBorrowTime(String borrowTime) {
		this.borrowTime = borrowTime;
	}
	public int getReturnNum() {
		return returnNum;
	}
	public void setReturnNum(int returnNum) {
		this.returnNum = returnNum;
	}
	public String getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}
	
	
}
