package com.huafen.tablet.model.apply;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("设备借出取消")
public class IotTableCancleDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -615312374461776980L;

	@ApiModelProperty(value="借还验证码",required = true,example = "8484")
	private String verifyCode;
	@ApiModelProperty(value="借出用户ID",required = true,example = "4600072255")
	private String userID;
	private int borrowNum;
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public int getBorrowNum() {
		return borrowNum;
	}
	public void setBorrowNum(int borrowNum) {
		this.borrowNum = borrowNum;
	}
	
	
}
