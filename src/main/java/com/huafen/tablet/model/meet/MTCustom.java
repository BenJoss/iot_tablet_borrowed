package com.huafen.tablet.model.meet;

import java.io.Serializable;
import java.util.ArrayList;

public class MTCustom implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7583597233343418640L;
	
	private Long roomId;
	
	private String roomName;

	private ArrayList<MTInfoPO> customerMsgList;

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public ArrayList<MTInfoPO> getCustomerMsgList() {
		return customerMsgList;
	}

	public void setCustomerMsgList(ArrayList<MTInfoPO> customerMsgList) {
		this.customerMsgList = customerMsgList;
	}
	
	
}
