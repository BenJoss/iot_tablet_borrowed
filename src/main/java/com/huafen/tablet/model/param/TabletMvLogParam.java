package com.huafen.tablet.model.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("移动端申请人借出平板记录")
public class TabletMvLogParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5289908044410301259L;

	@ApiModelProperty(value="借出用户ID",required = true,example = "4600072255")
	private String userID;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	
}
