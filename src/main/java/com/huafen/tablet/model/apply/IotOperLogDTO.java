package com.huafen.tablet.model.apply;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("设备借出日志记录")
public class IotOperLogDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2821431304445283650L;

	@ApiModelProperty(value="操作日志",example = "8484",required = true)
	private String operateId;
	@ApiModelProperty(value="操作类型",example = "借出",required = true)
	private String operateType;
	@ApiModelProperty(value="操作内容",example = "借出10台",required = true)
	private String operateCont;
	public String getOperateId() {
		return operateId;
	}
	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public String getOperateCont() {
		return operateCont;
	}
	public void setOperateCont(String operateCont) {
		this.operateCont = operateCont;
	}
	
	
}
