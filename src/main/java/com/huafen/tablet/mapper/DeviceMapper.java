package com.huafen.tablet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.huafen.tablet.model.apply.IotBindTabAllDTO;
import com.huafen.tablet.model.apply.IotBrorroTabDTO;
import com.huafen.tablet.model.apply.IotOperLogDTO;
import com.huafen.tablet.model.apply.IotTablBorroDTO;
import com.huafen.tablet.model.apply.IotTablBorroHisDTO;
import com.huafen.tablet.model.iot.IotTabletDTO;
import com.huafen.tablet.model.param.TabletApplayParam;
import com.huafen.tablet.model.param.TabletRevertParam;

@Mapper
public interface DeviceMapper {

	IotBrorroTabDTO  getTabletApplayNum(TabletApplayParam tabletApplayParam);
	
	int countBorrowNum(TabletRevertParam tabletRevertParam);
	
	int insertIotTablBorro(IotTablBorroDTO iotTablBorroDTO);
	
	int insertIotOperLog(IotOperLogDTO iotOperLogDTO);
	
	IotTablBorroHisDTO queryBindBorrowInfo(TabletRevertParam tabletRevertParam);
	
	List<IotTabletDTO> queryIotTabletInfo(TabletRevertParam tabletRevertParam);
	
	int updateIotTablBorro(IotBindTabAllDTO iotBindTabAllDTO);
}
