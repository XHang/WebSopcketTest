package com.demo.WebSocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.MXBean;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
@ServerEndpoint("/echo")
public class WebSocketTest {
	private  Map<String,WebSocketTest> users=new HashMap<String,WebSocketTest>();
	
	Session session;
	/**
	 * 当webSocket连接打开时
	 * @param session
	 */
	@OnOpen
	public void onOpen(Session session){
		System.out.println("有新的会话加入！它的id为："+session.getId());
		String nickName=session.getQueryString();
		users.put(nickName, this);
		System.out.println("用户"+nickName+"上线了");
	}
	/**
	 * 用于接受传入的客户端信息
	 */
	@OnMessage
	public void onMessage(String message,Session session){
	}
	/**
	 * 当webSocket连接关闭时
	 * @param session 连接的会话
	 * @param reason 封装更多关于关闭的细节
	 */
	@OnClose
	public void onClose(Session session, CloseReason reason){
		System.out.println("关闭会话！原因是:"+reason.getReasonPhrase()==null?"没有原因":reason.getReasonPhrase());
	}
	/**
	 * 不知道什么时候会调用，暂且留着
	 * @param t
	 */
	@OnError
	 public void onError(Throwable t) {
		System.out.println("错误啦，原因是......"+t.getMessage());
	 }
	//推送某人上线通知
	public void pushMsg(String nickName){
		Set<Map.Entry<String, WebSocketTest>> entry=users.entrySet();
		try{
			for(Iterator<Map.Entry<String, WebSocketTest>> it=entry.iterator();it.hasNext();){
				Map.Entry<String, WebSocketTest> user=it.next();
				user.getValue().session.getBasicRemote().sendText(nickName+"上线了");
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	} 
	
}
