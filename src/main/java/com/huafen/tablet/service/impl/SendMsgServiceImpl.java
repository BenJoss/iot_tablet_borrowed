package com.huafen.tablet.service.impl;

import org.springframework.stereotype.Service;

import com.huafen.tablet.model.chat.CallChatDTO;
import com.huafen.tablet.model.req.CallResp;
import com.huafen.tablet.service.SendMsgService;

@Service("sendMsgService")
public class SendMsgServiceImpl implements SendMsgService{

	@Override
	public CallResp sendMsgInfo(CallChatDTO callChatMsg) {
		
		return new CallResp();
	}

	
	
}
