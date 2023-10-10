package com.huafen.tablet.model.apply;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("借还码信息")
public class IotTablBorroCode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2863638475115491792L;
	@ApiModelProperty(value="借还验证码",example = "8484")
	private String verifyCode;
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	
	
}
