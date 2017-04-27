package com.demo.WebSocket;

import javax.management.MXBean;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
//�������webSocket��·������ע���������һ��WebSocket��
//ע�⣺��������Ҫ�и��޲ε�Ĭ�Ϲ��캯��
//����ʵ�ֵ�webSocket�ķ�ʽ��ѭ��׼ JSR356 �淶ʵ��
//Ҳ��һ��ʵ����Tomcat�߰汾�Զ����api��
//���ﲻ������һ����
@ServerEndpoint("/echo")
public class WebSocketTest {
	//��ͻ������ӵ�ͨ�����Ự��
	private Session session;
	
	
	
	
	/**
	 * ��webSocket���Ӵ�ʱ
	 * @param session
	 */
	@OnOpen
	public void onOpen(Session session){
		
	}
	/**
	 * ���ڽ��ܴ���Ŀͻ�����Ϣ
	 */
	@OnMessage
	public void onMessage(String message){
		
	}
	/**
	 * ��webSocket���ӹر�ʱ
	 * @param session ���ӵĻỰ
	 * @param reason ��װ������ڹرյ�ϸ��
	 */
	@OnClose
	public void onClose(Session session, CloseReason reason){
	}
	/**
	 * ��֪��ʲôʱ�����ã���������
	 * @param t
	 */
	@OnError
	 public void onError(Throwable t) {
	 }
	
}
