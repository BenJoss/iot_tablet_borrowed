package com.huafen.tablet.model.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("呼叫消息响应体")
public class CallResp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6853945418912532028L;

	@ApiModelProperty(value="响应码",example = "200")
	private int respCode;
	@ApiModelProperty(value="响应消息",example = "success")
	private String respMsg;
	
	
	public int getRespCode() {
		return respCode;
	}
	public void setRespCode(int respCode) {
		this.respCode = respCode;
	}
	public String getRespMsg() {
		return respMsg;
	}
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	
	
}
