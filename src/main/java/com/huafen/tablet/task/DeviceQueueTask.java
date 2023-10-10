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
import com.huafen.tablet.service.IoTDeviceReturnSerivce;
import com.huafen.tablet.service.impl.RedisQueueService;

@Component
public class DeviceQueueTask {

	private ExecutorService executorBorroService;

	private ExecutorService executorReturnService;

	@Autowired
	@Qualifier("ioTDeviceBindSerivce")
	private IoTDeviceBindSerivce ioTDeviceBindSerivce;
	@Autowired
	@Qualifier("ioTDeviceReturnSerivce")
	private IoTDeviceReturnSerivce ioTDeviceReturnSerivce;
	@Autowired
	@Qualifier("redisQueueService")
	private RedisQueueService redisQueueService;

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(RedissonClient.class);

	@PostConstruct
	public void startThread() {
		executorBorroService = Executors.newFixedThreadPool(1);// 两个大小的固定线程池
		executorBorroService.submit(new DeviceMQ());
		executorReturnService = Executors.newFixedThreadPool(1);// 两个大小的固定线程池
		executorReturnService.submit(new DeviceReMQ());
	}

	@PreDestroy
	public void stopThread() {
		executorBorroService.shutdown();
		executorReturnService.shutdown();
	}

	class DeviceMQ implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Object object = redisQueueService.msgBorrotConsume();
					if (object instanceof IotBorroFlowDTO) {
						IotBorroFlowDTO iotBorroFlowDTO = (IotBorroFlowDTO) object;
						ioTDeviceBindSerivce.pushDeviceTopicByRedis(iotBorroFlowDTO);
					} else if (object instanceof String) {
						System.out.println("消费了：" + (String) object);
					}
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}

		}

	}

	class DeviceReMQ implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Object object = redisQueueService.msgReturnConsume();
					if (object instanceof IotBorroFlowDTO) {
						IotBorroFlowDTO iotBorroFlowDTO = (IotBorroFlowDTO) object;
						ioTDeviceReturnSerivce.pushDeviceTopicByRedis(iotBorroFlowDTO);
					} else if (object instanceof String) {
						System.out.println("消费了：" + (String) object);
					}
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}

		}

	}

}
