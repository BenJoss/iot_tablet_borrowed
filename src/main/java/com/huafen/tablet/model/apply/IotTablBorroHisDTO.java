package com.huafen.tablet.model.apply;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("取平板预约信息")
public class IotTablBorroHisDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5430306050571374894L;
	@ApiModelProperty(value="会议室名称")
	private String roomName;
	@ApiModelProperty(value="会议名称",example = "人资部会议")
	private String meetName;
	@ApiModelProperty(value="会议开始时间",example = "2023-08-07 10:22:54")
	private String meetStartTime;
	@ApiModelProperty(value="会议结束时间",example = "2023-08-07 10:30:54")
	private String meetEndTime;
	@ApiModelProperty(value="借出用户名称",example = "维康")
	private String userName;
	@ApiModelProperty(value="借用数量",example = "3")
	private int borrowNum;
	@ApiModelProperty(value="借用开始时间",example = "6月30日 9:00-12:00")
	private String borrowStartTime;
	@ApiModelProperty(value="借用结束时间",example = "6月30日 9:00-12:00")
	private String borrowEndTime;
	
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
	
	public String getMeetStartTime() {
		return meetStartTime;
	}
	public void setMeetStartTime(String meetStartTime) {
		this.meetStartTime = meetStartTime;
	}
	public String getMeetEndTime() {
		return meetEndTime;
	}
	public void setMeetEndTime(String meetEndTime) {
		this.meetEndTime = meetEndTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getBorrowNum() {
		return borrowNum;
	}
	public void setBorrowNum(int borrowNum) {
		this.borrowNum = borrowNum;
	}
	public String getBorrowStartTime() {
		return borrowStartTime;
	}
	public void setBorrowStartTime(String borrowStartTime) {
		this.borrowStartTime = borrowStartTime;
	}
	public String getBorrowEndTime() {
		return borrowEndTime;
	}
	public void setBorrowEndTime(String borrowEndTime) {
		this.borrowEndTime = borrowEndTime;
	}

	
	

}
