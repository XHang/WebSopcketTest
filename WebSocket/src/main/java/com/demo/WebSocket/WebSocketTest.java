package com.demo.WebSocket;

import javax.management.MXBean;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
//访问这个webSocket的路径，并注解这个类是一个WebSocket类
//注意：这个类必须要有个无参的默认构造函数
//这种实现的webSocket的方式遵循标准 JSR356 规范实现
//也有一种实现是Tomcat高版本自定义的api。
//这里不解释另一种了
@ServerEndpoint("/echo")
public class WebSocketTest {
	//与客户端连接的通道（会话）
	private Session session;
	
	
	
	
	/**
	 * 当webSocket连接打开时
	 * @param session
	 */
	@OnOpen
	public void onOpen(Session session){
		
	}
	/**
	 * 用于接受传入的客户端信息
	 */
	@OnMessage
	public void onMessage(String message){
		
	}
	/**
	 * 当webSocket连接关闭时
	 * @param session 连接的会话
	 * @param reason 封装更多关于关闭的细节
	 */
	@OnClose
	public void onClose(Session session, CloseReason reason){
	}
	/**
	 * 不知道什么时候会调用，暂且留着
	 * @param t
	 */
	@OnError
	 public void onError(Throwable t) {
	 }
	
}
