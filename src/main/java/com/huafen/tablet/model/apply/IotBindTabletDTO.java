package com.huafen.tablet.model.apply;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("取平板绑定信息")
public class IotBindTabletDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -369176454357661840L;
	@ApiModelProperty(value="平板ID",example = "E280689400004020F535A17F")
	private String tabletID;
	@ApiModelProperty(value="平板名称",example = "平板01")
	private String tabletName;
	@ApiModelProperty(value="平板状态",example = "1：启用、2：禁用")
	private String tabletState;
	@ApiModelProperty(value="借还验证码",example = "8484")
	private String verifyCode;
	@ApiModelProperty(value="借用状态",example = "1：待借用、2：借用中、3：完结、4：异常、5：取消",required = false)
	private String borrowedStatus;
	
	public String getTabletID() {
		return tabletID;
	}
	public void setTabletID(String tabletID) {
		this.tabletID = tabletID;
	}
	public String getTabletName() {
		return tabletName;
	}
	public void setTabletName(String tabletName) {
		this.tabletName = tabletName;
	}
	public String getTabletState() {
		return tabletState;
	}
	public void setTabletState(String tabletState) {
		this.tabletState = tabletState;
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
