package com.huafen.tablet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.huafen.tablet.model.iot.IotBorRetuDTO;
import com.huafen.tablet.model.iot.IotTabletDTO;
import com.huafen.tablet.model.param.TabletRevertParam;

@Mapper
public interface DeviceBorrowMapper {

	
	int insertBorroReturnOperLog(IotBorRetuDTO iotBorRetuDTO);
	
	int updateBorroReturnOperLog(IotTabletDTO iotTabletDTO);
	
	int deleteIotBorReturnOperLog(IotBorRetuDTO iotBorRetuDTO);
	
	Integer countBorroReturnOperNum(IotBorRetuDTO iotBorRetuDTO);
	
	List<IotTabletDTO> queryBorrowRetultInfo(TabletRevertParam tabletRevertParam);
}
