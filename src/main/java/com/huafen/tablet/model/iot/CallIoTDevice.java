package com.huafen.tablet.model.iot;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("物联设备信息")
public class CallIoTDevice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7356794713091247651L;

	@ApiModelProperty(value="会议室ID",example = "8646084058906624")
	private String roomID;
	@ApiModelProperty(value="会议室名称",example = "A1-1617")
	private String roomName;
	@ApiModelProperty(value="会议室温度",example = "25")
	private String  roomTemp;
	@ApiModelProperty(value="会议室湿度",example = "60%")
	private String  roomHum;
	@ApiModelProperty(value="会议室灯光状态",example = "开")
	private String  roomLhtStat;
	@ApiModelProperty(value="会议室门锁状态",example = "关")
	private String  roomDoLkStat;
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
	public String getRoomTemp() {
		return roomTemp;
	}
	public void setRoomTemp(String roomTemp) {
		this.roomTemp = roomTemp;
	}
	public String getRoomHum() {
		return roomHum;
	}
	public void setRoomHum(String roomHum) {
		this.roomHum = roomHum;
	}
	public String getRoomLhtStat() {
		return roomLhtStat;
	}
	public void setRoomLhtStat(String roomLhtStat) {
		this.roomLhtStat = roomLhtStat;
	}
	public String getRoomDoLkStat() {
		return roomDoLkStat;
	}
	public void setRoomDoLkStat(String roomDoLkStat) {
		this.roomDoLkStat = roomDoLkStat;
	}
	
	
}
