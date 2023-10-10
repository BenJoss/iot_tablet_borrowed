package com.huafen.tablet.model.iot;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class PageDAO <T> extends PageBean<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2098863752868836963L;
	
	@ApiModelProperty(value="当前页面的数据集合",example = "List")
    private List<T> data; //当前页面的数据集合

	
	
    
}
