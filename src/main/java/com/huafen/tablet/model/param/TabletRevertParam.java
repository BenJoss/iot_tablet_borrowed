package com.huafen.tablet.model.param;

import java.io.Serializable;
import java.util.List;

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
	
	@ApiModelProperty(value="借用状态",example = "1：待借用、2：借用中、3：完结、4：异常、5：取消",required = false)
	private String borrowedStatus;

	@ApiModelProperty(value="借用状态",example = "1：待借用、2：借用中、3：完结、4：异常、5：取消",required = false)
	private List<String> borrowedStatusList;
	
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

	public List<String> getBorrowedStatusList() {
		return borrowedStatusList;
	}

	public void setBorrowedStatusList(List<String> borrowedStatusList) {
		this.borrowedStatusList = borrowedStatusList;
	}

	
	
	
	
	
}
