package com.huafen.tablet.task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.huafen.tablet.config.RepCode;
import com.huafen.tablet.model.config.MTConfig;
import com.huafen.tablet.model.meet.FloorPO;
import com.huafen.tablet.model.meet.MTCustom;
import com.huafen.tablet.model.meet.MTInfoPO;
import com.huafen.tablet.model.meet.MTResult;
import com.huafen.tablet.model.meet.MTRoomPO;
import com.huafen.tablet.model.req.RepMTInfoPO;
import com.huafen.tablet.model.req.RepMTRoom;
import com.huafen.tablet.model.req.RepMeetPO;
import com.huafen.tablet.model.room.CallMTInfoDAO;
import com.huafen.tablet.model.room.CallRoomDAO;
import com.huafen.tablet.service.CallIotService;
import com.huafen.tablet.service.CallRoomService;
import com.huafen.tablet.service.ShowCallMsgService;
import com.huafen.tablet.util.CallRMUtil;
import com.huafen.tablet.util.DateUtil;

public class CallServiceTask {

	private static ReentrantLock lock = new ReentrantLock();

	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	@Qualifier("callIotService")
	private CallIotService callIotService;

	@Autowired
	@Qualifier("callRoomService")
	private CallRoomService callRoomService;

	@Autowired
	@Qualifier("showCallMsgService")
	private ShowCallMsgService showCallMsgService ;
	
	
	@Autowired
	private MTConfig mtConfig;
	
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(CallServiceTask.class);

	public void syncMeetRoomInfoTask() {
		try {
			lock.lock();
			try {
				
				String url = mtConfig.getFloorUrl();
				// 创建一个请求头对象
				HttpHeaders httpHeaders = new HttpHeaders();
				// 设置参数

				// 创建一个响应体对象
				HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);

				// 发送GET请求
				ResponseEntity<RepMeetPO> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
						RepMeetPO.class);

				// 获取响应对象里的 body 对象
				RepMeetPO body = responseEntity.getBody();

				Integer code = body.getCode();
				if (RepCode.MEET_SUCCESS_CODE == code) {
					List<FloorPO> floorList = body.getResult();
					if (!floorList.isEmpty()) {
						floorList.sort(Comparator.comparing(FloorPO::getParamOrder));
						this.syncMeetRoomByFloor( httpHeaders, floorList);
					}
				}
				this.syncMeetInfo(httpHeaders);
			} catch (Exception e) {
				log.error("执行同步会议信息任务异常:" + e.getMessage());
			}
		} catch (Exception e) {

		} finally {
			lock.unlock();
		}

	}

	public void syncMeetRoomByFloor( HttpHeaders httpHeaders, List<FloorPO> floorList) {
		try {
			String url =  mtConfig.getRoomUrl();
			Map<String, Object> param = new HashMap<String, Object>();
			for (FloorPO floorPO : floorList) {
				Long floorId = floorPO.getId();
				int order = floorPO.getParamOrder();
				param.put("pageIndex", 1);
				param.put("pageSize", 30);
				param.put("floorId", floorId);
				// 发送GET请求
				// 创建一个响应体对象
				HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
				ResponseEntity<RepMTRoom> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
						RepMTRoom.class, param);
				// 获取响应对象里的 body 对象
				RepMTRoom body = responseEntity.getBody();
				Integer code = body.getCode();
				if (RepCode.MEET_SUCCESS_CODE == code) {
					List<MTRoomPO> mRoomList = body.getResult();
					if (!mRoomList.isEmpty()) {
						for (MTRoomPO item : mRoomList) {
							CallRoomDAO callRoomDAO = new CallRoomDAO();
							callRoomDAO.setFloorID(floorId);
							callRoomDAO.setOrder(order);
							callRoomDAO.setFloor(item.getFloor());
							callRoomDAO.setRoomID(String.valueOf(item.getId()));
							callRoomDAO.setRoomName(item.getRoomName());
							callRoomDAO.setRoomAddr(item.getAddr());
							callRoomDAO.setSource(CallRMUtil.MT_ROOM_SOURCE);
							callRoomService.upCallRoomInfo(callRoomDAO);
						}

					}
				}
			}

		} catch (Exception e) {
			log.error("执行同步会议信息任务异常:" + e.getMessage());
		}
	}

	public void syncMeetInfo( HttpHeaders httpHeaders) {
		try {
			String url = mtConfig.getMtinfoUrl();
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			for(int month :DateUtil.MONTHS) {
				  calendar.set(Calendar.MONTH, month);
				  int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
				  for (int day = 1; day <=days; day++) {
					    Long time = DateUtil.getCurrentTime(year, month, day);
					    Map<String, Object> param = new HashMap<String, Object>();
					    param.put("time", time);
					    HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
						ResponseEntity<RepMTInfoPO> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
								RepMTInfoPO.class, param);
						RepMTInfoPO body = responseEntity.getBody();
						Integer code = body.getCode();
						if (RepCode.MEET_SUCCESS_CODE == code) {
							MTResult result = body.getResult();
							ArrayList<MTCustom> customers = result.getCustomers();
							if (customers != null) {
								for (MTCustom item : customers) {
									List<MTInfoPO> customerMsgList = item.getCustomerMsgList();
									if (customerMsgList != null) {
										for (MTInfoPO mtInfoPO : customerMsgList) {
											if (mtInfoPO.getApplyId() != null) {
												String MTTime = mtInfoPO.getTime();
												CallMTInfoDAO callMTInfoDAO = new CallMTInfoDAO();
												if (MTTime  != null && !"".equals(MTTime)) {
													// 2023-09-14 15:13:01
													// time 16:00~18:00
													 String[] timeStart = MTTime.split(DateUtil.TIME_UNIX);
													 String monthStr = String.valueOf(month);
													 String dayStr = String.valueOf(day);
													 if (month < 10) {
														 monthStr = "0"+ monthStr;
													 }
													 if (day < 10) {
														 dayStr = "0"+ dayStr;
													 }
													 String timeStr = String.valueOf(year)+"-"+ monthStr+"-"+ dayStr+" ";
													 String startTime =timeStr + timeStart[0]+":00";
													 String endTime =timeStr + timeStart[1]+":00";
													 callMTInfoDAO.setStartTime(startTime);
												     callMTInfoDAO.setEndTime(endTime);	
												     String meetTime = timeStr+ MTTime;
												     callMTInfoDAO.setMeetTime(meetTime);
												}
												callMTInfoDAO.setRoomID(String.valueOf(mtInfoPO.getRoomId()));
												callMTInfoDAO.setMeetID(String.valueOf(mtInfoPO.getApplyId()));
												callMTInfoDAO.setMeetName(mtInfoPO.getMtName());
												callMTInfoDAO.setMeetCont(mtInfoPO.getCategory());
												callRoomService.upCallMTInfo(callMTInfoDAO);
											}
										}

									}

								}
							}
					}
				}
			}			
		} catch (Exception e) {
			log.error("执行同步会议信息任务异常:" + e.getMessage());
		}
	}

	
	
	
}
