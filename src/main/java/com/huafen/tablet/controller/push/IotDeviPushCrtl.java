package com.huafen.tablet.controller.push;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huafen.tablet.model.apply.IotBindTabletDTO;
import com.huafen.tablet.model.apply.IotMvBorrHisDTO;
import com.huafen.tablet.model.param.TabletMvParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {"平板借还信息推送"})
@RestController
@RequestMapping("/IotDeviPushCrtl")
public class IotDeviPushCrtl {

	

	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = IotMvBorrHisDTO.class,reference="list"),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "推送平板扫描信息到前端")
    @PostMapping("/pushTabletToTab")
    @ResponseBody
    public IotBindTabletDTO pushIotBindTablet(@RequestBody TabletMvParam tabletMvParam){
    	return new IotBindTabletDTO();
    }
}
