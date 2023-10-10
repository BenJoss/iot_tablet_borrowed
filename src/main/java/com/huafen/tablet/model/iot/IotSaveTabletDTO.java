package com.huafen.tablet.model.iot;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("平板信息新增")
public class IotSaveTabletDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9065202566671762652L;
	@ApiModelProperty(value="平板ID",example = "E280689400004020F535A17F",required = true)
	private String tabletID;
	@ApiModelProperty(value="平板名称",example = "平板01",required = true)
	private String tabletName;
	@ApiModelProperty(value="平板型号",example = "A2764",required = true)
	private String tabletModel;
	@ApiModelProperty(value="平板品牌",example = "联想",required = true)
	private String tabletBrand;
	@ApiModelProperty(value="平板IP",example = "172.28.5.141",required  = true )
	private String tabletIP;
	@ApiModelProperty(value="平板端口",example = "17000",required = true)
	private String tabletPort;
	@ApiModelProperty(value="平板状态",example = "1：启用、2：禁用")
	private String tabletState;
	@ApiModelProperty(value="平板借用状态",example = "1：使用中、2：空闲")
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
	
	
}
