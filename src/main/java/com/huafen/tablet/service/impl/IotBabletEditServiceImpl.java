package com.huafen.tablet.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huafen.tablet.config.RepCode;
import com.huafen.tablet.mapper.DeviceMapper;
import com.huafen.tablet.model.iot.IotEditTabletDTO;
import com.huafen.tablet.model.iot.IotSaveTabletDTO;
import com.huafen.tablet.model.iot.IotTabletDTO;
import com.huafen.tablet.model.param.IotDeleTablet;
import com.huafen.tablet.model.param.IotEditTabletParam;
import com.huafen.tablet.model.param.TabletMageParam;
import com.huafen.tablet.model.req.RepDTO;
import com.huafen.tablet.service.IotBabletEditService;
import com.huafen.tablet.util.IoTDevUtil;

@Service("iotBabletEditService")
public class IotBabletEditServiceImpl implements IotBabletEditService{

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(IotBabletEditServiceImpl.class);
	@Autowired
	private DeviceMapper deviceMapper;
	@Resource
    private RedissonClient redissonClient;
	
	@Override
	public RepDTO queryMageBabletSerivce(TabletMageParam tabletMageParam) {
		RepDTO  repDTO = new RepDTO();
		try {
			List<IotTabletDTO> iotTablBorroHisList = deviceMapper.queryIotEditTabletInfo(tabletMageParam);
			 if (iotTablBorroHisList != null && !iotTablBorroHisList.isEmpty() ) {
				 repDTO.setRepCode(RepCode.SUCCESS_CODE);
				 repDTO.setResult(iotTablBorroHisList);
			}else {
				repDTO.setRepCode(RepCode.ERROR_CODE);
				repDTO.setRepMsg("");
			}
		} catch (Exception e) {
			logger.error("异常", e.getMessage());
		}
		return repDTO;
	}

	@Override
	public RepDTO editBabletSerivce(IotEditTabletDTO iotEditTabletDTO) {
		RepDTO  repDTO = new RepDTO();
		try {
			deviceMapper.updateIotEditBablet(iotEditTabletDTO);
			repDTO.setRepCode(RepCode.SUCCESS_CODE);
		} catch (Exception e) {
			repDTO.setRepCode(RepCode.ERROR_CODE);
			repDTO.setRepMsg("");
			logger.error("异常", e.getMessage());
		}
		return repDTO;
	}

	@Override
	public RepDTO saveBabletSerivce(IotSaveTabletDTO iotSaveTabletDTO) {
		RepDTO  repDTO = new RepDTO();
		try {
			iotSaveTabletDTO.setBorrowedStatus(IoTDevUtil.IDLE_STATE);
			IotEditTabletParam iotEditTabletParam = new IotEditTabletParam();
			iotEditTabletParam.setTabletID(iotSaveTabletDTO.getTabletID());
			Integer  count = deviceMapper.countTabletNum(iotEditTabletParam);
			if (count == null || count == 0) {
				deviceMapper.insertIotTabletInfo(iotSaveTabletDTO);
			}
			repDTO.setRepCode(RepCode.SUCCESS_CODE);
		} catch (Exception e) {
			repDTO.setRepCode(RepCode.ERROR_CODE);
			repDTO.setRepMsg("");
			logger.error("异常", e.getMessage());
		}
		return repDTO;
	}

	@Override
	public RepDTO deleteIotIabletInfoSerivce(IotDeleTablet iotDeleTablet) {
		RepDTO  repDTO = new RepDTO();
		try {
			String tabletID = iotDeleTablet.getTabletID();
			deviceMapper.deleteIotIabletInfo(iotDeleTablet);
			if (redissonClient.getBucket(tabletID).isExists()) {
				redissonClient.getBucket(tabletID).delete();
			}
			repDTO.setRepCode(RepCode.SUCCESS_CODE);
		} catch (Exception e) {
			repDTO.setRepCode(RepCode.ERROR_CODE);
			repDTO.setRepMsg("");
			logger.error("异常", e.getMessage());
		}
		return repDTO;
	}
	
	
}
