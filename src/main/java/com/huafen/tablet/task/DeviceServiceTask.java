package com.huafen.tablet.task;

import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.huafen.tablet.service.IoTDeviceBindSerivce;
import com.huafen.tablet.service.IoTDeviceBorrowCacheSerivce;

@Component
public class DeviceServiceTask {

	private static ReentrantLock lock = new ReentrantLock();
	
	private static ReentrantLock cacheLock = new ReentrantLock();

	 @Resource
	 private CacheManager cacheManager;
	
	@Autowired
	@Qualifier("ioTDeviceBindSerivce")
	private IoTDeviceBindSerivce ioTDeviceBindSerivce;
		
	@Autowired
	@Qualifier("ioTDeviceBorrowCacheSerivce")
	private IoTDeviceBorrowCacheSerivce ioTDeviceBorrowCacheSerivce;
	

	@Scheduled(cron = "0 0/5 * * * ?")
	public void loadCache() {
		try {
			cacheLock.lock();
			  // 显示所有的Cache空间
			ioTDeviceBorrowCacheSerivce.loadBorrowInfoCache();
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
