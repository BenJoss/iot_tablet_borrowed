package com.huafen.tablet.model.chat;

import java.io.Serializable;
import java.util.Objects;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("呼叫服务用户信息")
public class CallUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7123463156477090392L;
	
	@ApiModelProperty(value="用户ID",required = true,example = "00000000")
	private String userID;
	@ApiModelProperty(value="用户名称",required = true,example = "赵云")
	private String userName;
	@ApiModelProperty(value="用户角色",required = true,example = "admin")
	private String roleName;
	
	private String chatID;
	
	public CallUser(String chatID, String userName) {
        super();
        this.chatID = chatID;
        this.userName = userName;
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
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getChatID() {
		return chatID;
	}

	public void setChatID(String chatID) {
		this.chatID = chatID;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CallUser callUser = (CallUser) o;
        return userID.equals(callUser.getUserID());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(userID);
    }
	
	
}
