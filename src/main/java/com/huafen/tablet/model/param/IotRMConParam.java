package com.huafen.tablet.model.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("首页会议室发布页查询参数")
public class IotRMConParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 201185852503043673L;
	
	@ApiModelProperty(value="分类ID",example = "3000000806751530")
	private String classID;

	public String getClassID() {
		return classID;
	}

	public void setClassID(String classID) {
		this.classID = classID;
	} 

	
}
