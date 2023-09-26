package com.huafen.tablet.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.huafen.tablet.model.apply.IotBrorroTabDTO;
import com.huafen.tablet.model.apply.IotOperLogDTO;
import com.huafen.tablet.model.apply.IotTablBorroDTO;
import com.huafen.tablet.model.param.TabletApplayParam;

@Mapper
public interface DeviceMapper {

	IotBrorroTabDTO  getTabletApplayNum(TabletApplayParam tabletApplayParam);
	
	int insertIotTablBorro(IotTablBorroDTO iotTablBorroDTO);
	
	int insertIotOperLog(IotOperLogDTO iotOperLogDTO);
}
