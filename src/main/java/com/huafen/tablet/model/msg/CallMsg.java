package com.huafen.tablet.model.msg;

import java.io.Serializable;

import com.huafen.tablet.model.meet.MeetRoomInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("呼叫请求体")
public class CallMsg implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7752477185424565878L;

	@ApiModelProperty(value="会议室信息")
	private MeetRoomInfo meetRoomInfo;
	
	@ApiModelProperty(value="呼叫消息")
	private String callMsgInfo;

	public MeetRoomInfo getMeetRoomInfo() {
		return meetRoomInfo;
	}

	public void setMeetRoomInfo(MeetRoomInfo meetRoomInfo) {
		this.meetRoomInfo = meetRoomInfo;
	}

	public String getCallMsgInfo() {
		return callMsgInfo;
	}

	public void setCallMsgInfo(String callMsgInfo) {
		this.callMsgInfo = callMsgInfo;
	}
	
	
	
	

	
}
