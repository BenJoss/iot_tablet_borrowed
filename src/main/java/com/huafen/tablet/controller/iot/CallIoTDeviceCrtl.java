package com.huafen.tablet.controller.iot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huafen.tablet.model.iot.CallIotFloor;
import com.huafen.tablet.model.param.CallIoTParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(tags = {"物联状态相关接口"})
@RestController
@RequestMapping("/CallIoTDeviceCrtl")
public class CallIoTDeviceCrtl {


    @ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success"),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "显示该栋所有楼层会议室物联信息", notes = "根据楼栋号查询缓存中的会议室物联信息")
    @PostMapping("/showCallIotFloorList")
    @ResponseBody
    public List<CallIotFloor> showCallIotFloorList(@RequestBody CallIoTParam callIoTParam){
    	List<CallIotFloor>  list = new ArrayList<CallIotFloor>();
    	CallIotFloor item = new CallIotFloor(); 
    	list.add(item);
    	return list;
    }
	
}
