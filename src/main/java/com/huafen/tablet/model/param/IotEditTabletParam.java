package com.huafen.tablet.model.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("平板信息查询参数")
public class IotEditTabletParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -900482009424411857L;
	@ApiModelProperty(value="平板ID",example = "E280689400004020F535A17F",required = true)
	private String tabletID;
	
	public String getTabletID() {
		return tabletID;
	}
	public void setTabletID(String tabletID) {
		this.tabletID = tabletID;
	}
	
	
}
