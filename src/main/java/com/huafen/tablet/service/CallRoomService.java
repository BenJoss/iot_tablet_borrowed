package com.huafen.tablet.service;

import com.huafen.tablet.model.room.CallMTInfoDAO;
import com.huafen.tablet.model.room.CallRoomDAO;

public interface CallRoomService {

	public int upCallRoomInfo(CallRoomDAO callRoomDAO);
	
	
	public int upCallMTInfo(CallMTInfoDAO callMTInfoDAO);
}
