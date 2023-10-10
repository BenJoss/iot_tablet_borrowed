package com.huafen.tablet.model.iot;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("物联设备所在楼层")
public class CallIotFloor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1185687628691422302L;

	@ApiModelProperty(value="设备所在楼层",example = "一")
	private String roomFloor; 
	@ApiModelProperty(value="物联设备信息")
	private List<CallIoTDevice> callIoTDeviceList;
	public String getRoomFloor() {
		return roomFloor;
	}
	public void setRoomFloor(String roomFloor) {
		this.roomFloor = roomFloor;
	}
	public List<CallIoTDevice> getCallIoTDeviceList() {
		return callIoTDeviceList;
	}
	public void setCallIoTDeviceList(List<CallIoTDevice> callIoTDeviceList) {
		this.callIoTDeviceList = callIoTDeviceList;
	}
	
	
}
