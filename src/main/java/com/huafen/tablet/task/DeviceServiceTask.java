package com.huafen.tablet.task;

import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.huafen.tablet.service.IoTDeviceBindSerivce;
import com.huafen.tablet.service.IoTDeviceBorrowCacheSerivce;
import com.huafen.tablet.service.IotPartitionService;

@Component
public class DeviceServiceTask {

	private static ReentrantLock lock = new ReentrantLock();
	
	private static ReentrantLock cacheLock = new ReentrantLock();

	@Autowired
	@Qualifier("ioTDeviceBindSerivce")
	private IoTDeviceBindSerivce ioTDeviceBindSerivce;
	
	
	@Autowired
	@Qualifier("iotPartitionService")
	private IotPartitionService iotPartitionService;
		
	@Autowired
	@Qualifier("ioTDeviceBorrowCacheSerivce")
	private IoTDeviceBorrowCacheSerivce ioTDeviceBorrowCacheSerivce;
	

	@Scheduled(cron = "*/5 * * * * *")
	public void loadCache() {
		try {
			cacheLock.lock();
			// 编辑分区
			iotPartitionService.editTablePartition();
			  // 显示所有的Cache空间
			ioTDeviceBorrowCacheSerivce.loadMeetingInfoCache();
			//
			// ioTDeviceBorrowCacheSerivce.loadBorrowInfoCache();
		}catch (Exception e) {

		}finally {
			cacheLock.unlock();
		}
		
	}
	@Scheduled(cron = "*/5 * * * * *")
	public void sendTopic() {
		try {
			lock.lock();
			ioTDeviceBindSerivce.loadDeviceInfo();
		}catch (Exception e) {

		} finally {
			lock.unlock();
		}
		
	}
	
	
	
	
}
