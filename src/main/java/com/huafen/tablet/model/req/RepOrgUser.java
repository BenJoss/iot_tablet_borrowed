package com.huafen.tablet.model.req;

import java.io.Serializable;
import java.util.ArrayList;

import com.huafen.tablet.model.user.OrgUser;

public class RepOrgUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2279405683186965870L;
	
	private Integer code;
	
	private String msg;
	
	private ArrayList<OrgUser>  result;

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

	public ArrayList<OrgUser> getResult() {
		return result;
	}

	public void setResult(ArrayList<OrgUser> result) {
		this.result = result;
	}
	
	

}
