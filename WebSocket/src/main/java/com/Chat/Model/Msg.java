package com.Chat.Model;
/**
 * 封装信息实体对象
 * @author Mr-hang
 *
 */

import javax.websocket.Session;

import com.Chat.Dictionary.Activity;

public class Msg {
	private Activity activity=Activity.sendMsg ;
	private String targeNickName;
	private Session targeSession;
	private String sourceNickName;
	private String info;
	
	
	
	
	public String getTargeNickName() {
		return targeNickName;
	}
	public void setTargeNickName(String targeNickName) {
		this.targeNickName = targeNickName;
	}
	public Session getTargeSession() {
		return targeSession;
	}
	public void setTargeSession(Session targeSession) {
		this.targeSession = targeSession;
	}
	public String getSourceNickName() {
		return sourceNickName;
	}
	public void setSourceNickName(String sourceNickName) {
		this.sourceNickName = sourceNickName;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	

}
