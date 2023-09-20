package com.huafen.tablet.model.param;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("首页会议室发布页删除参数")
public class IotConParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3470874338818222690L;
	
	private String contID;
	
	@ApiModelProperty(value="内容ID数组",example = "[3000001504019772,3000001504019773]",required = true)
	private List<String> contIDList;
	
	private String classID; 


	public String getContID() {
		return contID;
	}

	public void setContID(String contID) {
		this.contID = contID;
	}
	
	public List<String> getContIDList() {
		return contIDList;
	}

	public void setContIDList(List<String> contIDList) {
		this.contIDList = contIDList;
	}

	public String getClassID() {
		return classID;
	}

	public void setClassID(String classID) {
		this.classID = classID;
	}
	
	

}
