package com.huafen.tablet.service.impl;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huafen.tablet.mapper.CallMapper;
import com.huafen.tablet.model.chat.CallChatDTO;
import com.huafen.tablet.model.chat.CallUser;
import com.huafen.tablet.model.param.CallAuthParam;
import com.huafen.tablet.service.CallChatSaveService;


@Service("callChatSaveService")
public class CallChatSaveServiceImpl implements CallChatSaveService{
	
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(CallChatSaveServiceImpl.class);
	
	@Autowired
	private CallMapper callMapper;
	
	@Override
	public int saveCallChatInfo(CallChatDTO callChat) {
		log.info("保存联调消息");
		try {
			CallUser callUser = callChat.getCallUser();
			if (callUser != null) {
				 String userID = callUser.getUserID();
				 CallAuthParam callAuthParam = new CallAuthParam();
				 callAuthParam.setUserID(userID);
				 if (callMapper.countCallUser(callAuthParam) == 0) {
					 callMapper.insertCallUser(callUser);
				}			   
			}
			callMapper.insertCallChat(callChat);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return 0;
	}

	
	
}
