package com.huafen.tablet.controller.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huafen.tablet.model.auth.CallAuthDTO;
import com.huafen.tablet.model.param.CallAuthParam;
import com.huafen.tablet.model.req.CallResp;
import com.huafen.tablet.service.CallAuthService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {"呼叫权限相关接口"})
@RestController
@RequestMapping("/CallAuthCrtl")
public class CallAuthCrtl {

	@Autowired
	@Qualifier("callAuthService")
	private CallAuthService callAuthService;
	
	    @ApiResponses( value = { 
				@ApiResponse(code = 200, message = "success"),
				@ApiResponse(code = 1001, message = "error")})
	    @ApiOperation(value = "权限新增", notes = "权限配置保存")
	    @PostMapping("/savecallauth")
		public CallResp  saveCallAuthInfo(@RequestBody CallAuthDTO callAuthDTO) {
			return new CallResp();
		}
	    
	    @ApiResponses( value = { 
				@ApiResponse(code = 200, message = "success"),
				@ApiResponse(code = 1001, message = "error")})
	    @ApiOperation(value = "权限修改", notes = "权限配置保存")
	    @PostMapping("/modifycallauth")
	    public CallResp  modifyCallAuthInfo(@RequestBody CallAuthDTO callAuthDTO) {
			return new CallResp();
		}
	    
	    @ApiResponses( value = { 
				@ApiResponse(code = 200, message = "success"),
				@ApiResponse(code = 1001, message = "error")})
	    @ApiOperation(value = "权限查询", notes = "根据用户id查询用户权限")
	    @PostMapping("/querycallauth")
	    public List<CallAuthDTO> queryCallAuthInfo(@RequestBody CallAuthParam callAuthParam){
	    	List<CallAuthDTO> list = new  ArrayList<CallAuthDTO>();
	    	CallAuthDTO item = new CallAuthDTO();
	    	list.add(item);
	    	return list;
	    }
}
