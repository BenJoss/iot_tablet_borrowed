package com.huafen.tablet.controller.his;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huafen.tablet.model.apply.IotMvBorrHisDTO;
import com.huafen.tablet.model.apply.IotTablBorrHisDTO;
import com.huafen.tablet.model.apply.IotTablBorroCode;
import com.huafen.tablet.model.apply.IotTablMvLogDTO;
import com.huafen.tablet.model.his.IotBorrowHisDTO;
import com.huafen.tablet.model.his.IotCurHisAllDTO;
import com.huafen.tablet.model.iot.PageBean;
import com.huafen.tablet.model.meet.CallRmStatDTO;
import com.huafen.tablet.model.param.BorrowHisParam;
import com.huafen.tablet.model.param.IotCurParam;
import com.huafen.tablet.model.param.TabletMvLogParam;
import com.huafen.tablet.model.param.TabletMvParam;
import com.huafen.tablet.model.req.ReposeDTO;
import com.huafen.tablet.service.IoTBrrowoHisSerivce;
import com.huafen.tablet.service.IoTDeviceBorrReturnRecodeSerivce;
import com.huafen.tablet.service.IoTDeviceBorrowCacheSerivce;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {"平板借还历史"})
@RestController
@RequestMapping("/IotDeviHisCrtl")
public class IotDeviHisCrtl {

	@Autowired
	@Qualifier("ioTBrrowoHisSerivce")
	private IoTBrrowoHisSerivce ioTBrrowoHisSerivce;
	
	@Autowired
	@Qualifier("ioTDeviceBorrowCacheSerivce")
	private IoTDeviceBorrowCacheSerivce ioTDeviceBorrowCacheSerivce;
	
	@Autowired
	@Qualifier("ioTDeviceBorrReturnRecodeSerivce")
	private IoTDeviceBorrReturnRecodeSerivce ioTDeviceBorrReturnRecodeSerivce;
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = IotTablBorrHisDTO.class,reference="list"),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "平板借还历史分页查询", notes = "分页查询平板借还历史记录")
    @PostMapping("/queryTabletHis")
    @ResponseBody
    public PageBean<IotTablBorrHisDTO> queryTabletHis(@RequestBody PageBean<IotTablBorrHisDTO> pageBean){
    	return new PageBean<IotTablBorrHisDTO>();
    }
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = IotMvBorrHisDTO.class,reference="list"),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "移动端平板借还历史查询", notes = "查询平板借还历史记录")
    @PostMapping("/queryMvTablet")
    @ResponseBody
    public List<IotMvBorrHisDTO> queryMvTablet(@RequestBody TabletMvParam tabletMvParam){
    	return new ArrayList<IotMvBorrHisDTO>();
    }
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = IotTablMvLogDTO.class,reference="list"),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "移动端申请人平板借还历史查询", notes = "查询平板借还历史记录")
    @PostMapping("/queryMvTabletLog")
    @ResponseBody
    public List<IotTablMvLogDTO> queryMvTabletLog(@RequestBody TabletMvLogParam tabletMvLogParam){
			List<IotTablMvLogDTO>  list = new ArrayList<IotTablMvLogDTO>();
			IotTablMvLogDTO iotTablMvLogDTO = new IotTablMvLogDTO();
			iotTablMvLogDTO.setDescribe("归还10台平板");
			list.add(iotTablMvLogDTO);
			iotTablMvLogDTO = new IotTablMvLogDTO();
			iotTablMvLogDTO.setDescribe("借用绑定的10台平板");
			list.add(iotTablMvLogDTO);
			iotTablMvLogDTO = new IotTablMvLogDTO();
			iotTablMvLogDTO.setDescribe("借用申请的10台平板");
			list.add(iotTablMvLogDTO);
			return new ArrayList<IotTablMvLogDTO>();
    }
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = ReposeDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "平板借还首页当日数据显示", notes = "平板借还首页当日数据显示")
    @PostMapping("/queryCurentHisRetulInfo")
    @ResponseBody
    public ReposeDTO<IotCurHisAllDTO> queryCurentHisRetulInfo(@RequestBody IotCurParam iotCurParam){
    	return ioTDeviceBorrReturnRecodeSerivce.queryCurentHisRetulInfoSerivce(iotCurParam);
    }
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = ReposeDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "平板借还首页数据显示", notes = "查询平板借还首页数据显示")
    @PostMapping("/queryBorrowHisRetultInfo")
    @ResponseBody
    public ReposeDTO<List<IotBorrowHisDTO>> queryBorrowHisRetultInfo(@RequestBody BorrowHisParam borrowHisParam){
    	return ioTBrrowoHisSerivce.queryBorrowHisRetultInfo(borrowHisParam);
    }
	
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "success", response = IotTablBorroCode.class, responseContainer = "List"),
			@ApiResponse(code = 1001, message = "error") })
	@ApiOperation(value = "分页查询平板借历史信息")
	@PostMapping("/queryBorrowInfoPage")
	@ResponseBody
	public PageBean<IotBorrowHisDTO> queryBorrowInfoPage(@RequestBody PageBean<IotBorrowHisDTO> pageBean) {
		return ioTDeviceBorrReturnRecodeSerivce.queryBorrowInfoPageList(pageBean);
	}
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = ReposeDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "查询会议信息", notes = "查询会议信息")
    @GetMapping("/queryBorrowHMeetingInfo")
    @ResponseBody
    public ReposeDTO<List<CallRmStatDTO>> queryBorrowHMeetingInfo(@RequestParam(required = false, name = "meetName") String meetName ){
    	return ioTDeviceBorrowCacheSerivce.queryMeetingInfoByCache(meetName);
    }
}
