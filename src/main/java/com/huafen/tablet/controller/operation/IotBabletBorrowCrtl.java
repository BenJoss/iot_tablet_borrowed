package com.huafen.tablet.controller.operation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huafen.tablet.model.iot.IotBorRetuDTO;
import com.huafen.tablet.model.iot.IotTabletDTO;
import com.huafen.tablet.model.param.TabletRevertParam;
import com.huafen.tablet.model.req.RepDTO;
import com.huafen.tablet.service.IoTDeviceBorrReturnRecodeSerivce;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {"平板借还操作记录"})
@RestController
@RequestMapping("/IotBabletBorrowCrtl")
public class IotBabletBorrowCrtl {

	@Autowired
	@Qualifier("ioTDeviceBorrReturnRecodeSerivce")
	private IoTDeviceBorrReturnRecodeSerivce ioTDeviceBorrReturnRecodeSerivce;
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "记录绑定的平板信息")
    @PostMapping("/saveDeviceBorrReturn")
    @ResponseBody
    public RepDTO saveDeviceBorrReturn(@RequestBody List<IotBorRetuDTO> iotBorRetuList){
		return ioTDeviceBorrReturnRecodeSerivce.saveDeviceBorrReturnRecodes(iotBorRetuList);
    }
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "查询记录绑定的平板信息")
    @PostMapping("/queryBorrowRetultInfo")
    @ResponseBody
    public RepDTO queryBorrowRetultInfo(@RequestBody TabletRevertParam tabletRevertParam){
		return ioTDeviceBorrReturnRecodeSerivce.queryBorrowRetultInfoSerivce(tabletRevertParam);
    }
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "更新绑定的平板信息")
    @PostMapping("/updateBorroReturnOper")
    @ResponseBody
    public RepDTO updateBorroReturnOper(@RequestBody List<IotTabletDTO> iotTabletList){
		return ioTDeviceBorrReturnRecodeSerivce.updateBorroReturnOperLog(iotTabletList);
    }
	
}
