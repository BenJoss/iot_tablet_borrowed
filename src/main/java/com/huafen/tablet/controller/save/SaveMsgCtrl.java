package com.huafen.tablet.controller.save;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huafen.tablet.model.chat.CallChat;
import com.huafen.tablet.model.req.CallResp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {"呼叫消息保存接口"})
@RestController
@RequestMapping("/SaveMsgCtrl")
public class SaveMsgCtrl {

    @ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success"),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "呼叫信息", notes = "呼叫信息保存")
    @PostMapping("/sendMsg")
	public CallResp  saveCallChatMsg(@RequestBody CallChat callChat) {
		return new CallResp();
	}
}
