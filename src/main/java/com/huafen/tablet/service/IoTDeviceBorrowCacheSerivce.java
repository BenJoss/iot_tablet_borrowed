package com.huafen.tablet.service;

import java.util.List;

import com.huafen.tablet.model.meet.CallRmStatDTO;
import com.huafen.tablet.model.req.ReposeDTO;

public interface IoTDeviceBorrowCacheSerivce {

	public void loadMeetingInfoCache();
	
	public void loadBorrowInfoCache();
	
	public ReposeDTO<List<CallRmStatDTO>> queryMeetingInfoByCache(String meetName);
}
