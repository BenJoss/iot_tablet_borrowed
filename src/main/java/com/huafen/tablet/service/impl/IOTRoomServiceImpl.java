package com.huafen.tablet.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.huafen.tablet.mapper.CallMapper;
import com.huafen.tablet.mapper.IotMapper;
import com.huafen.tablet.model.iot.IOTRoomDTO;
import com.huafen.tablet.model.iot.IotAddress;
import com.huafen.tablet.model.iot.IotDeviceDAO;
import com.huafen.tablet.model.iot.IotDeviceDTO;
import com.huafen.tablet.model.iot.IotFloor;
import com.huafen.tablet.model.iot.IotRMClMdDTO;
import com.huafen.tablet.model.iot.IotRMClaTypDTO;
import com.huafen.tablet.model.iot.IotRMClassDTO;
import com.huafen.tablet.model.iot.IotRMCoMdDTO;
import com.huafen.tablet.model.iot.IotRMContDTO;
import com.huafen.tablet.model.iot.IotRMContInfoDTO;
import com.huafen.tablet.model.iot.PageBean;
import com.huafen.tablet.model.param.CallMTParam;
import com.huafen.tablet.model.param.IotClassParam;
import com.huafen.tablet.model.param.IotConParam;
import com.huafen.tablet.model.param.IotConfig;
import com.huafen.tablet.model.param.IotDeviceMdPara;
import com.huafen.tablet.model.param.IotDeviceParam;
import com.huafen.tablet.model.param.IotRMClParam;
import com.huafen.tablet.model.param.IotRMConParam;
import com.huafen.tablet.model.room.CallRoomDAO;
import com.huafen.tablet.service.IOTRoomService;
import com.huafen.tablet.util.CallRMUtil;
import com.huafen.tablet.util.RandUtil;

@Service("iotRoomService")
public class IOTRoomServiceImpl implements IOTRoomService{
	
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(IOTRoomServiceImpl.class);
	
	@Autowired
	private CallMapper callMapper;

	@Autowired
	private IotMapper iotMapper;
	
	
	@Override
	public int saveIOTRoomInfo(IOTRoomDTO iotRoomDTO,MultipartFile file) {
		try {
			String roomImg = Base64.encodeBase64String(file.getBytes());
			CallRoomDAO callRoomDAO = new CallRoomDAO();
			callRoomDAO.setRoomID(RandUtil.generateRandomString());
			callRoomDAO.setRoomName(iotRoomDTO.getRoomName());
			callRoomDAO.setRoomAddr(iotRoomDTO.getRoomAddr());
			callRoomDAO.setRoomImg(iotRoomDTO.getRoomImg());
			callRoomDAO.setFloor(iotRoomDTO.getFloor());
			callRoomDAO.setRoomImg(roomImg);
			callRoomDAO.setSource(CallRMUtil.IOT_ROOM_SOURCE);
			callMapper.insertCallRoom(callRoomDAO);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return 0;
	}

	@Override
	public List<IOTRoomDTO> queryIotRoomList(CallMTParam callMTParam) {
		try {
			callMTParam.setSource(CallRMUtil.IOT_ROOM_SOURCE);
			if(CallRMUtil.IOT_ROOM_ALL.equals(callMTParam.getRoomAddr())) {
				callMTParam.setRoomAddr(null);
			}
			if(CallRMUtil.IOT_ROOM_ALL.equals(callMTParam.getFloor())) {
				callMTParam.setFloor(null);
			}			
			List<IOTRoomDTO> iotRoomList = new ArrayList<IOTRoomDTO>();
			List<CallRoomDAO> callRoomList = callMapper.queryCallRoomList(callMTParam);
			for(CallRoomDAO item :callRoomList) {
				IOTRoomDTO iotRoomDTO  =  new  IOTRoomDTO();
				iotRoomDTO.setFloor(item.getFloor());
				iotRoomDTO.setRoomAddr(item.getRoomAddr());
				iotRoomDTO.setRoomID(item.getRoomID());
				iotRoomDTO.setRoomName(item.getRoomName());
				iotRoomDTO.setRoomImg(item.getRoomImg());
				iotRoomList.add(iotRoomDTO);
			}
			return iotRoomList;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return new ArrayList<IOTRoomDTO>();
	}

	@Override
	public int deleteIOTMTInfo(CallMTParam callMTParam) {
		try {
			callMTParam.setSource(CallRMUtil.IOT_ROOM_SOURCE);
			callMapper.deleteIOTMTInfo(callMTParam);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return 0;
	}

	@Override
	public int updateIOTRoom(IOTRoomDTO iotRoomDTO,MultipartFile file) {
		try {
			CallRoomDAO callRoomDAO= new CallRoomDAO();
			if(file != null && file.getBytes().length>0) {
				String roomImg = Base64.encodeBase64String(file.getBytes());
				callRoomDAO.setRoomImg(roomImg);
			}
			callRoomDAO.setSource(CallRMUtil.IOT_ROOM_SOURCE);
			callRoomDAO.setFloor(iotRoomDTO.getFloor());
			callRoomDAO.setRoomAddr(iotRoomDTO.getRoomAddr());
			callRoomDAO.setRoomName(iotRoomDTO.getRoomName());
			callRoomDAO.setRoomID(iotRoomDTO.getRoomID());
			return callMapper.updateCallRoom(callRoomDAO);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return 0;
	}

	@Override
	public void loadIOTDeriveServ() {
	    try {
	    	
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
	}

	@Override
	public Map<String, Object> queryIotFloorAddr() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
	    try {
	    	IotConfig iotConfig = new IotConfig();
	    	iotConfig.setType(CallRMUtil.IOT_FLOOR);
	    	List<IotFloor> iotFloorList = callMapper.queryIotFloorList(iotConfig);
	    	iotConfig.setType(CallRMUtil.IOT_ADDRESS);
	    	List<IotAddress> iotAddresseList = callMapper.queryIotAddrList(iotConfig);
	    	resultMap.put("iotFloorList", iotFloorList);
	    	resultMap.put("iotAddresseList", iotAddresseList);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return resultMap;
	}

	@Override
	public List<IOTRoomDTO> queryAllIotRoomList() {
		try {
		CallMTParam callMTParam = new CallMTParam();
		callMTParam.setSource(CallRMUtil.IOT_ROOM_SOURCE);			
		List<IOTRoomDTO> iotRoomList = new ArrayList<IOTRoomDTO>();
		List<CallRoomDAO> callRoomList = callMapper.queryCallRoomList(callMTParam);
		for(CallRoomDAO item :callRoomList) {
			IOTRoomDTO iotRoomDTO  =  new  IOTRoomDTO();
			iotRoomDTO.setFloor(item.getFloor());
			iotRoomDTO.setRoomAddr(item.getRoomAddr());
			iotRoomDTO.setRoomID(item.getRoomID());
			iotRoomDTO.setRoomName(item.getRoomName());
			iotRoomList.add(iotRoomDTO);
		}
		return iotRoomList;
	} catch (Exception e) {
		log.error(e.getMessage());
	}
	return new ArrayList<IOTRoomDTO>();
  }

	@Override
	public PageBean<IOTRoomDTO> queryIotRoomByPage(PageBean<IOTRoomDTO> pageBean) {
		try {
			if(CallRMUtil.IOT_ROOM_ALL.equals(pageBean.getRoomAddr())) {
				pageBean.setRoomAddr(null);
			}
			if(CallRMUtil.IOT_ROOM_ALL.equals(pageBean.getFloor())) {
				pageBean.setFloor(null);
			}	
			int totalRecord = 0;
			if (pageBean.getTotalRecord() == 0) {
				pageBean.setSource(CallRMUtil.IOT_ROOM_SOURCE);
				totalRecord = callMapper.countCallRoomPage(pageBean); 
			}else {
				totalRecord = pageBean.getTotalRecord();
			}
			PageBean<IOTRoomDTO> pageParam = new PageBean<>
			(pageBean.getPageNum(),pageBean.getPageSize(),totalRecord);
			pageParam.setFloor(pageBean.getFloor());
			pageParam.setRoomAddr(pageBean.getRoomAddr());
			pageParam.setRoomName(pageBean.getRoomName());
			pageParam.setSource(CallRMUtil.IOT_ROOM_SOURCE);
			List<CallRoomDAO>  dataCallList = callMapper.queryCallRoomPageList(pageParam);
			List<IOTRoomDTO> dataList = new ArrayList<IOTRoomDTO>();
			for (CallRoomDAO callRoomDAO : dataCallList) {
				 String roomID = callRoomDAO.getRoomID();
				 IotClassParam  iotClassParam = new IotClassParam();
				 iotClassParam.setRoomID(roomID);
				 int roomClassNum = iotMapper.countRmClassNum(iotClassParam);
				 int roomContNum = iotMapper.countRmContNum(iotClassParam);
				 // 组装数据
				 IOTRoomDTO  iotRoomDTO = new IOTRoomDTO();
				 iotRoomDTO.setRoomClassNum(roomClassNum);
				 iotRoomDTO.setRoomContNum(roomContNum);
				 iotRoomDTO.setFloor(callRoomDAO.getFloor());
				 iotRoomDTO.setRoomAddr(callRoomDAO.getRoomAddr());
				 iotRoomDTO.setRoomID(roomID);
				 iotRoomDTO.setRoomName(callRoomDAO.getRoomName());
				 iotRoomDTO.setRoomImg(callRoomDAO.getRoomImg());
				 dataList.add(iotRoomDTO);
			}
			pageParam.setTotalRecord(totalRecord);
			pageParam.setData(dataList);
			return pageParam;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return pageBean;
	}

	@Override
	public String insertIOTClass(IotRMClassDTO iotRMClassDTO) {
	  try {
		  String id = RandUtil.generateRandomString();
		  iotRMClassDTO.setClassID(id);
		  iotRMClassDTO.setClassType(CallRMUtil.CLASS_TYPE);
		  if (callMapper.insertIOTClass(iotRMClassDTO) > 0) {
			  return id;
		  }
	  } catch (Exception e) {
		log.error(e.getMessage());
	   }
		return "";
	}

	
	@Override
	public String insertIOTConet(IotRMContDTO iotRMContDTO) {
		try {
			String contID = RandUtil.generateRandomString();
			iotRMContDTO.setContID(contID);
			iotRMContDTO.setContType(CallRMUtil.IOT_PAGE);
			List<IotDeviceDTO> iotDeviceList = iotRMContDTO.getIotDeviceList();
		     if (iotDeviceList != null) {
		    	 for (IotDeviceDTO iotDeviceDTO : iotDeviceList) {
			    	   IotDeviceDAO iotDeviceDAO = new IotDeviceDAO();
			    	   iotDeviceDAO.setContID(contID);
			    	   iotDeviceDAO.setDeviceID(iotDeviceDTO.getDeviceID());
			    	   iotDeviceDAO.setDeviceModel(iotDeviceDTO.getDeviceModel());
			    	   iotDeviceDAO.setDeviceName(iotDeviceDTO.getDeviceName());
			    	   iotDeviceDAO.setDeviceType(iotDeviceDTO.getDeviceType());
			    	   iotMapper.insertIotContDevice(iotDeviceDAO);
				  }
			}
			if (callMapper.insertIOTConet(iotRMContDTO) > 0) {
				  return contID;
			  }
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "";
	}

	
	@Override
	public List<IotRMClaTypDTO> queryIotRoomClassList(IotRMClParam iotRMClParam) {
		try {
			
			List<IotRMClaTypDTO>  iotRMClaTypList = callMapper.queryIotRoomClass(iotRMClParam);
			if (!iotRMClaTypList.isEmpty()) {
				for (IotRMClaTypDTO item: iotRMClaTypList) {
					 IotRMConParam  iotRMConParam = new IotRMConParam();
					 iotRMConParam.setClassID(item.getClassID());
					 List<IotRMContInfoDTO> iotRMContInfoList =  callMapper.queryIotRoomCont(iotRMConParam);
					 item.setList(iotRMContInfoList);
					 item.setNumber(iotRMContInfoList.size());
					 if (!iotRMContInfoList.isEmpty()) {
						 for(IotRMContInfoDTO  iotRMContInfoDTO :  iotRMContInfoList) {
							 IotDeviceParam iotDeviceParam = new IotDeviceParam();
							 iotDeviceParam.setContID(iotRMContInfoDTO.getContID());
							 List<IotDeviceDTO> iotDeviceList = iotMapper.queryIotContDevice(iotDeviceParam);
							 iotRMContInfoDTO.setIotDeviceList(iotDeviceList);
						 }
					}
				}
			}
			 return iotRMClaTypList;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return new ArrayList<IotRMClaTypDTO>();
	}

	
	@Override
	public int updateIOTRoomClass(IotRMClMdDTO iotRMClMdDTO) {
		 try {
			 callMapper.updateIOTRoomClass(iotRMClMdDTO);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			return 0;
	}

	@Transactional(rollbackFor = SQLException.class) 
	@Override
	public int updateIOTRMCont(IotRMCoMdDTO iotRMCoMdDTO) {
		 try {
			 String contID = iotRMCoMdDTO.getContID();
			 IotDeviceMdPara iotDeviceMdPara = new IotDeviceMdPara();
			 iotDeviceMdPara.setContID(contID);
		     iotMapper.deleteIotDevice(iotDeviceMdPara);
		     //
		     List<IotDeviceDTO> iotDeviceList = iotRMCoMdDTO.getIotDeviceList();
		     if (iotDeviceList != null) {
		    	 for (IotDeviceDTO iotDeviceDTO : iotDeviceList) {
			    	   IotDeviceDAO iotDeviceDAO = new IotDeviceDAO();
			    	   iotDeviceDAO.setContID(contID);
			    	   iotDeviceDAO.setDeviceID(iotDeviceDTO.getDeviceID());
			    	   iotDeviceDAO.setDeviceModel(iotDeviceDTO.getDeviceModel());
			    	   iotDeviceDAO.setDeviceName(iotDeviceDTO.getDeviceName());
			    	   iotDeviceDAO.setDeviceType(iotDeviceDTO.getDeviceType());
			    	   iotMapper.insertIotContDevice(iotDeviceDAO);
				  }
			}		     
			 callMapper.updateIOTRMCont(iotRMCoMdDTO);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			return 0;
	}

	@Override
	public int saveIotContDevice(IotDeviceDAO iotDeviceDAO) {
		try {
		    return	iotMapper.insertIotContDevice(iotDeviceDAO);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return 0;
	}

	@Override
	public int deleteIotDevice(IotDeviceMdPara iotDeviceMdPara) {
		try {
			 List<String> deviceIDList = iotDeviceMdPara.getDeviceIDList();
			 for (String deviceID : deviceIDList) {
				 iotDeviceMdPara.setDeviceID(deviceID);
				 return	iotMapper.deleteIotDevice(iotDeviceMdPara);
		 	}			 
		} catch (Exception e) {
			log.error(e.getMessage());
			return -1;
		}
		return 0;
	}
	
	@Transactional(rollbackFor = SQLException.class) 
	@Override
	public int deleteIotRmCont(IotConParam iotConParam) {
		try {
			List<String> contIDList = iotConParam.getContIDList();
			for (String contID : contIDList) {
				// 删除设备
				IotDeviceMdPara iotDeviceMdPara = new IotDeviceMdPara();
				iotDeviceMdPara.setContID(contID);
				iotMapper.deleteIotDevice(iotDeviceMdPara);
				// 删除发布页
				iotConParam.setContID(contID);
				iotMapper.deleteIotRmCont(iotConParam);					
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			return -1;
		}
		return 0;
	}

	@Transactional(rollbackFor = SQLException.class) 
	@Override
	public int deleteIotRmClass(IotClassParam iotClassParam) {
		try {
			List<String> classIDList = iotClassParam.getClassIDList();
			for (String classID : classIDList) {
				// 删除发布页
				IotConParam iotConParam = new IotConParam();
				iotConParam.setClassID(classID);
				iotMapper.deleteIotRmCont(iotConParam);
				// 删除设备
				IotDeviceMdPara iotDeviceMdPara = new IotDeviceMdPara();
				iotDeviceMdPara.setClassID(classID);
				iotMapper.deleteIotDevice(iotDeviceMdPara);
				// 删除一级
				iotClassParam.setClassID(classID);
				iotMapper.deleteIotRmClass(iotClassParam);
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			return -1;
		}
		return 0;
	}
	
	
}
