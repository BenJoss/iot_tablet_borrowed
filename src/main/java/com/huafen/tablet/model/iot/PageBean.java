package com.huafen.tablet.model.iot;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class PageBean<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4120134091666547284L;
	
	private String source;
	
	@ApiModelProperty(value="楼层号",example = "1F")
	private String floor;
	@ApiModelProperty(value="会议室名称",example = "A2-310")
	private String roomName;
	@ApiModelProperty(value="会议室地址",example = "会议中心")
	private String roomAddr;
	
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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomAddr() {
		return roomAddr;
	}

	public void setRoomAddr(String roomAddr) {
		this.roomAddr = roomAddr;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	
    
    

}
