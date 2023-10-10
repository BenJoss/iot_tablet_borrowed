package com.huafen.tablet.msg;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import com.huafen.tablet.service.impl.OrgAuthServiceImpl;
import com.huafen.tablet.util.HttpUtil;

@Aspect
@Component
public class OrgSerExcepAspect {
	
	@Autowired
	private OrgAuthServiceImpl orgAuthService;
	
	//设置切入点：这里直接拦截被@ModelView注解的方法
	@Pointcut("@annotation(com.huafen.tablet.msg.OrgSeriviceToken)")
	public void pointcut() {
		
	}
	/*** 当有OrgSeriviceToken的注解的方法抛出异常的时候，做如下的处理*/
	@Around("pointcut()")
	public Object afterThrowable(ProceedingJoinPoint point){
		Object result = null;
		try {
			return point.proceed(point.getArgs());
		} catch (Throwable exThrowable) {
			if(exThrowable instanceof OrgSerException) {
				OrgSerException orgSerException = (OrgSerException) exThrowable;
				if(orgSerException.getCause() instanceof HttpClientErrorException) {
					 HttpClientErrorException httpClientError =(HttpClientErrorException) orgSerException.getCause();
					 if(httpClientError.getStatusCode().value() == HttpUtil.FORBI_DDEN) {
						 orgAuthService.setOrgAccessToken();
						 try {
							 return point.proceed(point.getArgs());
						} catch (Throwable e) {
							
						}
					 }
				 }else {
					 return orgSerException.getMsg();
				 }
			}
			
		}
		return result;
	}

}
