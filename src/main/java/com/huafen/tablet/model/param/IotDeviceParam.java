package com.huafen.tablet.model.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("首页会议室发布页绑定设备信息查询参数")
public class IotDeviceParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2980793851545111698L;

	@ApiModelProperty(value="内容ID",example = "3000001504019772",required = true)
	private String contID;

	public String getContID() {
		return contID;
	}

	public void setContID(String contID) {
		this.contID = contID;
	}
	
	

}
