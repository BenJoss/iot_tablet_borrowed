package com.huafen.tablet.model.ship;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户权限")
public class UserAuth implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7979452135242283351L;
	@ApiModelProperty(value="用户ID")
	private String userID;
	@ApiModelProperty(value="权限编号")
	private String authCode;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
	
	
}
