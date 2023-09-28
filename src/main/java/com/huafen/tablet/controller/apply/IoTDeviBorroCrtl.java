package com.huafen.tablet.controller.apply;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huafen.tablet.config.RepCode;
import com.huafen.tablet.model.apply.IotDeviReturnDTO;
import com.huafen.tablet.model.apply.IotTablBorroDTO;
import com.huafen.tablet.model.apply.IotTableCancleDTO;
import com.huafen.tablet.model.chat.CallRmStatDTO;
import com.huafen.tablet.model.param.IotBabletMntParam;
import com.huafen.tablet.model.req.RepDTO;
import com.huafen.tablet.msg.DeviceException;
import com.huafen.tablet.service.IoTDeviBorroSerivce;
import com.huafen.tablet.util.IoTDevUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {"平板借还相关"})
@RestController
@RequestMapping("/IoTDeviBorroCrtl")
public class IoTDeviBorroCrtl {

	@Autowired
	@Qualifier("ioTDeviBorroSerivce")
	private IoTDeviBorroSerivce ioTDeviBorroSerivce;
	
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "申请借用平板", notes = "新增平板借用申请记录")
    @PostMapping("/applyTablet")
    @ResponseBody
    public RepDTO applyTablet(@RequestBody IotTablBorroDTO iotTablBorroDTO){
		RepDTO repDTO = new RepDTO();
		try {
			String startTime = iotTablBorroDTO.getStartTime();
			String dateStr = startTime.substring(0,startTime.indexOf(IoTDevUtil.DATE_UINX));
			switch (iotTablBorroDTO.getBorrowTime()) {
					case IoTDevUtil.ALL_DAY:
						String alldayTime = dateStr+" "+ IoTDevUtil.ZERO_UINX;
						iotTablBorroDTO.setAlldayTime(alldayTime);
						if (ioTDeviBorroSerivce.applyALLOfDayTabletSerivce(iotTablBorroDTO)) {
							repDTO.setRepCode(RepCode.SUCCESS_CODE);
						}else {
							repDTO.setRepCode(RepCode.ERROR_CODE);
							repDTO.setRepMsg("无法申请,稍后重试");
						}
						break;
					case IoTDevUtil.MORN_DAY:
						String mornTime = dateStr+" " + IoTDevUtil.MORN_UINX;
						iotTablBorroDTO.setMornTime(mornTime);
						if (ioTDeviBorroSerivce.applyHalfOfDayTabletSerivce(iotTablBorroDTO)) {
							repDTO.setRepCode(RepCode.SUCCESS_CODE);
						}else {
							repDTO.setRepCode(RepCode.ERROR_CODE);
							repDTO.setRepMsg("无法申请,稍后重试");
						}
						break;
					case IoTDevUtil.AFTER_DAY:
						String afterTime = dateStr+" " + IoTDevUtil.AFTER_UINX;
						iotTablBorroDTO.setAfterTime(afterTime);
						if (ioTDeviBorroSerivce.applyHalfOfDayTabletSerivce(iotTablBorroDTO)) {
							repDTO.setRepCode(RepCode.SUCCESS_CODE);
						}else {
							repDTO.setRepCode(RepCode.ERROR_CODE);
							repDTO.setRepMsg("无法申请,稍后重试");
						}
						break;				
					default:
						break;
			}
			
		} catch (DeviceException e) {
			repDTO.setRepCode(RepCode.ERROR_CODE);
			repDTO.setRepMsg(e.getMessage());
		}
		 ;
    	return repDTO;
    }
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "取消申请", notes = "取消申请记录")
    @PostMapping("/cancelTablet")
    @ResponseBody
    public RepDTO cancelTablet(@RequestBody IotTableCancleDTO iotTableCancleDTO) {
		RepDTO repDTO = new RepDTO();
		try {
			if (ioTDeviBorroSerivce.cancelApplyTabletInfo(iotTableCancleDTO)) {
				repDTO.setRepCode(RepCode.SUCCESS_CODE);
			}
		} catch (DeviceException e) {
			repDTO.setRepCode(RepCode.ERROR_CODE);
			repDTO.setRepMsg(e.getMessage());
		}
		return repDTO;
	}
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "借用平板归还", notes = "新增平板借用归还记录")
    @PostMapping("/returnTablet")
    @ResponseBody
    public RepDTO returnTablet(@RequestBody IotDeviReturnDTO iotDeviReturnDTO){
		RepDTO repDTO = new RepDTO();
    	return repDTO;
    }
	
	@ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = CallRmStatDTO.class,reference="list"),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "预约人参会信息查询")
    @PostMapping("/queryTabletMeet")
    @ResponseBody
    public List<CallRmStatDTO> queryTabletMeet(@RequestBody IotBabletMntParam iotBabletMntParam){
    	return new ArrayList<CallRmStatDTO>();
    }
}
