package com.huafen.tablet.controller.tablet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huafen.tablet.model.iot.IotEditTabletDTO;
import com.huafen.tablet.model.iot.IotSaveTabletDTO;
import com.huafen.tablet.model.iot.IotTabletDTO;
import com.huafen.tablet.model.param.TabletMageParam;
import com.huafen.tablet.model.req.RepDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {"平板信息维护"})
@RestController
@RequestMapping("/IotBabletEditCrtl")
public class IotBabletEditCrtl {

	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "平板编辑信息查询")
    @PostMapping("/queryMageBablet")
    @ResponseBody
    public List<IotTabletDTO> queryMageBablet(@RequestBody TabletMageParam tabletMageParam){
		List<IotTabletDTO> repDTO = new ArrayList<IotTabletDTO>();
    	return repDTO;
    }
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "平板信息编辑")
    @PostMapping("/editBablet")
    @ResponseBody
    public RepDTO editBablet(@RequestBody IotEditTabletDTO iotEditTabletDTO){
		RepDTO repDTO = new RepDTO();
    	return repDTO;
    }
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "平板信息新增")
    @PostMapping("/saveBablet")
    @ResponseBody
    public RepDTO saveBablet(@RequestBody IotSaveTabletDTO iotSaveTabletDTO){
		RepDTO repDTO = new RepDTO();
    	return repDTO;
    }
}
