package com.xiaogua.better.class_init;

import org.junit.Test;

public class TestReferenceVarFinalClass {

	@Test
	public void testReferenceVarFinalNoSafe() throws Exception {
		int i = 0;
		while (i++ <= 100) {
			new Thread(new Runnable() {
				// 线程A,进行instance对象的写入
				public void run() {
					ReferenceVarFinalClass.writerInstance();
				}
			}).start();
			Thread.sleep(5);
			new Thread(new Runnable() {
				// 线程B,进行referVar_final对象状态的改变
				public void run() {
					ReferenceVarFinalClass.writerReferVar();
				}
			}).start();
			new Thread(new Runnable() {
				//// 线程C,进行referVar_final对象状态的读取
				public void run() {
					ReferenceVarFinalClass.reader();
				}
			}).start();
		}

	}
}
