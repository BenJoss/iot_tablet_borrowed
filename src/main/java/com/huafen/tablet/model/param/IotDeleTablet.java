package com.huafen.tablet.model.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("平板删除参数")
public class IotDeleTablet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -661592088307366663L;
	@ApiModelProperty(value="平板ID",required = true,example = "E280689400004020F535A17F")
	private String tabletID;
	
	public String getTabletID() {
		return tabletID;
	}
	public void setTabletID(String tabletID) {
		this.tabletID = tabletID;
	}
	
	

}
