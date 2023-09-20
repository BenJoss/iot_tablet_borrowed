package com.huafen.tablet.service;

import com.huafen.tablet.model.chat.CallChatDTO;
import com.huafen.tablet.model.req.CallResp;

public interface SendMsgService {

	public CallResp sendMsgInfo(CallChatDTO callChatMsg);
}
