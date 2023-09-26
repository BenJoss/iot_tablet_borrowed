package com.huafen.tablet.model.apply;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("移动端设备借出历史记录")
public class IotMvBorrHisDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4900107735880625044L;
	@ApiModelProperty(value="会议室名称")
	private String roomName;
	@ApiModelProperty(value="会议名称",example = "巴西变电站项目专项会议")
	private String meetName;
	@ApiModelProperty(value="借用数量",example = "8484")
	private int borrowNum;
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
	public int getBorrowNum() {
		return borrowNum;
	}
	public void setBorrowNum(int borrowNum) {
		this.borrowNum = borrowNum;
	}
	
	
}
