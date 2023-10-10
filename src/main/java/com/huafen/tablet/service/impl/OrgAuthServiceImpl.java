package com.huafen.tablet.service.impl;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.huafen.tablet.model.user.OrgTokenPO;
import com.huafen.tablet.util.CallTokenUtil;

@Component("orgAuthService")
public class OrgAuthServiceImpl {

	@Autowired
	private RestTemplate restTemplate;
	
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(OrgAuthServiceImpl.class);
	
	public synchronized void setOrgAccessToken() {
		try {
			String url = "http://d-nari.sgepri.sgcc.com.cn:90/common-openapi-oauth/oauth/token?username=user_multiport_plat2&password=123456&grant_type=password&scope=select&client_id=client_multiport_plat&client_secret=123456";
			// 创建一个请求头对象
			HttpHeaders httpHeaders = new HttpHeaders();
			HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
			ResponseEntity<OrgTokenPO> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
					OrgTokenPO.class);
			OrgTokenPO body = responseEntity.getBody();
			CallTokenUtil.setOrgAccesssToken(body.getAccess_token());
			log.info(body.getAccess_token());
		} catch (HttpClientErrorException e) {
			log.error(String.valueOf(e.getStatusCode().value()));
		}
	}
}
