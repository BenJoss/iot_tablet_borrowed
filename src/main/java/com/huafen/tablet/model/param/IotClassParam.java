package com.huafen.tablet.model.param;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("首页会议室一级删除参数")
public class IotClassParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5717769914987081308L;

	@ApiModelProperty(value="会议室ID",example = "3000001504019772",required = true)
	private String roomID;
	
	@ApiModelProperty(value="分类ID数组",example = "[3000001504019772,3000001504019773]",required = true)
	private List<String> classIDList;
	
	private String classID;

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

	public List<String> getClassIDList() {
		return classIDList;
	}

	public void setClassIDList(List<String> classIDList) {
		this.classIDList = classIDList;
	}
	
	
}
