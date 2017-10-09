package com.Chat.Model;

import com.Chat.Dictionary.Activity;

/**
 * 登录和登出的信息封装对象
 * @author Mr-hang
 *
 */
public class SingInAndSingOut {
	private String how;
	private Activity activity;
	public String getHow() {
		return how;
	}
	public void setHow(String how) {
		this.how = how;
	}
	/**
	 * 得到对象的作用
	 * @return
	 */
	public Activity getActivity() {
		return activity;
	}
	/**
	 * 设置对象的作用。
	 * 登录还是登出
	 * @param activity
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
}
