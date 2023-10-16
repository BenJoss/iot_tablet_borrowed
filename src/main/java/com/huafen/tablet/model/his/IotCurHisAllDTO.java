package com.huafen.tablet.model.his;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;

@ApiModel("平板借还首页当日数据汇总显示")
public class IotCurHisAllDTO implements Serializable{

	private static final long serialVersionUID = 3471077019505123243L;

	private int alldayNum;
	
	private int mornNum;
	
	private int afterNum;
	
	private List<IotCurentHisDTO> data;

	public int getAlldayNum() {
		return alldayNum;
	}

	public void setAlldayNum(int alldayNum) {
		this.alldayNum = alldayNum;
	}

	public int getMornNum() {
		return mornNum;
	}

	public void setMornNum(int mornNum) {
		this.mornNum = mornNum;
	}

	public int getAfterNum() {
		return afterNum;
	}

	public void setAfterNum(int afterNum) {
		this.afterNum = afterNum;
	}

	public List<IotCurentHisDTO> getData() {
		return data;
	}

	public void setData(List<IotCurentHisDTO> data) {
		this.data = data;
	}
	
	
}
