package com.huafen.tablet.model.apply;

import java.io.Serializable;
import java.util.List;

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
	private Integer returnNum;
	@ApiModelProperty(value="借用数量",example = "3")
	private Integer borrowNum;
	@ApiModelProperty(value="归还时间",required = true,example = "2023-09-14 12:00:00")
	private String returnTime;
	@ApiModelProperty(value="创建时间",required = false,example = "2023-09-14 08:00:00")	
	private String createTime;
	@ApiModelProperty(value="扫描设备topic",required = true,example = "A2-206/206-RFID-UP")
	private String topic;
	@ApiModelProperty(value="借出平板信息",required = true,example = "list")
	private List<IotBindTabletDTO> iotBindTabletList;
	
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public int getReturnNum() {
		return returnNum;
	}
	
	public Integer getBorrowNum() {
		return borrowNum;
	}
	public void setBorrowNum(Integer borrowNum) {
		this.borrowNum = borrowNum;
	}
	public String getReturnTime() {
		return returnTime;
	}
	public void setReturnNum(Integer returnNum) {
		this.returnNum = returnNum;
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
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public List<IotBindTabletDTO> getIotBindTabletList() {
		return iotBindTabletList;
	}
	public void setIotBindTabletList(List<IotBindTabletDTO> iotBindTabletList) {
		this.iotBindTabletList = iotBindTabletList;
	}
	
	
}
