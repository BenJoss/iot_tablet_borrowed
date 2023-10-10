package com.huafen.tablet.model.iot;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("首页会议室发布页分类")
public class IotRMClassDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3896961540167516114L;


	@ApiModelProperty(value="会议室ID",example = "3000001504019772",required = true)
	private String roomID;
	@ApiModelProperty(value="分类ID",required = false)
	private String classID; 
	@ApiModelProperty(value="分类名称",example = "信息发布",required = true)
	private String className; 
	@ApiModelProperty(value="页面类别",required = false)
	private String classType; 
	@ApiModelProperty(value="权限",required = false)
	private String classAuth;
	public String getRoomID() {
		return roomID;
	}
	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}
	public String getClassID() {
		return classID;
	}
	public void setClassID(String classID) {
		this.classID = classID;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public String getClassAuth() {
		return classAuth;
	}
	public void setClassAuth(String classAuth) {
		this.classAuth = classAuth;
	} 
	
	
	
	
	
	
}
