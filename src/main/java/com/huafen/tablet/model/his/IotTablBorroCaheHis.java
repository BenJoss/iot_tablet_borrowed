package com.huafen.tablet.model.his;

import java.io.Serializable;
import java.util.List;

import com.huafen.tablet.model.apply.IotTablBorroCahe;

public class IotTablBorroCaheHis implements Serializable{

	private static final long serialVersionUID = -8573758630675760859L;

	private List<IotTablBorroCahe> iotTablBorroCaheList;

	public List<IotTablBorroCahe> getIotTablBorroCaheList() {
		return iotTablBorroCaheList;
	}

	public void setIotTablBorroCaheList(List<IotTablBorroCahe> iotTablBorroCaheList) {
		this.iotTablBorroCaheList = iotTablBorroCaheList;
	}
	
	
}
