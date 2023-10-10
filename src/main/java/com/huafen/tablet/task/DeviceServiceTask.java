package com.huafen.tablet.task;

import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.huafen.tablet.service.IoTDeviceBindSerivce;

@Component
public class DeviceServiceTask {

	private static ReentrantLock lock = new ReentrantLock();


	
	@Autowired
	@Qualifier("ioTDeviceBindSerivce")
	private IoTDeviceBindSerivce ioTDeviceBindSerivce;
	

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
