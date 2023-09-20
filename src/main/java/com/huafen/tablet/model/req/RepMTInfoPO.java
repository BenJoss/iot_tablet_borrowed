package com.huafen.tablet.model.req;

import java.io.Serializable;
import com.huafen.tablet.model.meet.MTResult;

public class RepMTInfoPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7200830666960073910L;
	
	private Integer code;
	
	private String msg;
	
	private MTResult result;

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

	public MTResult getResult() {
		return result;
	}

	public void setResult(MTResult result) {
		this.result = result;
	}

   
	

}
