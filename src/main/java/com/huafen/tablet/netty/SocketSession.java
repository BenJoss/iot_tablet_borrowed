package com.huafen.tablet.netty;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.huafen.tablet.model.chat.CallUser;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

public class SocketSession {

	public static final AttributeKey<SocketSession> SESSION_KEY = AttributeKey.valueOf("SESSION_KEY");
	 
    /**
               * 用户实现服务端会话管理的核心
     */
    // 通道
    private Channel channel;
    // 用户
    private CallUser callUser;
 
    // session唯一标示
    private final String sessionId;
 
    private String group;
 
    /**
     * session中存储的session 变量属性值
     */
    private Map<String, Object> map = new HashMap<String, Object>();
 
    public SocketSession(Channel channel) {//注意传入参数channel。不同客户端会有不同channel
        this.channel = channel;
        this.sessionId = buildNewSessionId();
        channel.attr(SocketSession.SESSION_KEY).set(this);
    }
 
    // 反向导航
    public static SocketSession getSession(ChannelHandlerContext ctx) {//注意ctx，不同的客户端会有不同ctx
        Channel channel = ctx.channel();
        return channel.attr(SocketSession.SESSION_KEY).get();
    }
 
    // 反向导航
    public static SocketSession getSession(Channel channel) {
        return channel.attr(SocketSession.SESSION_KEY).get();
    }
 
    public String getId() {
        return sessionId;
    }
 
    private static String buildNewSessionId() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }
 
    public synchronized void set(String key, Object value) {
        map.put(key, value);
    }
 
    @SuppressWarnings("unchecked")
	public synchronized <T> T get(String key) {
        return (T) map.get(key);
    }
 
    public boolean isValid() {
        return getCallUser() != null ? true : false;
    }
 
    public CallUser getCallUser() {
		return callUser;
	}

	public void setCallUser(CallUser callUser) {
		this.callUser = callUser;
	}

	public String getGroup() {
        return group;
    }
 
    public void setGroup(String group) {
        this.group = group;
    }
 
    public Channel getChannel() {
        return channel;
    }
}
