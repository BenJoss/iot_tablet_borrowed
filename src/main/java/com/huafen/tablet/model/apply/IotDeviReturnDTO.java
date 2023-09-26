package com.huafen.tablet.model.apply;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("设备归还记录")
public class IotDeviReturnDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2880667037834206030L;
	@ApiModelProperty(value="借还验证码",required = true,example = "8484")
	private String verifyCode;
	@ApiModelProperty(value="归还数量",required = true,example = "10")
	private int returnNum;
	@ApiModelProperty(value="归还时间",required = true,example = "2023-09-14 12:00:00")
	private String returnTime;
	@ApiModelProperty(value="创建时间",required = false,example = "2023-09-14 08:00:00")	
	private String createTime;
	
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public int getReturnNum() {
		return returnNum;
	}
	public void setReturnNum(int returnNum) {
		this.returnNum = returnNum;
	}
	public String getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}
