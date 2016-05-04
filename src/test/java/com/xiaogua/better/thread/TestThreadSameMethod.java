package com.xiaogua.better.thread;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class TestThreadSameMethod {
	public void sleep(long time) {
		System.out.println("TestThreadSameMethod sleep:" + time);
	}

	public void threadRun() {
		new Thread() {
			public void run() {
				//方法覆盖
				TestThreadSameMethod.this.sleep(2);
			}
		}.start();
	}

	public void threadRunnable() {
		new Thread(new Runnable() {
			public void run() {
				sleep(2);
			}
		}).start();
	}

	@Test
	public void testThreadRun() throws Exception {
		threadRun();
		threadRunnable();
		TimeUnit.SECONDS.sleep(3);
	}

}
