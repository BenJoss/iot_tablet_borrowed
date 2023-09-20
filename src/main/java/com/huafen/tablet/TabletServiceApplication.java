package com.huafen.tablet;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.huafen.tablet.websocket.WebSocketServer;

@SpringBootApplication
@EnableKnife4j
@EnableScheduling
public class TabletServiceApplication {

	public static void main(String[] args) {

        SpringApplication.run(TabletServiceApplication.class, args);
        
        String host;
		try {
			host = InetAddress.getLocalHost().getHostAddress();
	        System.out.println("[----------------------------------------------------------]");
	        System.out.println("平板借还服务启动 :" + host );
	        System.out.println("[----------------------------------------------------------]");
	        WebSocketServer.inst().run(53135);	      
		} catch (UnknownHostException e) {
			
		}

	}
}
