package com.huafen.tablet.model.apply;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("设备借出记录")
public class IotTablBorroDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8060218740793946992L;
	@ApiModelProperty(value="会议室ID",example = "8559878580142080",required = true)
	private String roomID;
	@ApiModelProperty(value="会议ID",required = true,example = "8646084058906624")
	private String meetID;
	@ApiModelProperty(value="借还验证码",required = false,example = "8484")
	private String verifyCode;
	@ApiModelProperty(value="借用数量",required = true,example = "3")
	private int borrowNum;
	@ApiModelProperty(value="借出用户ID",required = true,example = "4600072255")
	private String userID;
	@ApiModelProperty(value="借出用户名称",required = true,example = "维康")
	private String userName;
	@ApiModelProperty(value="借出部门",required = false,example = "46000")
	private String borrowOrg;
	@ApiModelProperty(value="联系方式",required = false,example = "15651750271")
	private String connectWay;
	@ApiModelProperty(value="借用状态",example = "1：待借用、2：借用中、3：完结、4：异常、5：取消",required = false)
	private String borrowedStatus;
	@ApiModelProperty(value="借用时间",required = true,example = "全天、上午、下午")
	private String borrowTime;
	@ApiModelProperty(value="借用时间 开始时间",required = true,example = "2023-09-14 08:00:00")
	private String startTime;
	@ApiModelProperty(value="借用时间 结束时间",required = true,example = "2023-09-14 14:00:00")
	private String endTime;
	@ApiModelProperty(value="创建时间",required = false,example = "2023-09-14 08:00:00")	
	private String createTime;
	
	private String alldayTime;
	
	private String mornTime;
	
	private String afterTime;
	
	public String getRoomID() {
		return roomID;
	}
	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}
	public String getMeetID() {
		return meetID;
	}
	public void setMeetID(String meetID) {
		this.meetID = meetID;
	}
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
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBorrowOrg() {
		return borrowOrg;
	}
	public void setBorrowOrg(String borrowOrg) {
		this.borrowOrg = borrowOrg;
	}
	public String getConnectWay() {
		return connectWay;
	}
	public void setConnectWay(String connectWay) {
		this.connectWay = connectWay;
	}
	public String getBorrowTime() {
		return borrowTime;
	}
	public void setBorrowTime(String borrowTime) {
		this.borrowTime = borrowTime;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getBorrowedStatus() {
		return borrowedStatus;
	}
	public void setBorrowedStatus(String borrowedStatus) {
		this.borrowedStatus = borrowedStatus;
	}
	public String getAlldayTime() {
		return alldayTime;
	}
	public void setAlldayTime(String alldayTime) {
		this.alldayTime = alldayTime;
	}
	public String getMornTime() {
		return mornTime;
	}
	public void setMornTime(String mornTime) {
		this.mornTime = mornTime;
	}
	public String getAfterTime() {
		return afterTime;
	}
	public void setAfterTime(String afterTime) {
		this.afterTime = afterTime;
	}
	
	
}
