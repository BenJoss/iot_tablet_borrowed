package com.huafen.tablet.controller.bind;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huafen.tablet.config.RepCode;
import com.huafen.tablet.model.apply.IotBindTabAllDTO;
import com.huafen.tablet.model.apply.IotBorroFlowDTO;
import com.huafen.tablet.model.apply.IotTablBorroHisDTO;
import com.huafen.tablet.model.param.TabletRevertParam;
import com.huafen.tablet.model.req.RepDTO;
import com.huafen.tablet.service.IoTDeviceBindSerivce;
import com.huafen.tablet.service.IoTDeviceTopicSerivce;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {"平板绑定"})
@RestController
@RequestMapping("/IotBabletBindCrtl")
public class IotBabletBindCrtl {

	@Autowired
	@Qualifier("ioTDeviceBindSerivce")
	private IoTDeviceBindSerivce ioTDeviceBindSerivce;
	
	@Autowired
	@Qualifier("ioTDeviceTopicSerivce")
	private IoTDeviceTopicSerivce ioTDeviceTopicSerivce;
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "取平板前通过借还验证码查询预约信息")
    @PostMapping("/queryBindInfo")
    @ResponseBody
    public RepDTO queryBindInfo(@RequestBody TabletRevertParam tabletRevertParam){
		return ioTDeviceBindSerivce.queryBindInfoSerivce(tabletRevertParam);
    }
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "校验借还验证码信息")
    @PostMapping("/checkBindInfo")
    @ResponseBody
    public RepDTO checkBindInfo(@RequestBody TabletRevertParam tabletRevertParam){
		return ioTDeviceBindSerivce.checkBindInfoSerivce(tabletRevertParam);
    }
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "启动借取流程")
    @PostMapping("/startBindBorroFlow")
    @ResponseBody
	public RepDTO startBindBorroFlow(@RequestBody IotBorroFlowDTO iotBorroFlowDTO) {
		ioTDeviceTopicSerivce.OpenScannDevice(iotBorroFlowDTO);
		ioTDeviceBindSerivce.bindDeviceTopicRedisCahce(iotBorroFlowDTO);
		RepDTO repDTO = new RepDTO();
		repDTO.setRepCode(RepCode.SUCCESS_CODE);
		
		return repDTO;
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
    @ApiOperation(value = "取平板绑定操作")
    @PostMapping("/takeBabletBind")
    @ResponseBody
    public RepDTO takeBabletBind(@RequestBody IotBindTabAllDTO iotBindTabAllDTO){
		RepDTO repDTO = new RepDTO();
		ioTDeviceBindSerivce.bindIotTablBorroInfo(iotBindTabAllDTO);
		repDTO.setRepCode(RepCode.SUCCESS_CODE);
    	return repDTO;
    }
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "重置借取流程")
    @PostMapping("/resetDeviceBind")
    @ResponseBody
    public RepDTO resetDeviceBind(@RequestBody IotBorroFlowDTO iotBorroFlowDTO){
    	return ioTDeviceBindSerivce.resetBindDeviceInfoSerivce(iotBorroFlowDTO);
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
