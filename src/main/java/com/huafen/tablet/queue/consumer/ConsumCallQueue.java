package com.huafen.tablet.queue.consumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.huafen.tablet.model.chat.CallChatDTO;
import com.huafen.tablet.queue.CallQueue;
import com.huafen.tablet.service.CallChatSaveService;

@Component
public class ConsumCallQueue {

	@Autowired
	@Qualifier("callChatSaveService")
	private CallChatSaveService callChatSaveService;
	
	private ExecutorService executorService ;
	
	@PostConstruct
    public void startThread() {
		executorService = Executors.newFixedThreadPool(1);// 两个大小的固定线程池
		executorService.submit(new CallChat(callChatSaveService));
    }
	
	@PreDestroy
    public void stopThread() {
		executorService.shutdown();
    }
	
	class CallChat implements Runnable {
		
		CallChatSaveService callChatSaveService;
		
		 public CallChat(CallChatSaveService callChatSaveService) {
	            this.callChatSaveService = callChatSaveService;
	        }
		 
		@Override
		public void run() {
			while (true) {
			   try {
				   Object  object =  CallQueue.getCallQueue().consume();
				   if(object instanceof CallChatDTO) {
					   CallChatDTO callChat = (CallChatDTO) object;
					   callChatSaveService.saveCallChatInfo(callChat);
				   }else if (object instanceof String) {
					    System.out.println("消费了："+(String)object);
				}
			} catch (Exception e) {
				
			}	
		  }
		}
		
	}  
}
