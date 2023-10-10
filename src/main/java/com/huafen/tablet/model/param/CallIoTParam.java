package com.huafen.tablet.model.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "会议室所在楼栋信息",description = "接口入参")
public class CallIoTParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1349550584605915513L;

	@ApiModelProperty(value="会议室所在楼栋编号",required = false,example = "一")
	private String roomBuildNum;

	public String getRoomBuildNum() {
		return roomBuildNum;
	}

	public void setRoomBuildNum(String roomBuildNum) {
		this.roomBuildNum = roomBuildNum;
	} 
	
	
	
}
