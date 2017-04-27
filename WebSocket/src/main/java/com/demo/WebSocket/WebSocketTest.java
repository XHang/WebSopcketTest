package com.demo.WebSocket;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
//访问这个webSocket的路径
@ServerEndpoint("/echo")
public class WebSocketTest {
	//会话打开
	private Session session;
	
	
	
	
	/**
	 * 
	 * @param session
	 */
	@OnOpen
	public void onOpen(Session session){
		
	} 
	
}
