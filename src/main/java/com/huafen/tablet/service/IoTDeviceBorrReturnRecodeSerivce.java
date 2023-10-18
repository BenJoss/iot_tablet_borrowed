package com.huafen.tablet.service;

import java.util.List;
import java.util.Map;

import com.huafen.tablet.model.apply.IotTablBorroCode;
import com.huafen.tablet.model.his.IotBorrowHisDTO;
import com.huafen.tablet.model.his.IotCurHisAllDTO;
import com.huafen.tablet.model.iot.IotBorRetuDTO;
import com.huafen.tablet.model.iot.IotTabletDTO;
import com.huafen.tablet.model.iot.PageBean;
import com.huafen.tablet.model.param.IotCurParam;
import com.huafen.tablet.model.param.TabletRevertParam;
import com.huafen.tablet.model.req.RepDTO;
import com.huafen.tablet.model.req.ReposeDTO;

public interface IoTDeviceBorrReturnRecodeSerivce {


	public RepDTO saveDeviceBorrReturnRecodes(List<IotBorRetuDTO> iotBorRetuList); 
	
	public RepDTO updateBorroReturnOperLog(List<IotTabletDTO> iotTabletList);
	
	public RepDTO queryBorrowRetultInfoSerivce(TabletRevertParam tabletRevertParam);
	
	public Map<String, Object> queryBorrowinfoSerivce(TabletRevertParam tabletRevertParam);
	
    public PageBean<IotTablBorroCode> queryCodeInfoByPage(PageBean<IotTablBorroCode> pageBean);
    
    public PageBean<IotBorrowHisDTO> queryBorrowInfoPageList(PageBean<IotBorrowHisDTO> pageBean);
    
    public PageBean<IotBorrowHisDTO> queryBorrowInfoPageListByCache(PageBean<IotBorrowHisDTO> pageBean);
    
    public ReposeDTO<IotCurHisAllDTO> queryCurentHisRetulInfoSerivce(IotCurParam iotCurParam);
}
