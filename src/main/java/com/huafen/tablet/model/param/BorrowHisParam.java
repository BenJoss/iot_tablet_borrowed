package com.huafen.tablet.model.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("平板借还首页数据显示查询参数")
public class BorrowHisParam implements Serializable{

	private static final long serialVersionUID = 2928875180457681707L;
	@ApiModelProperty(value="结束天数",required = true,example = "7")
	private int endDayNum;
	@ApiModelProperty(value="显示数量",required = true,example = "30")
	private int showNum;
	
	public int getEndDayNum() {
		return endDayNum;
	}
	public void setEndDayNum(int endDayNum) {
		this.endDayNum = endDayNum;
	}
	public int getShowNum() {
		return showNum;
	}
	public void setShowNum(int showNum) {
		this.showNum = showNum;
	}
	
	
	
}
