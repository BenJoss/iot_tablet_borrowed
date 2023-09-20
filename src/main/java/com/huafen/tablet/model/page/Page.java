package com.huafen.tablet.model.page;

import java.util.List;

import com.huafen.tablet.model.chat.CallMsgDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("分页组件")
public class Page{

	@ApiModelProperty(value="数据")
	private List<CallMsgDTO> data;
	
	public List<CallMsgDTO> getData() {
		return data;
	}
	public void setData(List<CallMsgDTO> data) {
		this.data = data;
	}
	
	
	
	
	
	
	
}
