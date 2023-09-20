package com.huafen.tablet.model.meet;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("会议室信息")
public class MeetRoomInfo implements Serializable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 2204389239188154087L;

	@ApiModelProperty(value="会议室编号")
	private String  meetRoomNum;

	@ApiModelProperty(value="会议室ID")
	private Long meetRoomID;
	
	@ApiModelProperty(value="会议申请ID")
	private Long applyId;
	
	public String getMeetRoomNum() {
		return meetRoomNum;
	}

	public void setMeetRoomNum(String meetRoomNum) {
		this.meetRoomNum = meetRoomNum;
	}

	public Long getMeetRoomID() {
		return meetRoomID;
	}

	public void setMeetRoomID(Long meetRoomID) {
		this.meetRoomID = meetRoomID;
	}
	
	

	
	
	
}
