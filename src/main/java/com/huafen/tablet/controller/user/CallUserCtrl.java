package com.huafen.tablet.controller.user;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.huafen.tablet.config.RepCode;
import com.huafen.tablet.model.config.MTConfig;
import com.huafen.tablet.model.req.RepOrgUser;
import com.huafen.tablet.model.user.OrgUserParam;
import com.huafen.tablet.msg.OrgSeriviceToken;
import com.huafen.tablet.msg.OrgSerException;
import com.huafen.tablet.util.CallTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = { "呼叫用户信息相关接口" })
@RestController
@RequestMapping("/CallUserCtrl")
public class CallUserCtrl {

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(CallUserCtrl.class);

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private MTConfig mtConfig;

	@ApiResponses(value = { @ApiResponse(code = 200, message = "success"),
			@ApiResponse(code = 1001, message = "error") })
	@ApiOperation(value = "用户信息查询", notes = "根据id、姓名、手机查询用户信息")
	@PostMapping("/queryUserByOrgService")
	@ResponseBody
	@OrgSeriviceToken
	public RepOrgUser queryUserByOrgService(@RequestBody OrgUserParam orgUserParam)  {
		try {
			String url = mtConfig.getOrgUserAd();
			HttpHeaders httpHeaders = new HttpHeaders(); // 设置参数
			httpHeaders.set(CallTokenUtil.ACCESS_TOKEN, CallTokenUtil.getOrgAccesssToken());
			httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
			parameters.add("erpbh", orgUserParam.getErpbh());
			HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(parameters,httpHeaders);			
			ResponseEntity<RepOrgUser> responseEntity = restTemplate.postForEntity(url, httpEntity, RepOrgUser.class);
			RepOrgUser body = responseEntity.getBody();
			log.info(body.getMsg());
			return body;
		} catch (Exception e) {
			RepOrgUser repOrgUser = new RepOrgUser();
			repOrgUser.setCode(RepCode.ERROR_CODE);
			repOrgUser.setMsg(e.getMessage());
			OrgSerException mException = new OrgSerException(e);
			mException.setMsg(repOrgUser);
			throw mException;
		}
	}
}
