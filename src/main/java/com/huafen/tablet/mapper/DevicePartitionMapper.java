package com.huafen.tablet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.huafen.tablet.model.param.PartParam;
import com.huafen.tablet.model.partition.PartitionDTO;

@Mapper
public interface DevicePartitionMapper {

	List<PartitionDTO> queryPartitionInfo(PartParam partParam);
	
	PartitionDTO queryTablePartitionInfo(PartParam partParam);
	
	int alterTablePartition(PartitionDTO  partitionDTO);
	
	int insertTablePartition(PartitionDTO  partition);
}
