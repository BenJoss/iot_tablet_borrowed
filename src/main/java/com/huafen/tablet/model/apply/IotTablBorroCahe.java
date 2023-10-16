package com.huafen.tablet.model.apply;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("设备借出历史记录")
public class IotTablBorroCahe implements Serializable{

	private static final long serialVersionUID = 2597981767508926933L;

	@ApiModelProperty(value="会议室名称",example = "A2-215")
	private String roomName;
	@ApiModelProperty(value="会议名称",example = "巴西变电站项目专项会议")
	private String meetName;
	@ApiModelProperty(value="借还验证码",example = "8484")
	private String verifyCode;
	@ApiModelProperty(value="借用数量",example = "2")
	private int borrowNum;
	@ApiModelProperty(value="借出用户名称",example = "维康")
	private String userName;
	@ApiModelProperty(value="借用时间",example = "2023-09-14")
	private String borrowTime;
	@ApiModelProperty(value="归还数量",example = "10")
	private int returnNum;
	@ApiModelProperty(value="归还时间",example = "2023-09-14 12:00:00")
	private String returnTime;
	@ApiModelProperty(value="借用状态",example = "待借用、借用中、完结、异常、取消")
	private String borrowedStatus;
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
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
	public void setBorrowNum(int borrowNum) {
		this.borrowNum = borrowNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getBorrowedStatus() {
		return borrowedStatus;
	}
	public void setBorrowedStatus(String borrowedStatus) {
		this.borrowedStatus = borrowedStatus;
	}
	
	
	
}
