package com.huafen.tablet.service.impl;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huafen.tablet.mapper.DevicePartitionMapper;
import com.huafen.tablet.model.param.PartParam;
import com.huafen.tablet.model.partition.PartitionDTO;
import com.huafen.tablet.service.IotPartitionService;
import com.huafen.tablet.util.DateUtil;
import com.huafen.tablet.util.IoTDevUtil;

@Service("iotPartitionService")
public class IotPartitionServiceImpl implements IotPartitionService{

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(IoTDeviceTopicSerivceImpl.class);
	@Autowired
	private DevicePartitionMapper devicePartitionMapper;

	
	@Override
	@Transactional(propagation = Propagation.REQUIRED ,rollbackFor = Exception.class)
	public void editTablePartition() {
		try {
			String partTime = DateUtil.getNextMonth();
			// t_iot_device_borrow
			PartParam partParam = new PartParam();
			String partName = IoTDevUtil.PARTI_TION + partTime;
			partParam.setPartTable(IoTDevUtil.T_IOT_DEVICE_BORROW);
			partParam.setPartName(partName);
			partParam.setPartTime(partTime);
			PartitionDTO  partitionDTO = devicePartitionMapper.queryTablePartitionInfo(partParam);
			if (partitionDTO == null) {
				try {
					PartitionDTO  partition = new PartitionDTO();
					partition.setPartTable(IoTDevUtil.T_IOT_DEVICE_BORROW);
					partition.setPartName(partName);
					partition.setPartTime(partTime);
					devicePartitionMapper.alterTablePartition(partition);
					devicePartitionMapper.insertTablePartition(partition);
				} catch (Exception e) {
					log.error("添加分区失败："+e.getMessage());
				}
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
	}
	
	
}
