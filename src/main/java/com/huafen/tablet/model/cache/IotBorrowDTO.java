package com.huafen.tablet.model.cache;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;

@ApiModel("借还缓存")
public class IotBorrowDTO implements Serializable{

	private static final long serialVersionUID = 6392665830289663065L;

	private int maxNum;
	
	private int minNum;

	public int getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}

	public int getMinNum() {
		return minNum;
	}

	public void setMinNum(int minNum) {
		this.minNum = minNum;
	}

	
	
	
}
