package com.huafen.tablet.model.iot;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("首页会议室发布页信息修改")
public class IotRMCoMdDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4024557793513428837L;
	@ApiModelProperty(value="分类ID",example = "3000001336943648",required = true)
	private String classID; 
	@ApiModelProperty(value="内容ID",example = "3000001504019772",required = true)
	private String contID; 
	@ApiModelProperty(value="内容名称",example = "设置页面",required = false)
	private String contName; 
	@ApiModelProperty(value="内容发布路径",example = "http://10.31.0.101:1880/A2-227/",required = false)
	private String pubPath;
	@ApiModelProperty(value="内容编辑路径",example = "http://10.31.0.101:1880/#flow/52c3354996b6a408",required = false)
	private String editPath;
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
	public List<IotDeviceDTO> getIotDeviceList() {
		return iotDeviceList;
	}
	public void setIotDeviceList(List<IotDeviceDTO> iotDeviceList) {
		this.iotDeviceList = iotDeviceList;
	}
	

}
