package com.huafen.tablet.controller.iot;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.huafen.tablet.config.RepCode;
import com.huafen.tablet.model.iot.IOTRoomDTO;
import com.huafen.tablet.model.iot.IotDeviceDAO;
import com.huafen.tablet.model.iot.IotRMClMdDTO;
import com.huafen.tablet.model.iot.IotRMClaTypDTO;
import com.huafen.tablet.model.iot.IotRMClassDTO;
import com.huafen.tablet.model.iot.IotRMCoMdDTO;
import com.huafen.tablet.model.iot.IotRMContDTO;
import com.huafen.tablet.model.iot.PageBean;
import com.huafen.tablet.model.param.CallMTParam;
import com.huafen.tablet.model.param.IotClassParam;
import com.huafen.tablet.model.param.IotConParam;
import com.huafen.tablet.model.param.IotDeviceMdPara;
import com.huafen.tablet.model.param.IotRMClParam;
import com.huafen.tablet.model.req.RepDTO;
import com.huafen.tablet.service.IOTRoomService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {"物联首页会议信息"})
@RestController
@RequestMapping("/IOTRoomCrtl")
public class IOTRoomCrtl {

	@Autowired
	@Qualifier("iotRoomService")
	private IOTRoomService iotRoomService ;
	
    @ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "新增物联首页会议室信息", notes = "新增物联首页会议室")
    @PostMapping("/saveIOTRoomInfo")
    @ResponseBody
    public RepDTO saveIOTRoomInfo(@RequestPart("data") String data ,
    		@RequestPart("file") MultipartFile file){
    	RepDTO  repDTO = new RepDTO();
    	IOTRoomDTO iotRoomDTO = JSON.parseObject(data,IOTRoomDTO.class);
    	if (iotRoomService.saveIOTRoomInfo(iotRoomDTO,file)>0) {
    		repDTO.setRepCode(RepCode.SUCCESS_CODE);
		}
    	return	repDTO;
    	
    }
    
    @ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = IOTRoomDTO.class,responseContainer = "List"),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "物联首页会议室显示", notes = "分页查询首页会议室")
    @PostMapping("/queryIotRoomList")
    @ResponseBody
    public PageBean<IOTRoomDTO> queryIotRoomList(@RequestBody PageBean<IOTRoomDTO> pageBean){
    	return	iotRoomService.queryIotRoomByPage(pageBean);
    	
    }
    
    @ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = IOTRoomDTO.class,responseContainer = "List"),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "物联首页会议室列表示", notes = "查询首页所有会议室")
    @PostMapping("/queryAllIotRoomList")
    @ResponseBody
    public List<IOTRoomDTO> queryAllIotRoomList(){
    	return	iotRoomService.queryAllIotRoomList();    	
    }
    
    @ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "物联首页会议室删除",notes = "删除首页会议室")
    @PostMapping("/deleteIOTMTInfo")
    @ResponseBody
    public RepDTO deleteIOTMTInfo(@RequestBody CallMTParam callMTParam){
    	RepDTO  repDTO = new RepDTO();
    	repDTO.setRepCode(RepCode.SUCCESS_CODE);
    	iotRoomService.deleteIOTMTInfo(callMTParam);
    	return	repDTO;
    	
    }
    
    @ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "物联首页会议室修改",notes = "修改首页会议室")
    @PostMapping("/updateIOTRoom")
    @ResponseBody
    public RepDTO updateIOTRoom(@RequestPart("data") String data ,
    		@RequestPart("file") MultipartFile file){
    	IOTRoomDTO iotRoomDTO = JSON.parseObject(data,IOTRoomDTO.class);
    	RepDTO  repDTO = new RepDTO();
    	repDTO.setRepCode(RepCode.SUCCESS_CODE);
    	iotRoomService.updateIOTRoom(iotRoomDTO,file);
    	return	repDTO;
    	
    }
    
    @ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "物联首页会议室配置信息",notes = "查询首页会议室配置信息")
    @PostMapping("/queryIotFloorAddr")
    @ResponseBody
    public Map<String, Object>  queryIotFloorAddr(){
    	return	iotRoomService.queryIotFloorAddr();
    	
    }
    
    
    @ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "会议室新增发布页配置",notes = "新增发布页配置")
    @PostMapping("/saveIOTClassInfo")
    @ResponseBody
    public RepDTO  saveIOTClassInfo(@RequestBody IotRMClassDTO iotRMClassDTO){
    	RepDTO  repDTO = new RepDTO();
    	repDTO.setRepCode(RepCode.SUCCESS_CODE);
    	String classID = iotRoomService.insertIOTClass(iotRMClassDTO);
    	repDTO.setResult(classID);
    	repDTO.setRepMsg("");
    	return	repDTO;
    }
    
    
    @ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "会议室新增发布页详细信息",notes = "新增发布页信息")
    @PostMapping("/saveIOTConet")
    @ResponseBody
    public RepDTO  saveIOTConet(@RequestBody IotRMContDTO iotRMContDTO){
    	RepDTO  repDTO = new RepDTO();
    	repDTO.setRepCode(RepCode.SUCCESS_CODE);
    	String contID = iotRoomService.insertIOTConet(iotRMContDTO);
    	repDTO.setResult(contID);
    	repDTO.setRepMsg("");
    	return	repDTO;
    }
    
    @ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "物联首页会议室发布页信息查询",notes = "查询发布页信息")
    @PostMapping("/queryIotRoomClassList")
    @ResponseBody
    public RepDTO  queryIotRoomClassList(@RequestBody IotRMClParam iotRMClParam){
    	RepDTO  repDTO = new RepDTO();
    	repDTO.setRepCode(RepCode.SUCCESS_CODE);
    	List<IotRMClaTypDTO> list = iotRoomService.queryIotRoomClassList(iotRMClParam);
    	repDTO.setResult(list);
    	repDTO.setRepMsg("");
    	return	repDTO;
    }
    
    @ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "会议室修改发布页配置",notes = "更新发布页配置")
    @PostMapping("/modifyIOTRoomClass")
    @ResponseBody
    public RepDTO  updateIOTRoomClass(@RequestBody IotRMClMdDTO iotRMClMdDTO){
    	RepDTO  repDTO = new RepDTO();
    	repDTO.setRepCode(RepCode.SUCCESS_CODE);
    	iotRoomService.updateIOTRoomClass(iotRMClMdDTO);
    	repDTO.setRepMsg("");
    	return	repDTO;
    }
    
    @ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "会议室修改发布页内容信息",notes = "更新发布页内容信息")
    @PostMapping("/modifyIOTRMCont")
    @ResponseBody
    public RepDTO  updateIOTRMCont(@RequestBody IotRMCoMdDTO iotRMCoMdDTO){
    	RepDTO  repDTO = new RepDTO();
    	repDTO.setRepCode(RepCode.SUCCESS_CODE);
    	iotRoomService.updateIOTRMCont(iotRMCoMdDTO);
    	repDTO.setRepMsg("");
    	return	repDTO;
    }
    
    @ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "会议室发布页新增绑定设备",notes = "新增绑定设备")
    @PostMapping("/saveIotContDevice")
    @ResponseBody
    public RepDTO  saveIotContDevice(@RequestBody IotDeviceDAO iotDeviceDAO){
    	RepDTO  repDTO = new RepDTO();
    	if (iotRoomService.saveIotContDevice(iotDeviceDAO) >0) {
        	repDTO.setRepCode(RepCode.SUCCESS_CODE);
        	repDTO.setRepMsg("");
		}else {
        	repDTO.setRepCode(RepCode.ERROR_CODE);
        	repDTO.setRepMsg("");
		}
    	return	repDTO;
    }
    
    @ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "会议室发布页删除绑定设备",notes = "删除绑定设备")
    @PostMapping("/deleteIotDevice")
    @ResponseBody
    public RepDTO  deleteIotDevice(@RequestBody IotDeviceMdPara iotDeviceMdPara){
    	RepDTO  repDTO = new RepDTO();
    	if (iotRoomService.deleteIotDevice(iotDeviceMdPara) >= 0) {
        	repDTO.setRepCode(RepCode.SUCCESS_CODE);
        	repDTO.setRepMsg("");
		}else {
        	repDTO.setRepCode(RepCode.ERROR_CODE);
        	repDTO.setRepMsg("");
		}
    	return	repDTO;
    }
    
    @ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "会议室发布页删除发布页",notes = "删除发布页")
    @PostMapping("/deleteIotRmCont")
    @ResponseBody
    public RepDTO  deleteIotRmCont(@RequestBody IotConParam iotConParam){
    	RepDTO  repDTO = new RepDTO();
    	if (iotRoomService.deleteIotRmCont(iotConParam) >= 0) {
        	repDTO.setRepCode(RepCode.SUCCESS_CODE);
        	repDTO.setRepMsg("");
		}else {
        	repDTO.setRepCode(RepCode.ERROR_CODE);
        	repDTO.setRepMsg("");
		}
    	return	repDTO;
    }
    
    @ApiResponses( value = { 
			@ApiResponse(code = 200, message = "success",response = RepDTO.class),
			@ApiResponse(code = 1001, message = "error")})
    @ApiOperation(value = "会议室发布页删除一级",notes = "删除一级")
    @PostMapping("/deleteIotRmClass")
    @ResponseBody
    public RepDTO  deleteIotRmClass(@RequestBody IotClassParam iotClassParam){
    	RepDTO  repDTO = new RepDTO();
    	if (iotRoomService.deleteIotRmClass(iotClassParam) >= 0) {
        	repDTO.setRepCode(RepCode.SUCCESS_CODE);
        	repDTO.setRepMsg("");
		}else {
        	repDTO.setRepCode(RepCode.ERROR_CODE);
        	repDTO.setRepMsg("");
		}
    	return	repDTO;
    }
}
