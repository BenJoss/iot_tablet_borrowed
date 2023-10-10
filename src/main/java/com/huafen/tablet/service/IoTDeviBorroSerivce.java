package com.huafen.tablet.service;

import com.huafen.tablet.model.apply.IotTablBorroDTO;
import com.huafen.tablet.model.apply.IotTableCancleDTO;
import com.huafen.tablet.model.param.TabletApCoParam;
import com.huafen.tablet.model.req.RepDTO;

public interface IoTDeviBorroSerivce {

	
	public  boolean applyHalfOfDayTabletSerivce(IotTablBorroDTO iotTablBorroDTO);
	
	public  void     saveApplyTabletInfoSerivce(IotTablBorroDTO iotTablBorroDTO);
	
	public boolean  applyALLOfDayTabletSerivce(IotTablBorroDTO iotTablBorroDTO);
	
	public  boolean cancelApplyTabletInfo(IotTableCancleDTO iotTableCancleDTO);
	
	public RepDTO queryBorrowVerifyCode(TabletApCoParam tabletApCoParam); 

}
