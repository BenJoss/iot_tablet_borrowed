package com.huafen.tablet.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.huafen.tablet.mapper.CallMapper;
import com.huafen.tablet.model.chat.CallMsgDTO;
import com.huafen.tablet.model.chat.CallRmLtShowDTO;
import com.huafen.tablet.model.chat.CallRmStatDTO;
import com.huafen.tablet.model.param.CallMTParam;
import com.huafen.tablet.model.param.CallRmParam;
import com.huafen.tablet.model.param.ChatParam;
import com.huafen.tablet.model.req.RepDTO;
import com.huafen.tablet.model.room.CallRoomDAO;
import com.huafen.tablet.service.ShowCallMsgService;
import com.huafen.tablet.util.CallRMUtil;
import com.huafen.tablet.websocket.WSIotServer;

@Service("showCallMsgService")
public class ShowCallMsgServiceImpl implements ShowCallMsgService{
	
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(ShowCallMsgServiceImpl.class);
	
	@Autowired
	private CallMapper callMapper;

	@Autowired
	private WSIotServer webSocket;
	
	@Override
	public RepDTO queryCallChatList(ChatParam chatParam) {
		List<CallMsgDTO> callChatList = callMapper.queryCallChatList(chatParam);
		RepDTO repDTO= new RepDTO();
		repDTO.setResult(callChatList);
		return repDTO;
	}

	@Override
	public List<CallMsgDTO> queryLargeScreenChatList(ChatParam chatParam) {
		try {
			List<CallMsgDTO> list = callMapper.queryCallChatList(chatParam);
			if (!list.isEmpty()) {
				list.sort(Comparator.comparing(CallMsgDTO::getCreateTime));
			}
			return list;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return new ArrayList<CallMsgDTO>();
	}

	@Override
	public List<CallRmLtShowDTO> queryRoomDisplayList() {
		try {
			CallMTParam callMTParam = new CallMTParam();
			callMTParam.setSource(CallRMUtil.MT_ROOM_SOURCE);
			List<CallRoomDAO> callRoomList = callMapper.queryCallRoomList(callMTParam);
			if(callRoomList != null) {
				List<CallRmLtShowDTO> list = new ArrayList<CallRmLtShowDTO>();
				for(CallRoomDAO item: callRoomList) {
					ChatParam chatParam = new ChatParam();
					chatParam.setRoomID(item.getRoomID());
					CallRmLtShowDTO callRmLtShow = callMapper.queryCallRoomChatList(chatParam);
					if (callRmLtShow != null) {
						list.add(callRmLtShow);
					}else {
						 callRmLtShow = new CallRmLtShowDTO();
						 callRmLtShow.setCreateTime(Long.valueOf("0"));
						 callRmLtShow.setRoomID(item.getRoomID());
						 callRmLtShow.setRoomName(item.getRoomName());
						 list.add(callRmLtShow);
					}
				}
				if (!list.isEmpty()) {
					list.sort(Comparator.comparing(CallRmLtShowDTO::getCreateTime).reversed());
				}
				return list;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return new ArrayList<CallRmLtShowDTO>();
	}

	@Override
	public CallRmStatDTO queryCallRmStatInfo(CallRmParam callRmParam) {
		try {
			return callMapper.queryCallRmStatInfo(callRmParam);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return new CallRmStatDTO();
	}

	@Override
	public void sendRoomDisplayList() {
       try {	
    	    Map<String, Object> resultMap = new HashMap<String, Object>();
    	    List<CallRmLtShowDTO>  callRmLtShowList =  this.queryRoomDisplayList();
    	    resultMap.put(CallRMUtil.WS_TYPE, CallRMUtil.ROOM_DISPLAY_LIST);
    	    resultMap.put(CallRMUtil.RESULT, callRmLtShowList);
		    String message = JSON.toJSONString(resultMap);
		    webSocket.sendAllMessage(message);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
	}

	
	
	
	
}
