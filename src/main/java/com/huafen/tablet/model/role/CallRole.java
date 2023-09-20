package com.huafen.tablet.model.role;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("呼叫属性信息")
public class CallRole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1322362123909671587L;

	@ApiModelProperty(value="角色编号")
	private Integer roleNum;
	@ApiModelProperty(value="角色名称")
	private String roleName;
	
	public Integer getRoleNum() {
		return roleNum;
	}
	public void setRoleNum(Integer roleNum) {
		this.roleNum = roleNum;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	
}
