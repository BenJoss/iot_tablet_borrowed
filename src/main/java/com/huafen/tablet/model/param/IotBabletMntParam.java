package com.huafen.tablet.model.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("预约人参会信息查询参数")
public class IotBabletMntParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -202541936725557330L;

	@ApiModelProperty(value="借出用户ID",required = true,example = "4600072255")
	private String userID;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	
	
}
