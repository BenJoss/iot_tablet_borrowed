package com.huafen.tablet.service;

import java.util.List;

import com.huafen.tablet.model.chat.CallMsgDTO;
import com.huafen.tablet.model.chat.CallRmLtShowDTO;
import com.huafen.tablet.model.chat.CallRmStatDTO;
import com.huafen.tablet.model.param.CallRmParam;
import com.huafen.tablet.model.param.ChatParam;
import com.huafen.tablet.model.req.RepDTO;

public interface ShowCallMsgService {

	
	public RepDTO queryCallChatList(ChatParam chatParam);
	
	
	public List<CallMsgDTO> queryLargeScreenChatList(ChatParam chatParam);
	
	
	public List<CallRmLtShowDTO> queryRoomDisplayList();
	
	public void sendRoomDisplayList();
	
	public CallRmStatDTO queryCallRmStatInfo(CallRmParam callRmParam);
	
	
}
