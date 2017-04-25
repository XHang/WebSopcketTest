package com.test.WebSocket;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
//访问这个webSocket的路径
@ServerEndpoint("/echo")
public class WebSocketTest {
	//与客户端连接的通道（会话）
	private Session session;
	
	
	
	
	/**
	 * 
	 * @param session
	 */
	@OnOpen
	public void onOpen(Session session){
		
	}
	
}
