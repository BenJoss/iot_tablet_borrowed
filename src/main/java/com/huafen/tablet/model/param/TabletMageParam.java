package com.huafen.tablet.model.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("平板编辑查询参数")
public class TabletMageParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3494744274480684327L;

	@ApiModelProperty(value="平板名称",example = "平板01",required = false)
	private String tabletName;
	@ApiModelProperty(value="平板状态",example = "1：启用、2：禁用",required = false)
	private String tabletState;
	@ApiModelProperty(value="平板借用状态",example = "1：使用中、2：空闲",required = false)
	private String borrowedStatus;
	@ApiModelProperty(value="借还验证码",example = "8484")
	private String verifyCode;
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
	public String getBorrowedStatus() {
		return borrowedStatus;
	}
	public void setBorrowedStatus(String borrowedStatus) {
		this.borrowedStatus = borrowedStatus;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	
	
}
