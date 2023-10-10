package com.huafen.tablet.model.param;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("平板归还查询参数")
public class TabletRevertParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5249304496188312173L;

	@ApiModelProperty(value="借还验证码",required = true,example = "8484")
	private String verifyCode;
	
	

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	
	
	
}
