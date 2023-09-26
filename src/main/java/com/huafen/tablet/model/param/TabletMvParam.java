package com.huafen.tablet.model.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("移动端平板历史查询参数")
public class TabletMvParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 670986258468897535L;

	@ApiModelProperty(value="时间",required = true,example = "2023-09-14 00:00:00")
	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
	
}
