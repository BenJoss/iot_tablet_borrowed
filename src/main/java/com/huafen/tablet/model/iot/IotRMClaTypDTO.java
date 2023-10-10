package com.huafen.tablet.model.iot;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("首页会议室发布页分类")
public class IotRMClaTypDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2787414529368751105L;
	
	@ApiModelProperty(value="会议室ID",example = "3000001504019772")
	private String roomID;
	@ApiModelProperty(value="分类ID",example = "3000000806751530")
	private String classID; 
	@ApiModelProperty(value="分类名称",example = "信息发布")
	private String className; 
	@ApiModelProperty(value="页面类别",example = "分类")
	private String classType; 
	@ApiModelProperty(value="数量",example = "2")
	private int number;
	@ApiModelProperty(value="更新时间",example = "2023-09-10 16:32:06")
	private String updateTime;
	@ApiModelProperty(value="会议室发布页信息列表")
	private List<IotRMContInfoDTO> list;
	
	
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
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public List<IotRMContInfoDTO> getList() {
		return list;
	}
	public void setList(List<IotRMContInfoDTO> list) {
		this.list = list;
	}
	
	
}
