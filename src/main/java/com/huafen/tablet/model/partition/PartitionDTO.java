package com.huafen.tablet.model.partition;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;

@ApiModel("分区参数")
public class PartitionDTO implements Serializable{

	private static final long serialVersionUID = -6272801538558687094L;

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
