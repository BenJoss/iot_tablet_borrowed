package com.huafen.tablet.model.param;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("首页会议室发布页绑定设备信息删除参数")
public class IotDeviceMdPara implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2139039116479301097L;


	private String deviceID;
	
	@ApiModelProperty(value="展示设备IDList",example = "[3000001504019772,3000001504019773]",required = true)
	private List<String> deviceIDList;
	
	private String contID;

	private String classID;
	
	
	public String getDeviceID() {
		return deviceID;
	}


	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}


	public List<String> getDeviceIDList() {
		return deviceIDList;
	}


	public void setDeviceIDList(List<String> deviceIDList) {
		this.deviceIDList = deviceIDList;
	}


	public String getContID() {
		return contID;
	}


	public void setContID(String contID) {
		this.contID = contID;
	}


	public String getClassID() {
		return classID;
	}


	public void setClassID(String classID) {
		this.classID = classID;
	}
	
	
}
