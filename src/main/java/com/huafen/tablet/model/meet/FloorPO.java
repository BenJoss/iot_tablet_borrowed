package com.huafen.tablet.model.meet;

import java.io.Serializable;

public class FloorPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -557290832133451550L;

	private Long id;
	
	private String paramName;
	
	private String paramValue;
	
	private int paramOrder;
	
	private String paramGroup;
	
	private String paramDesc;
	
	private int order;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
		this.paramOrder = Integer.parseInt(paramValue);
	}

	public String getParamGroup() {
		return paramGroup;
	}

	public void setParamGroup(String paramGroup) {
		this.paramGroup = paramGroup;
	}

	public String getParamDesc() {
		return paramDesc;
	}

	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getParamOrder() {
		return paramOrder;
	}

	public void setParamOrder(int paramOrder) {
		this.paramOrder = paramOrder;
	}
	
	
}
