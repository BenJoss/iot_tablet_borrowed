package com.huafen.tablet.model.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("平板历史查询参数")
public class TabletHisParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4141526034593950299L;
	
	@ApiModelProperty(value="借用时间 开始时间",required = true,example = "2023-09-14 08:30:00")
	private String startTime;
	@ApiModelProperty(value="借用时间 结束时间",required = true,example = "2023-09-14 10:30:00")
	private String endTime;
	@ApiModelProperty(value="借用人",required = false,example = "维康")
	private String userName;
	@ApiModelProperty(value="借用状态",example = "1：待借用、2：借用中、3：完结、4：异常、5：取消",required = false)
	private String borrowedStatus;
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBorrowedStatus() {
		return borrowedStatus;
	}
	public void setBorrowedStatus(String borrowedStatus) {
		this.borrowedStatus = borrowedStatus;
	}

	
}
