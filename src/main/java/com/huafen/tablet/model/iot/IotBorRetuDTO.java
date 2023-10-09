package com.huafen.tablet.model.iot;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("设备借还操作记录")
public class IotBorRetuDTO implements Serializable{

	private static final long serialVersionUID = 6613734240534834878L;
	@ApiModelProperty(value="平板ID",example = "E280689400004020F535A17F")
	private String tabletID;
	@ApiModelProperty(value="借还验证码",required = true,example = "8484")
	private String verifyCode;
	@ApiModelProperty(value="平板借用状态",example = "1：使用中、2：空闲")
	private String borrowedStatus;
	public String getTabletID() {
		return tabletID;
	}
	public void setTabletID(String tabletID) {
		this.tabletID = tabletID;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public String getBorrowedStatus() {
		return borrowedStatus;
	}
	public void setBorrowedStatus(String borrowedStatus) {
		this.borrowedStatus = borrowedStatus;
	}
	
	
}
