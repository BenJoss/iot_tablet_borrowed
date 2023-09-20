package com.huafen.tablet.model.chat;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("会议室会议状态信息")
public class CallRmStatDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2802216772592686982L;

	@ApiModelProperty(value="会议室ID",example = "8646084058906624")
	private String roomID;
	@ApiModelProperty(value="会议室名称",example = "A1-1619")
	private String roomName;
	@ApiModelProperty(value="会议ID",example = "8803044486774784")
	private String meetID;
	@ApiModelProperty(value="会议名称",example = "人资部会议")
	private String meetName;
	@ApiModelProperty(value="会议内容",example = "培训、申报、答辩等会议")
	private String meetInfo;
	@ApiModelProperty(value="会议状态",example = "进行中")
	private String meetStat;
	@ApiModelProperty(value="会议时间",example = "2023-08-07 10:22:54")
	private String meetTime;
	
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
	public String getMeetInfo() {
		return meetInfo;
	}
	public void setMeetInfo(String meetInfo) {
		this.meetInfo = meetInfo;
	}
	public String getMeetStat() {
		return meetStat;
	}
	public void setMeetStat(String meetStat) {
		this.meetStat = meetStat;
	}
	public String getMeetTime() {
		return meetTime;
	}
	public void setMeetTime(String meetTime) {
		this.meetTime = meetTime;
	}
	
	
}
