package com.huafen.tablet.controller.bind;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huafen.tablet.model.apply.IotBindTabAllDTO;
import com.huafen.tablet.model.apply.IotTablBorroHisDTO;
import com.huafen.tablet.model.param.TabletRevertParam;
import com.huafen.tablet.model.req.RepDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {"平板绑定"})
@RestController
@RequestMapping("/IotBabletBindCrtl")
public class IotBabletBindCrtl {

	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = IotTablBorroHisDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "取平板前通过借还验证码查询预约信息")
    @PostMapping("/queryBindInfo")
    @ResponseBody
    public IotTablBorroHisDTO queryBindInfo(@RequestBody TabletRevertParam tabletRevertParam){
		IotTablBorroHisDTO repDTO = new IotTablBorroHisDTO();
    	return repDTO;
    }
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "取平板绑定操作")
    @PostMapping("/takeBabletBind")
    @ResponseBody
    public RepDTO takeBabletBind(@RequestBody IotBindTabAllDTO iotBindTabAllDTO){
		RepDTO repDTO = new RepDTO();
    	return repDTO;
    }
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = IotTablBorroHisDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "取平板绑定完成后展示结果")
    @PostMapping("/queryBindResult")
    @ResponseBody
    public IotBindTabAllDTO queryBindResult(@RequestBody TabletRevertParam tabletRevertParam){
		IotBindTabAllDTO repDTO = new IotBindTabAllDTO();
    	return repDTO;
    }
}
