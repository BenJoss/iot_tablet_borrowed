package com.huafen.tablet.model.iot;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("首页会议室发布页分类修改")
public class IotRMClMdDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6506826802739903495L;
	
	@ApiModelProperty(value="会议室ID",example = "3000001504019772",required = true)
	private String roomID;
	@ApiModelProperty(value="分类ID",example = "3000000806751530",required = true)
	private String classID; 
	@ApiModelProperty(value="分类名称",example = "信息发布",required = false)
	private String className;
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
	
	

}
