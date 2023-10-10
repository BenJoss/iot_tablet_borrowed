package com.huafen.tablet.service.impl;

import org.redisson.api.RBlockingDeque;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huafen.tablet.model.apply.IotBorroFlowDTO;
import com.huafen.tablet.msg.DeviceException;
import com.huafen.tablet.util.RedisUtil;

@Service("redisQueueService")
public class RedisQueueService {

    @Autowired
    private RedissonClient redissonClient;
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(RedisQueueService.class);
    /**
     * 消息生产
     *
     * @param msg
     */
    public void msgBorrotProduce(IotBorroFlowDTO iotBorroFlowDTO) {
        RBlockingDeque<Object> blockDeque = redissonClient.getBlockingDeque(RedisUtil.MQTT_TABLET_REDIS_QUEUE);
        try {
            blockDeque.putFirst(iotBorroFlowDTO); // 消息写入队列头部
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }
    
    /**
     * 消息生产
     *
     * @param msg
     */
    public void msgReturnProduce(IotBorroFlowDTO iotBorroFlowDTO) {
        RBlockingDeque<Object> blockDeque = redissonClient.getBlockingDeque(RedisUtil.MQTT_TABLET_REDIS_RETRUN_QUEUE);
        try {
            blockDeque.putFirst(iotBorroFlowDTO); // 消息写入队列头部
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }
    
    /**
     * 消息消费：阻塞
     */
    public Object msgBorrotConsume() {
    	   try {
    		   RBlockingDeque<Object> blockDeque = redissonClient.getBlockingDeque(RedisUtil.MQTT_TABLET_REDIS_QUEUE);
    		   return  blockDeque.takeLast();  
		} catch (Exception e) {
			DeviceException exception = new DeviceException();
			exception.setMsg(e.getMessage());
			throw exception;
		}
    }
    
    /**
     * 消息消费：阻塞
     */
    public Object msgReturnConsume() {
    	   try {
    		   RBlockingDeque<Object> blockDeque = redissonClient.getBlockingDeque(RedisUtil.MQTT_TABLET_REDIS_RETRUN_QUEUE);
    		   return  blockDeque.takeLast();  
		} catch (Exception e) {
			DeviceException exception = new DeviceException();
			exception.setMsg(e.getMessage());
			throw exception;
		}
    }
}
