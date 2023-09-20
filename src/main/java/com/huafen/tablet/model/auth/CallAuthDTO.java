package com.huafen.tablet.model.auth;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value= "呼叫权限信息",description ="呼叫权限信息")
public class CallAuthDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5553499209683030266L;

	@ApiModelProperty(value="权限编号",required = true,example = "1001")
	private String authCode;
	@ApiModelProperty(value="权限名称",required = true,example = "开锁权限")
	private String authName;
	@ApiModelProperty(value="权限类型",required = true,example = "操作权限")
	private String authType;
	@ApiModelProperty(value="父权限编号",required = false,example = "1000")
	private String parentAuthCode;
	@ApiModelProperty(value="权限状态",required = true,example = "10:新增;11:修改;12:删除")
	private String state;
	
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public String getAuthName() {
		return authName;
	}
	public void setAuthName(String authName) {
		this.authName = authName;
	}
	public String getAuthType() {
		return authType;
	}
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	public String getParentAuthCode() {
		return parentAuthCode;
	}
	public void setParentAuthCode(String parentAuthCode) {
		this.parentAuthCode = parentAuthCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	
}
