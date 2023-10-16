package com.huafen.tablet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.huafen.tablet.model.apply.IotTablBorroCahe;
import com.huafen.tablet.model.apply.IotTablBorroCode;
import com.huafen.tablet.model.his.IotBorrowHisDTO;
import com.huafen.tablet.model.his.IotCurHisAllDTO;
import com.huafen.tablet.model.his.IotCurentHisDTO;
import com.huafen.tablet.model.iot.IotBorRetuDTO;
import com.huafen.tablet.model.iot.IotTabletDTO;
import com.huafen.tablet.model.iot.PageBean;
import com.huafen.tablet.model.meet.CallRmStatDTO;
import com.huafen.tablet.model.param.BorrowHisParam;
import com.huafen.tablet.model.param.IotBorrowHisParam;
import com.huafen.tablet.model.param.IotCurParam;
import com.huafen.tablet.model.param.TabletRevertParam;

@Mapper
public interface DeviceBorrowMapper {

	
	int insertBorroReturnOperLog(IotBorRetuDTO iotBorRetuDTO);
	
	int updateBorroReturnOperLog(IotTabletDTO iotTabletDTO);
	
	int deleteIotBorReturnOperLog(IotBorRetuDTO iotBorRetuDTO);
	
	Integer countBorroReturnOperNum(IotBorRetuDTO iotBorRetuDTO);
	
	int countCodeInfoPage(PageBean<IotTablBorroCode> pageBean);
	
	int countBorrowMaxNum(IotBorrowHisParam iotBorrowHisParam);
	
	int countBorrowByPage(PageBean<IotBorrowHisDTO> pageBean);
	
	IotCurHisAllDTO countCurentHisRetulInfo(IotCurParam iotCurParam);
	
    List<IotTablBorroCahe>	queryBorrowHisCacheInfo(IotBorrowHisParam iotBorrowHisParam);
	
	List<IotTabletDTO> queryBorrowRetultInfo(TabletRevertParam tabletRevertParam);
	
	List<IotTablBorroCode> queryCodeInfoPageList(PageBean<IotTablBorroCode> pageBean);
	
	List<IotBorrowHisDTO> queryBorrowHisRetultInfo(BorrowHisParam borrowHisParam);
	
	List<CallRmStatDTO>  queryCallRmStatInfo(BorrowHisParam borrowHisParam);
	
	List<IotBorrowHisDTO>  queryBorrowInfoPageList(PageBean<IotBorrowHisDTO> pageBean);
	
	List<IotCurentHisDTO>  queryCurentHisRetulInfo(IotCurParam iotCurParam);
}
