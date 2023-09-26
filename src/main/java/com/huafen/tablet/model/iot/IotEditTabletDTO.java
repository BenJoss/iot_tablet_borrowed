package com.huafen.tablet.model.iot;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("平板信息维护")
public class IotEditTabletDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3352828408557139979L;
	
	@ApiModelProperty(value="平板名称",example = "平板01",required = false)
	private String tabletName;
	@ApiModelProperty(value="平板型号",example = "A2764",required = false)
	private String tabletModel;
	@ApiModelProperty(value="平板品牌",example = "联想",required = false)
	private String tabletBrand;
	@ApiModelProperty(value="平板IP",example = "172.28.5.141",required  = false )
	private String tabletIP;
	@ApiModelProperty(value="平板端口",example = "17000",required = false)
	private String tabletPort;
	
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

	
}
