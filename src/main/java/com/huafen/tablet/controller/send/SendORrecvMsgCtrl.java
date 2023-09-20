package com.huafen.tablet.controller.send;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huafen.tablet.model.chat.CallChatDTO;
import com.huafen.tablet.model.req.CallResp;
import com.huafen.tablet.service.SendMsgService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(tags = {"呼叫消息相关接口"})
@RestController
@RequestMapping("/SendMsgCtrl")
public class SendORrecvMsgCtrl {

	@Autowired
	@Qualifier("sendMsgService")
	private SendMsgService sendMsgService;
	
    @ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success"),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "发送呼叫信息", notes = "ws推送消息")
    @PostMapping("/sendORrecvMsg")
	public CallResp  sendORrecvMsg(@RequestBody CallChatDTO callChatMsg) {
		return sendMsgService.sendMsgInfo(callChatMsg);
	}

}