package com.huafen.tablet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.huafen.tablet.model.chat.CallChatDTO;
import com.huafen.tablet.model.chat.CallMsgDTO;
import com.huafen.tablet.model.chat.CallRmLtShowDTO;
import com.huafen.tablet.model.chat.CallRmStatDTO;
import com.huafen.tablet.model.chat.CallUser;
import com.huafen.tablet.model.iot.IOTRoomDTO;
import com.huafen.tablet.model.iot.IotAddress;
import com.huafen.tablet.model.iot.IotFloor;
import com.huafen.tablet.model.iot.IotRMClMdDTO;
import com.huafen.tablet.model.iot.IotRMClaTypDTO;
import com.huafen.tablet.model.iot.IotRMClassDTO;
import com.huafen.tablet.model.iot.IotRMCoMdDTO;
import com.huafen.tablet.model.iot.IotRMContDTO;
import com.huafen.tablet.model.iot.IotRMContInfoDTO;
import com.huafen.tablet.model.iot.PageBean;
import com.huafen.tablet.model.param.CallAuthParam;
import com.huafen.tablet.model.param.CallMTParam;
import com.huafen.tablet.model.param.CallRmParam;
import com.huafen.tablet.model.param.ChatParam;
import com.huafen.tablet.model.param.IotConfig;
import com.huafen.tablet.model.param.IotRMClParam;
import com.huafen.tablet.model.param.IotRMConParam;
import com.huafen.tablet.model.room.CallMTInfoDAO;
import com.huafen.tablet.model.room.CallRoomDAO;

@Mapper
public interface CallMapper {

	
	List<IotRMClaTypDTO> queryIotRoomClass(IotRMClParam iotRMClParam);
	
	List<IotRMContInfoDTO> queryIotRoomCont(IotRMConParam iotRMConParam);
	
	List<CallMsgDTO> queryCallChatList(ChatParam chatParam);
	
	CallRmLtShowDTO queryCallRoomChatList(ChatParam chatParam);
	
	CallRmStatDTO queryCallRmStatInfo(CallRmParam callRmParam);
	
	List<CallRoomDAO> queryCallRoomList(CallMTParam callMTParam);
	
	List<CallRoomDAO> queryCallRoomPageList(PageBean<IOTRoomDTO> pageBean);
	
	List<IotFloor> queryIotFloorList(IotConfig iotConfig);
	
	List<IotAddress> queryIotAddrList(IotConfig iotConfig);
	
	int countCallRoom(ChatParam chatParam);
	
	int  countIOTRoom(CallMTParam callMTParam);
	
	int  countCallRoomPage(PageBean<IOTRoomDTO> pageBean);
	
	int countCallMeet(CallMTParam callMTParam);
	
	int  countCallUser(CallAuthParam callAuthParam);
	
	int updateCallRoom(CallRoomDAO callRoomDAO);
	
	int updateCallMeet(CallMTInfoDAO callMTInfoDAO);
	
	int updateIOTRoomClass(IotRMClMdDTO iotRMClMdDTO);
	
	int updateIOTRMCont(IotRMCoMdDTO iotRMCoMdDTO);
	
	int insertCallChat(CallChatDTO callChat);
	
	int insertCallRoom(CallRoomDAO callRoomDAO);
	
	int insertCallMTInfo(CallMTInfoDAO callMTInfoDAO);
	
	int insertIOTClass(IotRMClassDTO iotRMClassDTO);
	
	int insertIOTConet(IotRMContDTO iotRMContDTO);
	
	int insertCallUser(CallUser callUser);
	
	int deleteIOTMTInfo(CallMTParam callMTParam);
}
