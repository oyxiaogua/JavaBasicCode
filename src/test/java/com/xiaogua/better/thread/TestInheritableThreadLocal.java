package com.xiaogua.better.thread;

import java.util.concurrent.TimeUnit;

public class TestInheritableThreadLocal {
	private static ThreadLocal<String> stringItl = new InheritableThreadLocal<String>() {
		protected String initialValue() {
			System.out.println(Thread.currentThread().getName() + " initialize stringItl variable.");
			return "String init";
		}
	};

	private static ThreadLocal<String> stringItl2 = new InheritableThreadLocal<String>() {
		protected String initialValue() {
			System.out.println(Thread.currentThread().getName() + " initialize stringItl2 variable.");
			return "String2 init";
		}
	};

	private static ThreadLocal<StringBuffer> stringBufferItl = new InheritableThreadLocal<StringBuffer>() {
		protected StringBuffer initialValue() {
			System.out.println(Thread.currentThread().getName() + " initialize stringBufferItl variable.");
			return new StringBuffer("StringBuffer init");
		}
	};

	private static ThreadLocal<StringBuffer> stringBufferItl2 = new InheritableThreadLocal<StringBuffer>() {
		protected StringBuffer initialValue() {
			System.out.println(Thread.currentThread().getName() + " initialize stringBufferItl2 variable.");
			return new StringBuffer("StringBuffer2 init");
		}
	};

	public static void main(String[] args) throws InterruptedException {
		stringItl.set("Parent");
		stringBufferItl.set(new StringBuffer("ParentBuffer"));

		System.out.println(Thread.currentThread().getName() + " first get stringItl : " + stringItl.get());
		System.out.println(
				Thread.currentThread().getName() + " first get stringBufferItl : " + stringBufferItl.get().toString());

		for (int i = 0; i < 2; i++) {
			new Thread() {
				public void run() {
					System.out.println(Thread.currentThread().getName() + " first get stringItl : " + stringItl.get());
					stringItl.set(Thread.currentThread().getName() + "Child");
					System.out.println(
							Thread.currentThread().getName() + " get after set stringItl : " + stringItl.get());

					System.out
							.println(Thread.currentThread().getName() + " first get stringItl2 : " + stringItl2.get());
					stringItl2.set(Thread.currentThread().getName() + "Child");
					System.out.println(
							Thread.currentThread().getName() + " get after set stringItl2 : " + stringItl2.get());

					System.out.println(Thread.currentThread().getName() + " first get stringBufferItl : "
							+ stringBufferItl.get().toString());
					stringBufferItl.get().append(Thread.currentThread().getName());
					System.out.println(Thread.currentThread().getName() + " get after set stringBufferItl : "
							+ stringBufferItl.get().toString());

					System.out.println(Thread.currentThread().getName() + " first get stringBufferIt2 : "
							+ stringBufferItl2.get().toString());
					stringBufferItl2.get().append(Thread.currentThread().getName());
					System.out.println(Thread.currentThread().getName() + " get after set stringBufferItl2 : "
							+ stringBufferItl2.get().toString());
				}

			}.start();
		}

		for (int i = 0; i < 2; i++) {
			new Thread() {
				public void run() {
					System.out.println(Thread.currentThread().getName() + " first get stringItl : " + stringItl.get());
					stringItl.set(Thread.currentThread().getName() + "Child");
					System.out.println(
							Thread.currentThread().getName() + " get after set stringItl : " + stringItl.get());

					System.out
							.println(Thread.currentThread().getName() + " first get stringItl2 : " + stringItl2.get());
					stringItl2.set(Thread.currentThread().getName() + "Child");
					System.out.println(
							Thread.currentThread().getName() + " get after set stringItl2 : " + stringItl2.get());

					System.out.println(Thread.currentThread().getName() + " first get stringBufferItl : "
							+ stringBufferItl.get().toString());
					stringBufferItl.set(new StringBuffer(Thread.currentThread().getName() + "Buffer"));
					System.out.println(Thread.currentThread().getName() + " get after set stringBufferItl : "
							+ stringBufferItl.get().toString());

					System.out.println(Thread.currentThread().getName() + " first get stringBufferIt2 : "
							+ stringBufferItl2.get().toString());
					stringBufferItl2.get().append(Thread.currentThread().getName());
					System.out.println(Thread.currentThread().getName() + " get after set stringBufferItl2 : "
							+ stringBufferItl2.get().toString());
				}

			}.start();
		}

		TimeUnit.SECONDS.sleep(2);
		// 不变性的对象，如String，对于主线程设置的值子线程可以通过get函数获取，但子线程调用set函数设置新值后，对主线程没有影响
		System.out.println(Thread.currentThread().getName() + " second get stringItl : " + stringItl.get());
		System.out.println(Thread.currentThread().getName() + " first get stringItl2 : " + stringItl2.get());
		System.out.println(
				Thread.currentThread().getName() + " second get stringBufferItl : " + stringBufferItl.get().toString());
		System.out.println(Thread.currentThread().getName() + " first get stringBufferItl2 : "
				+ stringBufferItl2.get().toString());
	}
}