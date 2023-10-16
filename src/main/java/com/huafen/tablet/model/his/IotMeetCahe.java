package com.huafen.tablet.model.his;

import java.io.Serializable;
import java.util.List;

import com.huafen.tablet.model.meet.CallRmStatDTO;

public class IotMeetCahe implements Serializable{

	private static final long serialVersionUID = 8610387716802321534L;

	private List<CallRmStatDTO> callRmStatList;

	public List<CallRmStatDTO> getCallRmStatList() {
		return callRmStatList;
	}

	public void setCallRmStatList(List<CallRmStatDTO> callRmStatList) {
		this.callRmStatList = callRmStatList;
	}
	
	
}
