package com.huafen.tablet.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.huafen.tablet.model.apply.IotBorroFlowDTO;
import com.huafen.tablet.service.IoTDeviceBindSerivce;
import com.huafen.tablet.service.impl.RedisQueueService;

@Component
public class DeviceQueueTask {

    private ExecutorService executorService ;

	@Autowired
	@Qualifier("ioTDeviceBindSerivce")
	private IoTDeviceBindSerivce ioTDeviceBindSerivce;
	@Autowired
	@Qualifier("redisQueueService")
	private RedisQueueService redisQueueService;

	
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(RedissonClient.class);

	
	@PostConstruct
    public void startThread() {
		executorService = Executors.newFixedThreadPool(1);// 两个大小的固定线程池
		executorService.submit(new DeviceMQ());
    }
	
	@PreDestroy
    public void stopThread() {
		executorService.shutdown();
    }
	
	class DeviceMQ implements Runnable{
		
		@Override
		public void run() {
			while (true) {
				   try {
					   Object object =  redisQueueService.msgBorrotConsume(); 
					   if(object instanceof IotBorroFlowDTO) {
						   IotBorroFlowDTO iotBorroFlowDTO = (IotBorroFlowDTO) object;
						   ioTDeviceBindSerivce.pushDeviceTopicByRedis(iotBorroFlowDTO);
					   }else if (object instanceof String) {
						    System.out.println("消费了："+(String)object);
					}
				} catch (Exception e) {
					 log.error(e.getMessage());
				}	
		}
		
	}
		
	}
	
}
