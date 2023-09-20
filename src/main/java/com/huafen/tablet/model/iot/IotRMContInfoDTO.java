package com.huafen.tablet.model.iot;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("首页会议室发布页信息")
public class IotRMContInfoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6244618311289958623L;
	
	@ApiModelProperty(value="分类ID",example = "3000000806751530")
	private String classID; 
	@ApiModelProperty(value="内容ID")
	private String contID; 
	@ApiModelProperty(value="内容名称",example = "设置页面")
	private String contName; 
	@ApiModelProperty(value="内容类型",example = "页面")
	private String contType;
	@ApiModelProperty(value="内容发布路径",example = "http://10.31.0.101:1880/A2-227/")
	private String pubPath;
	@ApiModelProperty(value="内容编辑路径",example = "http://10.31.0.101:1880/#flow/52c3354996b6a408")
	private String editPath;
	@ApiModelProperty(value="更新时间",example = "2023-09-10 16:32:06")
	private String updateTime;
	@ApiModelProperty(value="首页会议室发布页绑定设备信息列表")
	private List<IotDeviceDTO> iotDeviceList;
	
	public String getClassID() {
		return classID;
	}
	public void setClassID(String classID) {
		this.classID = classID;
	}
	public String getContID() {
		return contID;
	}
	public void setContID(String contID) {
		this.contID = contID;
	}
	public String getContName() {
		return contName;
	}
	public void setContName(String contName) {
		this.contName = contName;
	}
	public String getContType() {
		return contType;
	}
	public void setContType(String contType) {
		this.contType = contType;
	}
	public String getPubPath() {
		return pubPath;
	}
	public void setPubPath(String pubPath) {
		this.pubPath = pubPath;
	}
	public String getEditPath() {
		return editPath;
	}
	public void setEditPath(String editPath) {
		this.editPath = editPath;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public List<IotDeviceDTO> getIotDeviceList() {
		return iotDeviceList;
	}
	public void setIotDeviceList(List<IotDeviceDTO> iotDeviceList) {
		this.iotDeviceList = iotDeviceList;
	}
	
	
	
	
}
