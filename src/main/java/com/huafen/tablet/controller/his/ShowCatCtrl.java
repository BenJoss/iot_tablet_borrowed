package com.huafen.tablet.controller.his;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huafen.tablet.model.chat.CallMsgDTO;
import com.huafen.tablet.model.chat.CallRmLtShowDTO;
import com.huafen.tablet.model.chat.CallRmStatDTO;
import com.huafen.tablet.model.param.CallRmParam;
import com.huafen.tablet.model.param.ChatParam;
import com.huafen.tablet.service.ShowCallMsgService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {"显示呼叫消息相关接口"})
@RestController
@RequestMapping("/ShowCatCtrl")
public class ShowCatCtrl {

	@Autowired
	@Qualifier("showCallMsgService")
	private ShowCallMsgService showCallMsgService ;
	
	
    @ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = CallMsgDTO.class,responseContainer = "List"),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "显示大屏呼叫历史记录", notes = "分页查询大屏呼叫历史记录")
    @PostMapping("/showLgScrnChatHis")
    @ResponseBody
    public List<CallMsgDTO> showCallChatHisInfo(@RequestBody ChatParam chatParam) {
    	
		return showCallMsgService.queryLargeScreenChatList(chatParam);
	}
    
    @ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = CallRmLtShowDTO.class,responseContainer = "List"),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "大屏显示会议室最新消息列表", notes = "查询会议室最新消息列表")
    @PostMapping("/showRoomDisplayList")
    @ResponseBody
    public List<CallRmLtShowDTO> queryRoomDisplayList(){
    	return showCallMsgService.queryRoomDisplayList();
    }
    
    @ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = CallRmStatDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "查询会议室会议状态", notes = "查询会议室会议状态")
    @PostMapping("/queryCallRmStatInfo")
    @ResponseBody
    public CallRmStatDTO queryCallRmStatInfo(@RequestBody CallRmParam callRmParam){
          
    	return	showCallMsgService.queryCallRmStatInfo(callRmParam);
    	
    }
}
