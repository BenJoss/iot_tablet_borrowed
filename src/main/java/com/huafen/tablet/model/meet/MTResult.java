package com.huafen.tablet.model.meet;

import java.io.Serializable;
import java.util.ArrayList;

public class MTResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -736747154123718747L;
	
	private Integer num;
	
	private ArrayList<MTCustom>  customers;

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public ArrayList<MTCustom> getCustomers() {
		return customers;
	}

	public void setCustomers(ArrayList<MTCustom> customers) {
		this.customers = customers;
	}
	
	

}
