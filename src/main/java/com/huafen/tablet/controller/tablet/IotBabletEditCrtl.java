package com.huafen.tablet.controller.tablet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huafen.tablet.model.iot.IotEditTabletDTO;
import com.huafen.tablet.model.iot.IotSaveTabletDTO;
import com.huafen.tablet.model.param.TabletMageParam;
import com.huafen.tablet.model.req.RepDTO;
import com.huafen.tablet.service.IotBabletEditService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {"平板信息维护"})
@RestController
@RequestMapping("/IotBabletEditCrtl")
public class IotBabletEditCrtl {

	@Autowired
	@Qualifier("iotBabletEditService")
	private IotBabletEditService iotBabletEditService;
	
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "平板编辑信息查询")
    @PostMapping("/queryMageBablet")
    @ResponseBody
    public RepDTO queryMageBablet(@RequestBody TabletMageParam tabletMageParam){
    	return iotBabletEditService.queryMageBabletSerivce(tabletMageParam);
    }
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "平板信息编辑")
    @PostMapping("/editBablet")
    @ResponseBody
    public RepDTO editBablet(@RequestBody IotEditTabletDTO iotEditTabletDTO){
    	return iotBabletEditService.editBabletSerivce(iotEditTabletDTO);
    }
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "平板信息新增")
    @PostMapping("/saveBablet")
    @ResponseBody
    public RepDTO saveBablet(@RequestBody IotSaveTabletDTO iotSaveTabletDTO){
    	return iotBabletEditService.saveBabletSerivce(iotSaveTabletDTO);
    }
}
