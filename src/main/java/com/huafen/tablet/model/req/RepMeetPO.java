package com.huafen.tablet.model.req;

import java.io.Serializable;
import java.util.ArrayList;

import com.huafen.tablet.model.meet.FloorPO;

public class RepMeetPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4581831434987646444L;

	private Integer code;
	
	private String msg;
	
	private ArrayList<FloorPO>  result;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ArrayList<FloorPO> getResult() {
		return result;
	}

	public void setResult(ArrayList<FloorPO> result) {
		this.result = result;
	}

	
	
	
}
