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
import com.huafen.tablet.model.his.IotMeetCahe;
import com.huafen.tablet.model.meet.CallRmStatDTO;
import com.huafen.tablet.model.param.BorrowHisParam;
import com.huafen.tablet.model.req.ReposeDTO;
import com.huafen.tablet.service.IoTDeviceBorrowCacheSerivce;
import com.huafen.tablet.util.DeviceCacheUtil;
@Service("ioTDeviceBorrowCacheSerivce")
public class IoTDeviceBorrowCacheSerivceImpl implements IoTDeviceBorrowCacheSerivce{

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(IoTDeviceBorrowCacheSerivceImpl.class);
	@Autowired
	private DeviceBorrowMapper deviceBorrowMapper;
     @Resource
	 private CacheManager cacheManager;
	
	
	@Override
	public void loadBorrowInfoCache() {
		
		try {
			
			BorrowHisParam borrowHisParam = new BorrowHisParam();
			borrowHisParam.setStartNum(-360);
			borrowHisParam.setEndDayNum(360);
			borrowHisParam.setShowNum(100000);
			 Cache cache = cacheManager.getCache(DeviceCacheUtil.BORROW_CACHE);
			 IotMeetCahe iotObject = 
					 cache.get(DeviceCacheUtil.IOT_TABL_BORRO_CAHEHIS,IotMeetCahe.class);
			 if (iotObject == null) {
				 IotMeetCahe iotMeetCahe  = new IotMeetCahe();
				 iotMeetCahe.setCallRmStatList(new ArrayList<CallRmStatDTO>());
			   iotObject = iotMeetCahe;
		}
			 List<CallRmStatDTO> resultCallRmStatList = deviceBorrowMapper.queryCallRmStatInfo(borrowHisParam);
			 iotObject.setCallRmStatList(resultCallRmStatList);
			 cache.put(DeviceCacheUtil.IOT_TABL_BORRO_CAHEHIS, iotObject);
//		 Integer  max =	deviceBorrowMapper.countBorrowMaxNum(null);
//		 if (max == null || max == 0) {
//			return;
//		 }else {
//			  // 显示所有的Cache空间
//			 Cache cache = cacheManager.getCache(DeviceCacheUtil.BORROW_CACHE);
//			 IotTablBorroCaheHis iotObject = 
//					 cache.get(DeviceCacheUtil.IOT_TABL_BORRO_CAHEHIS,IotTablBorroCaheHis.class);
//			 if (iotObject == null) {
//				 IotTablBorroCaheHis iotTablBorroCaheHis  = new IotTablBorroCaheHis();
//				 iotTablBorroCaheHis.setIotTablBorroCaheList(new ArrayList<IotTablBorroCahe>());
//				 iotObject = iotTablBorroCaheHis;
//			}
//			 List<IotTablBorroCahe> cacheList = iotObject.getIotTablBorroCaheList();
//			 IotBorrowHisParam iotBorrowHisParam = new IotBorrowHisParam();
//			 while (max > 0) {
//				 int endNum = max;
//				 int startNum = endNum - DeviceCacheUtil.BORROW_CACHE_LIMIT;
//				 iotBorrowHisParam.setStartNum(startNum);
//				 iotBorrowHisParam.setEndNum(endNum);
//				 iotBorrowHisParam.setLimitNum(DeviceCacheUtil.BORROW_CACHE_LIMIT);
//				 List<IotTablBorroCahe> iotTablBorroCaheList = deviceBorrowMapper.queryBorrowHisCacheInfo(iotBorrowHisParam);
//				 cacheList.addAll(iotTablBorroCaheList);
//				 max = max - DeviceCacheUtil.BORROW_CACHE_LIMIT;
//			}
//			 iotObject.setIotTablBorroCaheList(cacheList);
//			 cache.put(DeviceCacheUtil.IOT_TABL_BORRO_CAHEHIS, iotObject);
//			 
//		}
			
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
			 Cache cache = cacheManager.getCache(DeviceCacheUtil.BORROW_CACHE);
			 IotMeetCahe iotObject = 
					 cache.get(DeviceCacheUtil.IOT_TABL_BORRO_CAHEHIS,IotMeetCahe.class);
			 if (iotObject == null) {
					reposeDTO.setRepCode(RepCode.SUCCESS_CODE);
			}else {
				List<CallRmStatDTO>  callRmStatList = iotObject.getCallRmStatList();
				if (meetName != null && !"".equals(meetName)) {
					List<CallRmStatDTO>  reslut =	this.filterMeetInfo(callRmStatList, meetName, 1);
					reposeDTO.setResult(reslut);
				}else {
					List<CallRmStatDTO> resultCallRmStatList = callRmStatList.subList(0, 100);
					reposeDTO.setResult(resultCallRmStatList);
				}
			}
			 
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return reposeDTO;
	}
	
	
}
