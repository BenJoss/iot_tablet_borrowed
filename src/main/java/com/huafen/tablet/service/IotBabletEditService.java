package com.huafen.tablet.service;

import com.huafen.tablet.model.iot.IotEditTabletDTO;
import com.huafen.tablet.model.iot.IotSaveTabletDTO;
import com.huafen.tablet.model.param.TabletMageParam;
import com.huafen.tablet.model.req.RepDTO;

public interface IotBabletEditService {

	
	public RepDTO queryMageBabletSerivce(TabletMageParam tabletMageParam);
	
	
	public RepDTO editBabletSerivce(IotEditTabletDTO iotEditTabletDTO);
	
	
	public RepDTO saveBabletSerivce(IotSaveTabletDTO iotSaveTabletDTO);
}
