package com.huafen.tablet.model.meet;

import java.io.Serializable;

public class MTInfoPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9115557872951397904L;
	
	private Long roomId;
	
	private Long applyId;
	
	private String roomName;
	
	private String mtName;
	
	private String time;
	
	private String startTime;
	
	private String endTime;
	
	private String pmStartTime;
	
	private String category;
	
	private String videoMeeting;
	
	private Integer personNum;
	
	private String serviceDemand;
	
	private String contactName;
	
	private String contactPhone;
	
	private String dept;

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getMtName() {
		return mtName;
	}

	public void setMtName(String mtName) {
		this.mtName = mtName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPmStartTime() {
		return pmStartTime;
	}

	public void setPmStartTime(String pmStartTime) {
		this.pmStartTime = pmStartTime;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getVideoMeeting() {
		return videoMeeting;
	}

	public void setVideoMeeting(String videoMeeting) {
		this.videoMeeting = videoMeeting;
	}

	public Integer getPersonNum() {
		return personNum;
	}

	public void setPersonNum(Integer personNum) {
		this.personNum = personNum;
	}

	public String getServiceDemand() {
		return serviceDemand;
	}

	public void setServiceDemand(String serviceDemand) {
		this.serviceDemand = serviceDemand;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
	
	
	

}
