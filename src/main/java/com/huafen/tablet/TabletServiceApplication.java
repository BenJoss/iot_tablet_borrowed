package com.huafen.tablet;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;

@SpringBootApplication
@EnableKnife4j
@EnableScheduling
@EnableTransactionManagement
public class TabletServiceApplication {

	public static void main(String[] args) {

		
        SpringApplication.run(TabletServiceApplication.class, args);
        
        String host;
		try {
			host = InetAddress.getLocalHost().getHostAddress();
	        System.out.println("[----------------------------------------------------------]");
	        System.out.println("平板借还服务启动 :" + host );
	        System.out.println("[----------------------------------------------------------]");     
		} catch (UnknownHostException e) {
			
		}

	}
}
