package com.huafen.tablet.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.huafen.tablet.config.RepCode;
import com.huafen.tablet.mapper.DeviceBorrowMapper;
import com.huafen.tablet.mapper.DevicePartitionMapper;
import com.huafen.tablet.model.apply.IotTablBorroCahe;
import com.huafen.tablet.model.cache.IotBorrowDTO;
import com.huafen.tablet.model.his.IotMeetCahe;
import com.huafen.tablet.model.his.IotTablBorroCaheHis;
import com.huafen.tablet.model.meet.CallRmStatDTO;
import com.huafen.tablet.model.param.BorrowHisParam;
import com.huafen.tablet.model.param.IotBorrowHisParam;
import com.huafen.tablet.model.param.PartParam;
import com.huafen.tablet.model.partition.PartitionDTO;
import com.huafen.tablet.model.req.ReposeDTO;
import com.huafen.tablet.service.IoTDeviceBorrowCacheSerivce;
import com.huafen.tablet.util.DeviceCacheUtil;
import com.huafen.tablet.util.IoTDevUtil;
@Service("ioTDeviceBorrowCacheSerivce")
public class IoTDeviceBorrowCacheSerivceImpl implements IoTDeviceBorrowCacheSerivce{

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(IoTDeviceBorrowCacheSerivceImpl.class);
	@Autowired
	private DeviceBorrowMapper deviceBorrowMapper;
    @Resource
	private CacheManager cacheManager;
	@Autowired
	private DevicePartitionMapper devicePartitionMapper;

    
	@Override
	public void loadMeetingInfoCache() {
		
		try {
			
			BorrowHisParam borrowHisParam = new BorrowHisParam();
			borrowHisParam.setStartNum(-360);
			borrowHisParam.setEndDayNum(360);
			borrowHisParam.setShowNum(100000);
			 Cache cache = cacheManager.getCache(DeviceCacheUtil.MEET_ING_CACHE);
			 IotMeetCahe iotObject = 
					 cache.get(DeviceCacheUtil.IOT_MEET_ING_CAHE,IotMeetCahe.class);
			 if (iotObject == null) {
				 IotMeetCahe iotMeetCahe  = new IotMeetCahe();
				 iotMeetCahe.setCallRmStatList(new ArrayList<CallRmStatDTO>());
			     iotObject = iotMeetCahe;
		   }
			 List<CallRmStatDTO> resultCallRmStatList = deviceBorrowMapper.queryCallRmStatInfo(borrowHisParam);
			 iotObject.setCallRmStatList(resultCallRmStatList);
			 cache.put(DeviceCacheUtil.IOT_MEET_ING_CAHE, iotObject);

			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}


	@Override
	public void loadBorrowInfoCache() {
		try {
			  // 显示所有的Cache空间
			 Cache cache = cacheManager.getCache(DeviceCacheUtil.BORROW_CACHE);
			 IotTablBorroCaheHis iotObject = 
					 cache.get(DeviceCacheUtil.IOT_TABL_BORRO_CAHE,IotTablBorroCaheHis.class);
			 if (iotObject == null) {
				 IotTablBorroCaheHis iotTablBorroCaheHis  = new IotTablBorroCaheHis();
				 iotTablBorroCaheHis.setIotTablBorroCaheList(new ArrayList<IotTablBorroCahe>());
				 iotTablBorroCaheHis.setPartitionList(new ArrayList<String>());
				 iotObject = iotTablBorroCaheHis;
			}
			 List<String> partitionOldList = iotObject.getPartitionList();
			 if (!partitionOldList.isEmpty()) {
				
			 }
//			 for( String partitionOld: partitionOldList) {
//				  
//			 }
			 PartParam partParam = new PartParam();
			 partParam.setPartTable(IoTDevUtil.T_IOT_DEVICE_BORROW);
			 List<PartitionDTO>  partitionList =  devicePartitionMapper.queryPartitionInfo(partParam);
			 if (partitionList.isEmpty()) {
				return;
			}
			
			 PartitionDTO partition = partitionList.get(0);
			 partParam.setPartName(partition.getPartName());
			 IotBorrowDTO  iotBorrowDTO = deviceBorrowMapper.countBorrowMaxAndMinNum(partParam);
			 if (iotBorrowDTO == null) {
				return;
			}
			 int maxNum = iotBorrowDTO.getMaxNum();
			 int minNum = iotBorrowDTO.getMinNum();
			 List<IotTablBorroCahe> cacheList = iotObject.getIotTablBorroCaheList();
			 cacheList.clear();
			 IotBorrowHisParam iotBorrowHisParam = new IotBorrowHisParam();
			 while (minNum <= maxNum) {
				 int startNum = minNum;
				 int endNum = minNum + DeviceCacheUtil.BORROW_CACHE_LIMIT;
				 iotBorrowHisParam.setStartNum(startNum);
				 iotBorrowHisParam.setEndNum(endNum);
				 iotBorrowHisParam.setLimitNum(DeviceCacheUtil.BORROW_CACHE_LIMIT);
				 List<IotTablBorroCahe> iotTablBorroCaheList = deviceBorrowMapper.queryBorrowHisCacheInfo(iotBorrowHisParam);
				 cacheList.addAll(iotTablBorroCaheList);
				 minNum = endNum + 1;
			}
			 iotObject.setIotTablBorroCaheList(cacheList);
			 cache.put(DeviceCacheUtil.IOT_TABL_BORRO_CAHE, iotObject);
			 
//			 for (PartitionDTO partition :partitionList) {
//					 
//					
//				}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
	}
	   /**
     * list过滤会议名称
     * @param list
     * @param finalSearch
     * @return
     */
    private List<CallRmStatDTO> filterMeetInfo(List<CallRmStatDTO> list,String finalSearch,int filterType){
        if(filterType==1){
            list = list.stream()
                    .filter(meetDto -> meetDto.getMeetName().contains(finalSearch))
                    .collect(Collectors.toList());
        }else if(filterType==2){
            list = list.stream()
                    .filter(meetDto -> meetDto.getMeetStat().contains(finalSearch))
                    .collect(Collectors.toList());
        }else{
            list = list.stream()
                    .filter(meetDto -> meetDto.getMeetTime().contains(finalSearch))
                    .collect(Collectors.toList());
        }
        return list;
    }
   

	@Override
	public ReposeDTO<List<CallRmStatDTO>> queryMeetingInfoByCache(String meetName) {
		ReposeDTO<List<CallRmStatDTO>>  reposeDTO = new ReposeDTO<List<CallRmStatDTO>>();
		try {
			 Cache cache = cacheManager.getCache(DeviceCacheUtil.MEET_ING_CACHE);
			 IotMeetCahe iotObject = 
					 cache.get(DeviceCacheUtil.IOT_MEET_ING_CAHE,IotMeetCahe.class);
			 if (iotObject == null) {
					BorrowHisParam borrowHisParam = new BorrowHisParam();
					borrowHisParam.setStartNum(-360);
					borrowHisParam.setEndDayNum(360);
					borrowHisParam.setShowNum(5000);
					List<CallRmStatDTO>  resultCallRmStatList = deviceBorrowMapper.queryCallRmStatInfo(borrowHisParam);
					if (meetName != null && !"".equals(meetName)) {
						List<CallRmStatDTO>  reslut =	this.filterMeetInfo(resultCallRmStatList, meetName, 1);
						reposeDTO.setResult(reslut);
					}else {
						List<CallRmStatDTO> resultList = resultCallRmStatList.subList(0, 100);
						reposeDTO.setResult(resultList);
					}
					 IotMeetCahe iotMeetCahe  = new IotMeetCahe();
					 iotMeetCahe.setCallRmStatList(new ArrayList<CallRmStatDTO>());
					 iotObject = iotMeetCahe;
					 iotObject.setCallRmStatList(resultCallRmStatList);
					 cache.put(DeviceCacheUtil.IOT_MEET_ING_CAHE, iotObject);
			}else {
				List<CallRmStatDTO>  callRmStatList = iotObject.getCallRmStatList();
				if (meetName != null && !"".equals(meetName)) {
					List<CallRmStatDTO>  reslut =	this.filterMeetInfo(callRmStatList, meetName, 1);
					reposeDTO.setResult(reslut);
				}else {
					int size = 100;
					if (callRmStatList.size() < 100) {
						 size = callRmStatList.size();
					}
					List<CallRmStatDTO> resultCallRmStatList = callRmStatList.subList(0, size);
					reposeDTO.setResult(resultCallRmStatList);
				}
			}
			reposeDTO.setRepCode(RepCode.SUCCESS_CODE);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return reposeDTO;
	}

	
	
}
