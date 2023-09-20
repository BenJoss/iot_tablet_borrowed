package com.huafen.tablet.model.iot;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("首页会议室发布页绑定设备信息")
public class IotDeviceDAO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8574054608763410749L;
	
	@ApiModelProperty(value="内容ID",example = "3000001504019772",required = true)
	private String contID;

	@ApiModelProperty(value="展示设备ID",example = "3000001504019772",required = true)
	private String deviceID;
	
	@ApiModelProperty(value="展示设备名称",example = "信息发布屏",required = true)
	private String deviceName;
	
	@ApiModelProperty(value="设备类型 1:移动、2:大屏",example = "1",required = true)
	private String deviceType;
	
	@ApiModelProperty(value="设备型号",example = "huawei",required = true)
	private String deviceModel;
	
	
	public String getContID() {
		return contID;
	}

	public void setContID(String contID) {
		this.contID = contID;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}


	
	
	

}
