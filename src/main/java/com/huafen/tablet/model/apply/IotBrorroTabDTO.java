package com.huafen.tablet.model.apply;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;

@ApiModel("借用平板数量信息")
public class IotBrorroTabDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 671760542243611308L;

	private Integer allDayNum;
	
	private Integer mornDayNum;
	
	private Integer afterDayNum;

	public Integer getAllDayNum() {
		return allDayNum;
	}

	public void setAllDayNum(Integer allDayNum) {
		this.allDayNum = allDayNum;
	}

	public Integer getMornDayNum() {
		return mornDayNum;
	}

	public void setMornDayNum(Integer mornDayNum) {
		this.mornDayNum = mornDayNum;
	}

	public Integer getAfterDayNum() {
		return afterDayNum;
	}

	public void setAfterDayNum(Integer afterDayNum) {
		this.afterDayNum = afterDayNum;
	}
	
	
}
