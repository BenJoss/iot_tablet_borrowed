package com.huafen.tablet.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.huafen.tablet.mapper.CallMapper;
import com.huafen.tablet.model.config.MqttProperties;
import com.huafen.tablet.model.iot.CallIoTDevice;
import com.huafen.tablet.model.iot.CallIotFloor;
import com.huafen.tablet.model.param.CallMTParam;
import com.huafen.tablet.model.room.CallRoomDAO;
import com.huafen.tablet.service.CallIotService;
import com.huafen.tablet.util.CallCacheUtil;
import com.huafen.tablet.util.CallRMUtil;
import com.huafen.tablet.util.MqttUtil;
import com.huafen.tablet.websocket.WSIotServer;

@Service("callIotService")
public class CallIotServiceImpl implements CallIotService{

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(CallIotServiceImpl.class);
	@Autowired
	private WSIotServer webSocket;
	@Autowired
	private CallMapper callMapper;
    @Autowired
    private MqttProperties mqttProperties;
	@Override
	public void loadCallDeriveSer() {
		try {
			if (null == CallCacheUtil.getInstance().get(CallRMUtil.IOT_DEVICE_KEY)) {
				CallMTParam callMTParam = new CallMTParam();
				callMTParam.setSource(CallRMUtil.MT_ROOM_SOURCE);
				List<CallRoomDAO> callRoomList = callMapper.queryCallRoomList(callMTParam);
				if(callRoomList != null) {
					Map<Integer, List<CallRoomDAO>> groupByMap = callRoomList.stream()
							.sorted(Comparator.comparing(iteam -> iteam.getOrder()))
							.collect(Collectors.groupingBy(iteam -> iteam.getOrder(),TreeMap::new, Collectors.toList()));
					
					List<CallIotFloor> callIotFloorList = new ArrayList<CallIotFloor>();
					
					for (Map.Entry<Integer, List<CallRoomDAO>> entry : groupByMap.entrySet()) {
						     CallIotFloor  callIotFloor = new CallIotFloor();
						     List<CallIoTDevice> callIoTDeviceList = new ArrayList<CallIoTDevice>();
						     List<CallRoomDAO> iotList = entry.getValue();
						     if (!iotList.isEmpty()) {
						    	  CallRoomDAO  item =  iotList.get(0);
						    	  callIotFloor.setRoomFloor(item.getFloor());
						    	  for(CallRoomDAO callRoom: iotList) {
						    		  CallIoTDevice callIoTDevice = new CallIoTDevice();
						    		  callIoTDevice.setRoomID(callRoom.getRoomID());
									  callIoTDevice.setRoomName(callRoom.getRoomName()); 
									  callIoTDevice.setRoomDoLkStat("");
									  callIoTDevice.setRoomHum("");
									  callIoTDevice.setRoomLhtStat("");
									  callIoTDevice.setRoomTemp("");
									  callIoTDeviceList.add(callIoTDevice);
						    	   }
						    	  callIotFloor.setCallIoTDeviceList(callIoTDeviceList);
						    	  callIotFloorList.add(callIotFloor);
						    }
					 }
					
					CallCacheUtil.getInstance().put(CallRMUtil.IOT_DEVICE_KEY, callIotFloorList);					
				} 
			}			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void sendIOTMQInfo() {
		try {
			 this.loadCallDeriveSer();
			 Object callIotFloorList =  CallCacheUtil.getInstance().get(CallRMUtil.IOT_DEVICE_KEY);
			 if (null != callIotFloorList) {
				    String[] topics =  mqttProperties.getDefaultTopic().split(":");
					List<CallIotFloor> callIotList = (List<CallIotFloor>) callIotFloorList;
					 for (CallIotFloor item:callIotList) {
						  List<CallIoTDevice> callIoTDeviceList = item.getCallIoTDeviceList();
						  for(CallIoTDevice callIoTDevice:callIoTDeviceList) {
							  String roomName = callIoTDevice.getRoomName();
							  for(String  topic:topics) {
								  String iotValue =  (String)
										CallCacheUtil.getInstance().get(topic);
								   if (topic.contains(roomName)&& topic.contains(MqttUtil.IOT_TEMP)) {
									   callIoTDevice.setRoomTemp(iotValue);
								   }
								   
								   if (topic.contains(roomName)&& topic.contains(MqttUtil.IOT_HUM)) {
									   callIoTDevice.setRoomHum(iotValue);
								   }
								  
							  }
							  
						  }
					}
			
			    Map<String, Object> resultMap = new HashMap<String, Object>();
			    resultMap.put(CallRMUtil.WS_TYPE, CallRMUtil.CALL_IOT);
	    	    resultMap.put(CallRMUtil.RESULT, callIotList);
			    String message = JSON.toJSONString(resultMap);
			    webSocket.sendAllMessage(message);			
			 }
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public void sendRoomDisplayList() {
		try {
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
	}

	
	
}
