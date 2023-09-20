package com.huafen.tablet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.huafen.tablet.model.iot.IotDeviceDAO;
import com.huafen.tablet.model.iot.IotDeviceDTO;
import com.huafen.tablet.model.param.IotClassParam;
import com.huafen.tablet.model.param.IotConParam;
import com.huafen.tablet.model.param.IotDeviceMdPara;
import com.huafen.tablet.model.param.IotDeviceParam;

@Mapper
public interface IotMapper {

	int insertIotContDevice(IotDeviceDAO iotDeviceDAO);
	
	int  deleteIotDevice(IotDeviceMdPara iotDeviceMdPara);
	
	int  deleteIotRmCont(IotConParam iotConParam);
	
	int  deleteIotRmClass(IotClassParam iotClassParam);
	
	int  countRmClassNum(IotClassParam iotClassParam);
	
	int  countRmContNum(IotClassParam iotClassParam);
	
	List<IotDeviceDTO> queryIotContDevice(IotDeviceParam iotDeviceParam);
}
