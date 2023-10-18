package com.huafen.tablet.model.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;

@ApiModel("分区参数")
public class PartParam implements Serializable{

	private static final long serialVersionUID = -3538885684120689038L;

    private String partTable;
	
	private String partName;
	
	private String partTime;

	public String getPartTable() {
		return partTable;
	}

	public void setPartTable(String partTable) {
		this.partTable = partTable;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getPartTime() {
		return partTime;
	}

	public void setPartTime(String partTime) {
		this.partTime = partTime;
	}
	
	
}
