package com.huafen.tablet.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

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

public interface IOTRoomService {

	
	public int saveIOTRoomInfo(IOTRoomDTO iotRoomDTO,MultipartFile file);
	
	public List<IOTRoomDTO> queryIotRoomList(CallMTParam callMTParam);
	
	public List<IOTRoomDTO> queryAllIotRoomList();
	
	public int deleteIOTMTInfo(CallMTParam callMTParam);
	
	public int updateIOTRoom(IOTRoomDTO iotRoomDTO,MultipartFile file);
	
	public int updateIOTRoomClass(IotRMClMdDTO iotRMClMdDTO);
	
	public int updateIOTRMCont(IotRMCoMdDTO iotRMCoMdDTO);
	
	public void loadIOTDeriveServ();
	
	public Map<String,Object> queryIotFloorAddr();
	
	public PageBean<IOTRoomDTO> queryIotRoomByPage(PageBean<IOTRoomDTO> pageBean);
	
	public String insertIOTClass(IotRMClassDTO iotRMClassDTO);
	
	public String insertIOTConet(IotRMContDTO iotRMContDTO);
	
	public List<IotRMClaTypDTO> queryIotRoomClassList(IotRMClParam iotRMClParam);
	
	public int saveIotContDevice(IotDeviceDAO iotDeviceDAO);
	
	public int deleteIotDevice(IotDeviceMdPara iotDeviceMdPara);
	
	public int deleteIotRmCont(IotConParam iotConParam);
	
	public int  deleteIotRmClass(IotClassParam iotClassParam);
}
