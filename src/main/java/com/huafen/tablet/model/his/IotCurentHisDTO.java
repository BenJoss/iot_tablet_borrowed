package com.huafen.tablet.model.his;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("平板借还首页当日数据显示")
public class IotCurentHisDTO implements Serializable{

	private static final long serialVersionUID = 1688279144018492701L;

	@ApiModelProperty(value="借用数量",example = "2")
	private int borrowNum;
	@ApiModelProperty(value="借用状态",example = "1：待借用、2：借用中、3：完结、4：异常、5：取消",required = false)
	private String borrowedStatus;
	@ApiModelProperty(value="借出用户名称",example = "维康")
	private String userName;
	@ApiModelProperty(value="借用开始时间",example = "2023-09-14 08:00:00")
	private String startTime;
	@ApiModelProperty(value="借用结束时间",example = "2023-09-14 08:00:00")
	private String endTime;
	public int getBorrowNum() {
		return borrowNum;
	}
	public void setBorrowNum(int borrowNum) {
		this.borrowNum = borrowNum;
	}
	public String getBorrowedStatus() {
		return borrowedStatus;
	}
	public void setBorrowedStatus(String borrowedStatus) {
		this.borrowedStatus = borrowedStatus;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
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
	
}
