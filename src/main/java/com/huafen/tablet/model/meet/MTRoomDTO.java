package com.huafen.tablet.model.meet;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("IOT会议室信息")
public class MTRoomDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1681937980440116866L;
	
	@ApiModelProperty(value="会议室名称",example = "A2-204会议室")
	private String  roomName;
	@ApiModelProperty(value="楼层  ",example = "2F")
	private String  floor;
	@ApiModelProperty(value="地址  ",example = "会议中心")
	private String  building;
	@ApiModelProperty(value="uploadToken",example = "")
	private String  uploadToken;
	@ApiModelProperty(value="主键",example = "5b9ed459-2066-479b-b491-ee717beb9db8")
	private String  areaId;
	
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getUploadToken() {
		return uploadToken;
	}
	public void setUploadToken(String uploadToken) {
		this.uploadToken = uploadToken;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
	
}
