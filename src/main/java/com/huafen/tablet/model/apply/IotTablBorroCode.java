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
	@ApiModelProperty(value="借用数量",required = true,example = "3")
	private int borrowNum;
	@ApiModelProperty(value="归还数量",required = true,example = "10")
	private Integer returnNum;
	@ApiModelProperty(value="借用时间",required = true,example = "全天、上午、下午")
	private String borrowTime;
	@ApiModelProperty(value="借用状态",example = "1：待借用、2：借用中、3：完结、4：异常、5：取消",required = false)
	private String borrowedStatus;
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public int getBorrowNum() {
		return borrowNum;
	}
	public void setBorrowNum(int borrowNum) {
		this.borrowNum = borrowNum;
	}
	public Integer getReturnNum() {
		return returnNum;
	}
	public void setReturnNum(Integer returnNum) {
		this.returnNum = returnNum;
	}
	public String getBorrowTime() {
		return borrowTime;
	}
	public void setBorrowTime(String borrowTime) {
		this.borrowTime = borrowTime;
	}
	public String getBorrowedStatus() {
		return borrowedStatus;
	}
	public void setBorrowedStatus(String borrowedStatus) {
		this.borrowedStatus = borrowedStatus;
	}
	
	
}
