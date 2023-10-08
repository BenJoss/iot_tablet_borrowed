package com.huafen.tablet.model.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("查询借还码参数")
public class TabletApCoParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6406989352476096407L;
	@ApiModelProperty(value="借出用户ID",required = true,example = "4600072255")
	private String userID;
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	
}
