package com.xiaogua.better.thread;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;

import com.xiaogua.better.bean.UserInfoBean;

public class TestThreadLocal {

	@Test
	public void testThreadLocalThreadSafe() throws Exception {
		UserInfoBean userInfoBean = new UserInfoBean(1, "userName");
		executeTestCode(userInfoBean, 5);
	}

	public void executeTestCode(UserInfoBean userInfoBean, int executeTime) throws Exception {
		CountDownLatch countDownLatch = new CountDownLatch(executeTime);
		for (int i = 0; i < executeTime; i++) {
			new OperateUserInfoThread(userInfoBean, countDownLatch).start();
		}
		countDownLatch.await();
	}

	private static class OperateUserInfoThread extends Thread {
		private UserInfoBean userInfoBean;
		private CountDownLatch countDownLatch;

		public OperateUserInfoThread(UserInfoBean userInfoBean, CountDownLatch countDownLatch) {
			super();
			this.userInfoBean = userInfoBean;
			this.countDownLatch = countDownLatch;
		}

		public void run() {
			String threadNameStr = Thread.currentThread().getName();
			try {
				UserInfoThreadLocal.setUserInfo(userInfoBean, threadNameStr);
				for (int i = 0; i < 100; i++) {
					UserInfoThreadLocal.addCountNum();
				}
				UserInfoBean userInfoBean = UserInfoThreadLocal.getUserInfo();
				System.out.println(threadNameStr + ",info=" + userInfoBean);
				UserInfoThreadLocal.removeUserInfo();
			} catch (Exception e) {
				System.err.println(threadNameStr + "," + e.getMessage());
			} finally {
				countDownLatch.countDown();
			}
		}
	}
}
