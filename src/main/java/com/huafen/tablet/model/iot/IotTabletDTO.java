package com.huafen.tablet.model.iot;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("平板基本信息")
public class IotTabletDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5892395750544073025L;
	@ApiModelProperty(value="平板ID",example = "E280689400004020F535A17F")
	private String tabletID;
	@ApiModelProperty(value="平板名称",example = "平板01")
	private String tabletName;
	@ApiModelProperty(value="平板型号",example = "A2764")
	private String tabletModel;
	@ApiModelProperty(value="平板品牌",example = "联想")
	private String tabletBrand;
	@ApiModelProperty(value="平板IP",example = "172.28.5.141")
	private String tabletIP;
	@ApiModelProperty(value="平板端口",example = "17000")
	private String tabletPort;
	@ApiModelProperty(value="平板状态",example = "1：启用、2：禁用")
	private String tabletState;
	@ApiModelProperty(value="平板借用状态",example = "1：使用中、2：空闲")
	private String borrowedStatus;
	@ApiModelProperty(value="平板顺序",example = "1")
	private int tabletOrder;
	@ApiModelProperty(value="借还验证码",example = "8484")
	private String verifyCode;
	
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
	public String getTabletModel() {
		return tabletModel;
	}
	public void setTabletModel(String tabletModel) {
		this.tabletModel = tabletModel;
	}
	public String getTabletBrand() {
		return tabletBrand;
	}
	public void setTabletBrand(String tabletBrand) {
		this.tabletBrand = tabletBrand;
	}
	public String getTabletIP() {
		return tabletIP;
	}
	public void setTabletIP(String tabletIP) {
		this.tabletIP = tabletIP;
	}
	public String getTabletPort() {
		return tabletPort;
	}
	public void setTabletPort(String tabletPort) {
		this.tabletPort = tabletPort;
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
	public int getTabletOrder() {
		return tabletOrder;
	}
	public void setTabletOrder(int tabletOrder) {
		this.tabletOrder = tabletOrder;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	
	
}
