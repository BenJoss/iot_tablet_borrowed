package com.huafen.tablet.model.room;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class CallRoomDAO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7131358663424513591L;

	@ApiModelProperty(value="会议室ID")
	private String roomID;
	@ApiModelProperty(value="会议室名称")
	private String roomName;
	@ApiModelProperty(value="会议室地址")
	private String roomAddr;
	@ApiModelProperty(value="楼层ID")
	private Long floorID;
	@ApiModelProperty(value="楼层")
	private String floor;
	@ApiModelProperty(value="会议室图片")
	private String roomImg;
	@ApiModelProperty(value="会议室信息来源")
	private String source;
	@ApiModelProperty(value="会议室显示顺序")
	private int order;
	
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
	public Long getFloorID() {
		return floorID;
	}
	public void setFloorID(Long floorID) {
		this.floorID = floorID;
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
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	
	
	
}
