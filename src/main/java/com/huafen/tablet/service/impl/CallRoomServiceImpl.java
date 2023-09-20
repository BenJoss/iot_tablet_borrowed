package com.huafen.tablet.service.impl;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huafen.tablet.mapper.CallMapper;
import com.huafen.tablet.model.param.CallMTParam;
import com.huafen.tablet.model.param.ChatParam;
import com.huafen.tablet.model.room.CallMTInfoDAO;
import com.huafen.tablet.model.room.CallRoomDAO;
import com.huafen.tablet.service.CallRoomService;

@Service("callRoomService")
public class CallRoomServiceImpl implements CallRoomService{
	
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(CallRoomServiceImpl.class);
	
	@Autowired
	private CallMapper callMapper;
	
	@Override
	@Transactional(rollbackFor = SQLException.class) 
	public int upCallRoomInfo(CallRoomDAO callRoomDAO) {
		ChatParam chatParam = new ChatParam();
		chatParam.setRoomID(callRoomDAO.getRoomID());
		chatParam.setSource(callRoomDAO.getSource());
		try {
			if(callMapper.countCallRoom(chatParam) > 0) {
				return callMapper.updateCallRoom(callRoomDAO);
			}else {
				return callMapper.insertCallRoom(callRoomDAO);
			}
		} catch (Exception e) {
			log.error("");
		}
		return 0;
	}

	@Override
	@Transactional(rollbackFor = SQLException.class) 
	public int upCallMTInfo(CallMTInfoDAO callMTInfoDAO) {
		CallMTParam callMTParam = new CallMTParam();
		callMTParam.setMeetID(callMTInfoDAO.getMeetID());
		try {
			if(callMapper.countCallMeet(callMTParam) > 0) {
				return callMapper.updateCallMeet(callMTInfoDAO);
			}else {
				return callMapper.insertCallMTInfo(callMTInfoDAO);
			}
		} catch (Exception e) {
			//log.error(e.getMessage());
		}
		return 0;
	}

}
