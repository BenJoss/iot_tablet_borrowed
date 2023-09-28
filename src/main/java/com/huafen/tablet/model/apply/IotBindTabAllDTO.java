package com.huafen.tablet.model.apply;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("取平板录入信息")
public class IotBindTabAllDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8950319244625073362L;
	@ApiModelProperty(value="借还验证码",required = true,example = "8484")
	private String verifyCode;
	@ApiModelProperty(value="扫描设备topic",required = true,example = "A2-206/206-RFID-UP")
	private String topic;
	@ApiModelProperty(value="取出平板信息",required = true,example = "list")
	private List<IotBindTabletDTO> iotBindTabletList;
	
	private int bindNum;
	
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public List<IotBindTabletDTO> getIotBindTabletList() {
		return iotBindTabletList;
	}
	public void setIotBindTabletList(List<IotBindTabletDTO> iotBindTabletList) {
		this.iotBindTabletList = iotBindTabletList;
	}
	public int getBindNum() {
		return bindNum;
	}
	public void setBindNum(int bindNum) {
		this.bindNum = bindNum;
	}
	
}
