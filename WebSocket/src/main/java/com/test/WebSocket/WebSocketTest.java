package com.test.WebSocket;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
//�������webSocket��·��
@ServerEndpoint("/echo")
public class WebSocketTest {
	//��ͻ������ӵ�ͨ�����Ự��
	private Session session;
	
	
	
	
	/**
	 * 
	 * @param session
	 */
	@OnOpen
	public void onOpen(Session session){
		
	}
	
}
