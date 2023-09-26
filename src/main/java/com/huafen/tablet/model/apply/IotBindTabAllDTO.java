package com.huafen.tablet.model.apply;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("取平板录入信息")
public class IotBindTabAllDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8950319244625073362L;
	@ApiModelProperty(value="借还验证码",required = true,example = "8484")
	private String verifyCode;
	@ApiModelProperty(value="取出平板信息",required = true,example = "list")
	private List<IotBindTabletDTO> iotBindTabletList;
	
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public List<IotBindTabletDTO> getIotBindTabletList() {
		return iotBindTabletList;
	}
	public void setIotBindTabletList(List<IotBindTabletDTO> iotBindTabletList) {
		this.iotBindTabletList = iotBindTabletList;
	}
	
}
