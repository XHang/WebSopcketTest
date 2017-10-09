package com.Chat.WebSocket;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.MXBean;
import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.Chat.Dictionary.Activity;
import com.Chat.Model.Msg;
import com.Chat.Model.SingInAndSingOut;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
@ServerEndpoint("/echo")
public class WebSocketTest {
	private  static Map<String,Session> users=new HashMap<String,Session>();
	
	Session session;
	/**
	 * 当webSocket连接打开时
	 * @param session
	 * @throws IOException 
	 */
	@OnOpen
	public void onOpen(Session session) throws IOException{
		String nickName=session.getQueryString();
		nickName=new String(URLDecoder.decode(nickName, "utf-8"));
		SingInAndSingOut info=new SingInAndSingOut();
		info.setHow(nickName);
		info.setActivity(Activity.Singin);
		pushMsg(info);
		singlePush(session);
		users.put(nickName, session);
		System.out.println("用户"+nickName+"上线了....");
	}
	/**
	 * 用于接受传入的客户端信息
	 * @throws EncodeException 
	 * @throws IOException 
	 */
	@OnMessage
	public void onMessage(String message,Session session) throws IOException, EncodeException{
		System.out.println("接受信息"+message);
		Msg msg=JSON.parseObject(message, Msg.class);
		Session targeSession=getSessionByNickName(msg.getTargeNickName());
		try {
			targeSession.getBasicRemote().sendText(JSON.toJSONString(msg));
		} catch (Exception e) {
			e.printStackTrace();
			return ;
		}
		
	}
	/**
	 * 当webSocket连接关闭时
	 * @param session 连接的会话
	 * @param reason 封装更多关于关闭的细节
	 * @throws Exception 
	 */
	@OnClose
	public void onClose(Session session, CloseReason reason) throws Exception{
		System.out.println("关闭会话！原因是:"+reason.getReasonPhrase()==null?"没有原因":reason.getReasonPhrase());
		String nickName=deleteUser(session);
		SingInAndSingOut info=new SingInAndSingOut();
		info.setHow(nickName);
		info.setActivity(Activity.SingOut);
		pushMsg(info);
		
	}
	/**
	 * 方法外直接抛异常会被这个方法捕捉到
	 * @param t
	 */
	@OnError
	 public void onError(Throwable t) {
		System.out.println("错误啦，原因是......"+t.getMessage());
	 }
	//广播
	private void pushMsg(SingInAndSingOut info){
		Set<Map.Entry<String, Session>> entry=users.entrySet();
		try{
			for(Iterator<Map.Entry<String, Session>> it=entry.iterator();it.hasNext();){
				Map.Entry<String, Session> user=it.next();
				user.getValue().getBasicRemote().sendText(JSON.toJSONString(info));
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	} 
	/**
	 * 通过传进来的session来找出他的昵称是什么
	 * @return 昵称
	 * @throws Exception 
	 */
	private String getNickNameBySession(Session session) throws Exception{
		Set<Map.Entry<String, Session>> entry=users.entrySet();
			for(Iterator<Map.Entry<String, Session>> it=entry.iterator();it.hasNext();){
				Map.Entry<String, Session> user=it.next();
				if(user.getValue().getId()==session.getId()){
					return user.getKey();
				}
			}
			throw new Exception("这个Session还没有计入到连接池中");
	}
	/**
	 * 根据昵称获取到对应的会话对象
	 * @param nickName
	 * @return
	 */
	private Session getSessionByNickName(String nickName){
		return users.get(nickName);
	}
	/**
	 * 通过客户端传来的Json数据取出要传送的目标
	 * @param jsonStr 客户端的Json数据
	 * @return	要传送给对方的昵称
	 */
	private String getTargeByJson(String jsonStr){
		JSONObject json=JSONObject.parseObject(jsonStr);
		return (String) json.get("tager");
	}
	/**
	 * 通过json数据分析出传送信息的本人昵称
	 * @param jsonStr
	 * @return
	 */
	private String getSourceByJson(String jsonStr){
		JSONObject json=JSONObject.parseObject(jsonStr);
		return (String) json.get("source");
	}
	/**
	 * 通过Josn数据分析出对话信息
	 * @return
	 */
	private String getmsgByJson(String jsonStr){
		JSONObject json=JSONObject.parseObject(jsonStr);
		return (String) json.get("msg");
	}
	/**
	 * 删除用户集合的一个用户，通过Session
	 * @param session
	 * @throws Exception 
	 * @retuen 删除用户的昵称
	 */
	private String deleteUser(Session session) throws Exception{
		Set<Map.Entry<String, Session>> entry=users.entrySet();
		for(Iterator<Map.Entry<String, Session>> it=entry.iterator();it.hasNext();){
			Map.Entry<String, Session> user=it.next();
			if(user.getValue().getId()==session.getId()){
				String nickName=user.getKey();
				users.remove(nickName);
				return nickName;
			}
		}
		throw new Exception("这个Session还没有计入到Session池中");
	}
	/**
	 * 当前登录的用户推送在线客户
	 * @throws IOException 
	 */
	private void singlePush(Session session) throws IOException{
		Set<Map.Entry<String, Session>> entry=users.entrySet();
		for(Iterator<Map.Entry<String, Session>> it=entry.iterator();it.hasNext();){
			Map.Entry<String, Session> user=it.next();
			SingInAndSingOut info =new SingInAndSingOut();
			info.setActivity(Activity.Singin);
			info.setHow(user.getKey());
			session.getBasicRemote().sendText(JSON.toJSONString(info));
			}
		}
	
}
