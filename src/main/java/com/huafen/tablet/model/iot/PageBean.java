package com.huafen.tablet.model.iot;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class PageBean<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4120134091666547284L;
	
	@ApiModelProperty(value="借用时间 开始时间",required = false,example = "2023-09-14 08:30:00")
	private String startTime;
	@ApiModelProperty(value="借用时间 结束时间",required = false,example = "2023-09-14 10:30:00")
	private String endTime;
	@ApiModelProperty(value="借出用户ID",required = true,example = "4600072255")
	private String userID;
	@ApiModelProperty(value="借出用户名称",required = true,example = "维康")
	private String userName;
	@ApiModelProperty(value="借用状态",example = "1：待借用、2：借用中、3：完结、4：异常、5：取消",required = false)
	private List<String> borrowedStatusList;
	@ApiModelProperty(value="借用状态",example = "1：待借用、2：借用中、3：完结、4：异常、5：取消",required = false)
	private String borrowedStatus;
	@ApiModelProperty(value="当前页数",example = "1")
    private int pageNum; //当前页数
	@ApiModelProperty(value="每页显示数",example = "10")
    private int pageSize; //每页显示数
	@ApiModelProperty(value="总页数",example = "0")
    private int totalPage; //总页数
	@ApiModelProperty(value="总的记录数",example = "0")
    private int totalRecord; //总的记录数
	// @ApiModelProperty(value="当前页面的数据集合",example = "List")
    private List<T> data; //当前页面的数据集合
    private int start;
    private int end;

    public PageBean() {
    	
    }

    public PageBean(int pageNum, int pageSize, int totalRecord) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        //计算总页数
        this.totalPage=totalRecord%pageSize==0?(totalRecord/pageSize):(totalRecord/pageSize+1);
        //计算每页的起始下标
        this.start=(pageNum-1)*pageSize;
        this.end=this.start+pageSize;
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

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<String> getBorrowedStatusList() {
		return borrowedStatusList;
	}

	public void setBorrowedStatusList(List<String> borrowedStatusList) {
		this.borrowedStatusList = borrowedStatusList;
	}

	public String getBorrowedStatus() {
		return borrowedStatus;
	}

	public void setBorrowedStatus(String borrowedStatus) {
		this.borrowedStatus = borrowedStatus;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	
    
    

}
