package com.huafen.tablet.service;

import com.huafen.tablet.model.apply.IotTablBorroDTO;

public interface IoTDeviBorroSerivce {

	
	public  boolean applyHalfOfDayTabletSerivce(IotTablBorroDTO iotTablBorroDTO);
	
	public void saveApplyTabletInfoSerivce(IotTablBorroDTO iotTablBorroDTO);
	
	public boolean  applyALLOfDayTabletSerivce(IotTablBorroDTO iotTablBorroDTO);

}
