package com.huafen.tablet.model.param;

import java.io.Serializable;

public class IotBorrowHisParam implements Serializable{

	private static final long serialVersionUID = 5326528624881410974L;

	private int startNum;
	
	private int endNum;
	
	private int limitNum;

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getEndNum() {
		return endNum;
	}

	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}

	public int getLimitNum() {
		return limitNum;
	}

	public void setLimitNum(int limitNum) {
		this.limitNum = limitNum;
	}
	
	
}
