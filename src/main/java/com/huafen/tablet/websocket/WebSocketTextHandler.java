package com.huafen.tablet.websocket;

import org.slf4j.Logger;

import com.huafen.tablet.netty.SessionGroup;
import com.huafen.tablet.netty.SocketSession;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class WebSocketTextHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(WebSocketTextHandler.class);
	
	
	@Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        SocketSession.getSession(ctx);
        String body = msg.text();
        SessionGroup.inst().sendMsg(ctx,body);
    }
 
	/**
     * 客户端掉线时的操作
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    	log.info("设备下线了:{}", ctx.channel().id().asLongText());
        ctx.close(); //关闭连接
    }
    
    
    @SuppressWarnings("deprecation")
	@Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
 
        // 是否握手成功，升级为 Websocket 协议
        if (evt == WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE) {
            // 握手成功，移除 HttpRequestHandler，因此将不会接收到任何消息
            // 并把握手成功的 Channel 加入到 ChannelGroup 中
            new SocketSession(ctx.channel());
        } else if (evt instanceof IdleStateEvent) {
            IdleStateEvent stateEvent = (IdleStateEvent) evt;
            if (stateEvent.state() == IdleState.READER_IDLE) {
                
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
