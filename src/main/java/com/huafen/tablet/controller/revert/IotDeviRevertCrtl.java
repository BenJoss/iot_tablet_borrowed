package com.huafen.tablet.controller.revert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huafen.tablet.config.RepCode;
import com.huafen.tablet.model.apply.IotBorroFlowDTO;
import com.huafen.tablet.model.apply.IotDeviReturnDTO;
import com.huafen.tablet.model.param.TabletMageParam;
import com.huafen.tablet.model.param.TabletRevertParam;
import com.huafen.tablet.model.req.RepDTO;
import com.huafen.tablet.service.IoTDeviceReturnSerivce;
import com.huafen.tablet.service.IoTDeviceTopicSerivce;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {"平板归还接口"})
@RestController
@RequestMapping("/IotDeviRevertCrtl")
public class IotDeviRevertCrtl {
	
	@Autowired
	@Qualifier("ioTDeviceTopicSerivce")
	private IoTDeviceTopicSerivce ioTDeviceTopicSerivce;
	
	@Autowired
	@Qualifier("ioTDeviceReturnSerivce")
	private IoTDeviceReturnSerivce ioTDeviceReturnSerivce;
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "校验借还验证码信息")
    @PostMapping("/checkReturnInfo")
    @ResponseBody
    public RepDTO checkReturnInfo(@RequestBody TabletRevertParam tabletRevertParam){
    	return ioTDeviceReturnSerivce.checkReturnInfoSerivce(tabletRevertParam);
    }
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "根据验证码查询申请借出的平板信息")
    @PostMapping("/queryTabletRevert")
    @ResponseBody
    public RepDTO queryTabletRevert(@RequestBody TabletRevertParam tabletRevertParam){
    	return ioTDeviceReturnSerivce.queryReturnInfoSerivce(tabletRevertParam);
    }
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "根据验证码借出的平板信息")
    @PostMapping("/queryRevertTabletInfo")
    @ResponseBody
    public RepDTO queryRevertTabletInfo(@RequestBody TabletMageParam tabletRevertParam){
    	return ioTDeviceReturnSerivce.queryTabletInfoSerivce(tabletRevertParam);
    }
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "启动归还流程")
    @PostMapping("/startBindBorroFlow")
    @ResponseBody
	public RepDTO startBindBorroFlow(@RequestBody IotBorroFlowDTO iotBorroFlowDTO) {
		ioTDeviceTopicSerivce.OpenScannDevice(iotBorroFlowDTO);
		ioTDeviceReturnSerivce.bindDeviceTopicRedisCahce(iotBorroFlowDTO);
		RepDTO repDTO = new RepDTO();
		repDTO.setRepCode(RepCode.SUCCESS_CODE);
		return repDTO;
	}
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "重置归还流程")
    @PostMapping("/resetDeviceBind")
    @ResponseBody
	public RepDTO resetDeviceBind(@RequestBody IotBorroFlowDTO iotBorroFlowDTO) {
	   return	ioTDeviceReturnSerivce.resetDeviceBindInfo(iotBorroFlowDTO);
	}
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "关闭设备")
    @PostMapping("/closeScannDevice")
    @ResponseBody
	public RepDTO closeScannDevice(@RequestBody IotBorroFlowDTO iotBorroFlowDTO) {
		ioTDeviceTopicSerivce.CloseScannDevice(iotBorroFlowDTO);
		RepDTO repDTO = new RepDTO();
		repDTO.setRepCode(RepCode.SUCCESS_CODE);
		return repDTO;
	}
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "借用平板归还", notes = "新增平板借用归还记录")
    @PostMapping("/returnTablet")
    @ResponseBody
    public RepDTO returnTablet(@RequestBody IotDeviReturnDTO iotDeviReturnDTO){
    	return ioTDeviceReturnSerivce.returnIotTablBorroInfo(iotDeviReturnDTO);
    }
}
