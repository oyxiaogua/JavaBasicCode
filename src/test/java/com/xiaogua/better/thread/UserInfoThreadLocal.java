package com.xiaogua.better.thread;

import com.xiaogua.better.bean.UserInfoBean;

public class UserInfoThreadLocal {
	private static ThreadLocal<UserInfoBean> threadLocal = new ThreadLocal<UserInfoBean>() {
		protected UserInfoBean initialValue() {
			return new UserInfoBean();
		}
	};

	public static void setUserInfo(UserInfoBean userInfo, String threadName) {
		UserInfoBean initUserInfo = getUserInfo();
		initUserInfo.setId(userInfo.getId());
		initUserInfo.setName(threadName);
		threadLocal.set(initUserInfo);
	}

	public static UserInfoBean getUserInfo() {
		return threadLocal.get();
	}

	public static void removeUserInfo() {
		threadLocal.remove();
	}

	public static void addCountNum() {
		getUserInfo().increaseNum();
	}

}
