package com.huafen.tablet.model.param;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("呼叫历史查询条件")
public class ChatParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1127398954495263520L;
	

	@ApiModelProperty(value="会议室ID",required = true,example = "8646084058906624")
	private String roomID;

	@ApiModelProperty(value="最新时间",required = true,example = "2023-08-07 10:22:54")
	private String lastChatTime;
	
	@ApiModelProperty(value="显示条数",required = true,example = "10")
	private Integer count;

	@ApiModelProperty(value="来源",example = "会议室信息来源:0:会议预约、1:物联系统")
	private String source;
	
	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public String getLastChatTime() {
		return lastChatTime;
	}

	public void setLastChatTime(String lastChatTime) {
		this.lastChatTime = lastChatTime;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}


	

	
	
	
}
