package com.huafen.tablet.model.iot;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("物联会议室信息")
public class IOTRoomDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5259726204125894667L;
	@ApiModelProperty(value="会议室ID")
	private String roomID;
	@ApiModelProperty(value="会议室名称",required = true,example = "A1-1617")
	private String roomName;
	@ApiModelProperty(value="会议室地址",required = true,example = "会议中心")
	private String roomAddr;
	@ApiModelProperty(value="楼层",required = true,example = "1F")
	private String floor;
    @ApiModelProperty(value="会议室图片",required = true,example = "../index/1.png")
	private String roomImg;
	@ApiModelProperty(value="会议室信息来源")
	private String source;
	@ApiModelProperty(value="会议室一级发布页数量",example = "2")
	private int roomClassNum;
	@ApiModelProperty(value="会议室发布页数量",example = "4")
	private int roomContNum;
	
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
	public String getRoomAddr() {
		return roomAddr;
	}
	public void setRoomAddr(String roomAddr) {
		this.roomAddr = roomAddr;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getRoomImg() {
		return roomImg;
	}
	public void setRoomImg(String roomImg) {
		this.roomImg = roomImg;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public int getRoomClassNum() {
		return roomClassNum;
	}
	public void setRoomClassNum(int roomClassNum) {
		this.roomClassNum = roomClassNum;
	}
	public int getRoomContNum() {
		return roomContNum;
	}
	public void setRoomContNum(int roomContNum) {
		this.roomContNum = roomContNum;
	}
	
	
}
