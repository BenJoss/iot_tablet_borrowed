package com.huafen.tablet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.huafen.tablet.model.apply.IotBindTabAllDTO;
import com.huafen.tablet.model.apply.IotBindTabletDTO;
import com.huafen.tablet.model.apply.IotBrorroTabDTO;
import com.huafen.tablet.model.apply.IotDeviReturnDTO;
import com.huafen.tablet.model.apply.IotOperLogDTO;
import com.huafen.tablet.model.apply.IotTablBorroDTO;
import com.huafen.tablet.model.apply.IotTablBorroHisDTO;
import com.huafen.tablet.model.apply.IotTableCancleDTO;
import com.huafen.tablet.model.iot.IotEditTabletDTO;
import com.huafen.tablet.model.iot.IotSaveTabletDTO;
import com.huafen.tablet.model.iot.IotTabletDTO;
import com.huafen.tablet.model.param.IotEditTabletParam;
import com.huafen.tablet.model.param.TabletApCoParam;
import com.huafen.tablet.model.param.TabletApplayParam;
import com.huafen.tablet.model.param.TabletMageParam;
import com.huafen.tablet.model.param.TabletRevertParam;

@Mapper
public interface DeviceMapper {

	IotBrorroTabDTO  getTabletApplayNum(TabletApplayParam tabletApplayParam);
	
	int countBorrowNum(TabletRevertParam tabletRevertParam);
	
	Integer countReturnNum(TabletRevertParam tabletRevertParam);
	
	Integer countTabletNum(IotEditTabletParam iotEditTabletParam);
	
	int insertIotTablBorro(IotTablBorroDTO iotTablBorroDTO);
	
	int insertIotOperLog(IotOperLogDTO iotOperLogDTO);
	
	int insertDeviceReturn(IotDeviReturnDTO iotDeviReturnDTO);
	
	int insertIotTabletInfo(IotSaveTabletDTO iotSaveTabletDTO);
	
	IotTablBorroHisDTO queryBindBorrowInfo(TabletRevertParam tabletRevertParam);
	
	List<IotTabletDTO> queryIotTabletInfo(TabletRevertParam tabletRevertParam);
	
	List<IotTabletDTO> queryIotEditTabletInfo(TabletMageParam tabletMageParam);
	
	List<IotTablBorroHisDTO> queryBorrowVerifyCode(TabletApCoParam tabletApCoParam);
	
	int updateIotTablBorro(IotBindTabAllDTO iotBindTabAllDTO);
	
	int updateCancelIotTablBorro(IotTableCancleDTO iotTableCancleDTO);
	
	int updateIotTabletInfo(IotBindTabletDTO iotBindTabletDTO);
	
	int updateDeviceReturnTable(IotDeviReturnDTO iotDeviReturnDTO);
	
	int updateIotEditBablet(IotEditTabletDTO iotEditTabletDTO);
	
	int setIotTabletCodeNull(IotBindTabletDTO iotBindTabletDTO);
}
