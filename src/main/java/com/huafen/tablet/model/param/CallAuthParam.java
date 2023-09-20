package com.huafen.tablet.model.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("呼叫权限查询参数")
public class CallAuthParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2851700684313318136L;
	@ApiModelProperty(value="用户ID",required = true,example = "00000000")
	private String userID;
	
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	

}
