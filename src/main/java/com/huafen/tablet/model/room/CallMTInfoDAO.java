package com.huafen.tablet.model.room;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class CallMTInfoDAO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3289602483156211363L;
	
	@ApiModelProperty(value="会议室ID")
	private String roomID;
	@ApiModelProperty(value="会议ID")
	private String meetID;
	@ApiModelProperty(value="会议名称")
	private String meetName;
	@ApiModelProperty(value="会议内容")
	private String meetCont;
	@ApiModelProperty(value="通知")
	private String notices;
	@ApiModelProperty(value="会议申请人")
	private String meetApply;
	@ApiModelProperty(value="会议地址")
	private String meetAddr;
	@ApiModelProperty(value="会议开始时间")
	private String startTime;
	@ApiModelProperty(value="会议结束时间")
	private String endTime;
	@ApiModelProperty(value="会议时间")
	private String meetTime;
	
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
	public String getMeetName() {
		return meetName;
	}
	public void setMeetName(String meetName) {
		this.meetName = meetName;
	}
	public String getMeetCont() {
		return meetCont;
	}
	public void setMeetCont(String meetCont) {
		this.meetCont = meetCont;
	}
	public String getNotices() {
		return notices;
	}
	public void setNotices(String notices) {
		this.notices = notices;
	}
	public String getMeetApply() {
		return meetApply;
	}
	public void setMeetApply(String meetApply) {
		this.meetApply = meetApply;
	}
	public String getMeetAddr() {
		return meetAddr;
	}
	public void setMeetAddr(String meetAddr) {
		this.meetAddr = meetAddr;
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
	public String getMeetTime() {
		return meetTime;
	}
	public void setMeetTime(String meetTime) {
		this.meetTime = meetTime;
	}
	
	
	
	
	

}
