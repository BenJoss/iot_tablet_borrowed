package com.huafen.tablet.model.user;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("组织中心用户信息")
public class OrgUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4820623703109265380L;

	@ApiModelProperty(value="姓名")
	private String xm;
	
	@ApiModelProperty(value="员工编号")
	private String erpbh;

	@ApiModelProperty(value="组织编码")
	private String orgeh;
	

	@ApiModelProperty(value="组织名称")
	private String orgehName;


	public String getXm() {
		return xm;
	}


	public void setXm(String xm) {
		this.xm = xm;
	}


	public String getErpbh() {
		return erpbh;
	}


	public void setErpbh(String erpbh) {
		this.erpbh = erpbh;
	}

	
	public String getOrgeh() {
		return orgeh;
	}


	public void setOrgeh(String orgeh) {
		this.orgeh = orgeh;
	}


	public String getOrgehName() {
		return orgehName;
	}


	public void setOrgehName(String orgehName) {
		this.orgehName = orgehName;
	}
	
	
    
 
    
	
}
