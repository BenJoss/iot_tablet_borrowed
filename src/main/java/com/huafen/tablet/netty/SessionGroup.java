package com.huafen.tablet.netty;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSON;
import com.huafen.tablet.model.chat.CallChatDTO;
import com.huafen.tablet.model.chat.CallMsgDTO;
import com.huafen.tablet.util.DateUtil;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.ImmediateEventExecutor;

public final class SessionGroup {

	 private static SessionGroup singleInstance = new SessionGroup();
	 
	    // 组的映射
	    private ConcurrentHashMap<String, ChannelGroup> groupMap = new ConcurrentHashMap<>();
	 
	    public static SessionGroup inst() {
	        return singleInstance;
	    }
	 
	    public void shutdownGracefully() {
	 
	        Iterator<ChannelGroup> groupIterator = groupMap.values().iterator();
	        while (groupIterator.hasNext()) {
	            ChannelGroup group = groupIterator.next();
	            group.close();
	        }
	    }
	 
	    // 广播消息
	    public void sendAllMessage(String message) {
	    	 Iterator<ChannelGroup> groupIterator = groupMap.values().iterator();
		        while (groupIterator.hasNext()) {
			            ChannelGroup group = groupIterator.next();
			            ChannelGroupFuture future  = group.writeAndFlush(new TextWebSocketFrame(message));
			            future.addListener(f -> {//发送监听
				          
				        });
			            return;
		        }
	    }
	    
	    public void sendToOthers(CallChatDTO callChat, SocketSession s) {
	        // 获取组
	    	if (null == s.getGroup()) {
	    		 return;
			}
	        ChannelGroup group = groupMap.get(s.getGroup());
	        if (null == group) {
	            return;
	        }
	        CallMsgDTO callMsgDTO = new CallMsgDTO();
	        callMsgDTO.setChatMsg(callChat.getChatMsg());
	        callMsgDTO.setUserID(callChat.getCallUser().getUserID());
	        callMsgDTO.setUserName(callChat.getCallUser().getUserName());
	        callMsgDTO.setChatTime(DateUtil.getCallDate());
	        String json = JSON.toJSONString(callMsgDTO);
	        ChannelGroupFuture future = group.writeAndFlush(new TextWebSocketFrame(json));
	        future.addListener(f -> {
	            System.out.println("完成发送："+json);
	 
	        });
	    }
	 
	    
	    public void rmSession(SocketSession session) {
	    	String groupName = session.getGroup();
	        if ("".equals("groupName")||null == groupName) {
	            // 组为空，直接返回
	            return;
	        }
	        ChannelGroup group = groupMap.get(groupName);
	        if (null == group) {
	        	return ;
	        }
	      	Channel channel = session.getChannel();
	        // 从组中移除通道
  	        group.remove(channel);
	    }
	    
	    public void addSession(SocketSession session) {
	 
	        String groupName = session.getGroup();
	        if ("".equals("groupName")) {
	            // 组为空，直接返回
	            return;
	        }
	        ChannelGroup group = groupMap.get(groupName);
	        if (null == group) {
	            group = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
	            groupMap.put(groupName, group);
	        }
	        group.add(session.getChannel());
	    }
	 
	    /**
	     * 关闭连接， 关闭前发送一条通知消息
	     */
	    public void closeSession(SocketSession session, String echo) {
	        ChannelFuture sendFuture = session.getChannel().writeAndFlush(new TextWebSocketFrame(echo));
	        sendFuture.addListener(new ChannelFutureListener() {
	            public void operationComplete(ChannelFuture future) {
	                System.out.println("关闭连接："+echo);
	                future.channel().close();
	            }
	        });
	    }
	 
	    /**
	     * 关闭连接
	     */
	    public void closeSession(SocketSession session) {
	 
	        ChannelFuture sendFuture = session.getChannel().close();
	        sendFuture.addListener(new ChannelFutureListener() {
	            public void operationComplete(ChannelFuture future) {
	                System.out.println("发送所有完成："+session.getCallUser().getUserName());
	            }
	        });
	 
	    }
	 
	    /**
	     * 发送消息
	     * @param ctx 上下文
	     * @param msg 待发送的消息
	     */
	    public void sendMsg(ChannelHandlerContext ctx, String msg) {
	        ChannelFuture sendFuture = ctx.writeAndFlush(new TextWebSocketFrame(msg));
	        sendFuture.addListener(f -> {//发送监听
	          
	        });
	    }
}
