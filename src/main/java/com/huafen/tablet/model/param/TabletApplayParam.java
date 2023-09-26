package com.huafen.tablet.model.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("获取申请时间内的平板借用数量查询参数")
public class TabletApplayParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7961037908275640837L;
	
	@ApiModelProperty(value="全天",example = "2023-09-26 00:00:00")
	private String alldayTime;
	@ApiModelProperty(value="上午",example = "2023-09-26 08:00:00")
	private String mornTime;
	@ApiModelProperty(value="下午",example = "2023-09-26 14:00:00")
	private String afterTime;
	
	public String getAlldayTime() {
		return alldayTime;
	}
	public void setAlldayTime(String alldayTime) {
		this.alldayTime = alldayTime;
	}
	public String getMornTime() {
		return mornTime;
	}
	public void setMornTime(String mornTime) {
		this.mornTime = mornTime;
	}
	public String getAfterTime() {
		return afterTime;
	}
	public void setAfterTime(String afterTime) {
		this.afterTime = afterTime;
	}
	
	

}
