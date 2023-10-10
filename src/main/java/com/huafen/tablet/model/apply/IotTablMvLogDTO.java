package com.huafen.tablet.model.apply;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("移动端申请人平板借还记录")
public class IotTablMvLogDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5811809819435670690L;
	@ApiModelProperty(value="借还记录",example = "归还10台平板")
	private String describe;
	
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	
}
