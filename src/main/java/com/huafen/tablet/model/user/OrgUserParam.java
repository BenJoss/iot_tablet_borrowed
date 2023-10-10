package com.huafen.tablet.model.user;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class OrgUserParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6284154821270950586L;
	

	@ApiModelProperty(value="员工编号",required = true)
	private String erpbh;
	@ApiModelProperty(value="手机号")
	private String sjhm;
	@ApiModelProperty(value="姓名")
	private String xm;
	public String getErpbh() {
		return erpbh;
	}
	public void setErpbh(String erpbh) {
		this.erpbh = erpbh;
	}
	public String getSjhm() {
		return sjhm;
	}
	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}

	
}
