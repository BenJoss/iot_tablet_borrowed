package com.huafen.tablet.controller.revert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huafen.tablet.model.iot.IotTabletDTO;
import com.huafen.tablet.model.param.TabletRevertParam;
import com.huafen.tablet.model.req.RepDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {"平板归还接口"})
@RestController
@RequestMapping("/IotDeviRevertCrtl")
public class IotDeviRevertCrtl {

	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "根据验证码查询申请借出的平板信息")
    @PostMapping("/queryTabletRevert")
    @ResponseBody
    public List<IotTabletDTO> queryTabletRevert(@RequestBody TabletRevertParam tabletRevertParam){
    	return new ArrayList<IotTabletDTO>();
    }
	
}
