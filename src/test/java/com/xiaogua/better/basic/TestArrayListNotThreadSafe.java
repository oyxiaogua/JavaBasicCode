package com.xiaogua.better.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.test.annotation.Repeat;

import com.xiaogua.better.test.My_Junit_RepeatRule;
import com.xiaogua.better.test.My_Junit_RepeatRule.My_Junit_Repeat;

public class TestArrayListNotThreadSafe {

	@Repeat(10)
	@Test
	public void testArrayListNoThreadSafeWithSpringRepeat() throws Exception {
		List<String> list = new ArrayList<String>();
		int threadCount = 1000;
		final int addSize = 10;
		executeTestCode(list, threadCount, addSize);

		int size = list.size();
		if (size != threadCount * addSize) {
			System.err.println("happend list size not equal expected value,current list size=" + size);
		}
	}

	@Rule
	public My_Junit_RepeatRule repeatRule = new My_Junit_RepeatRule();

	@My_Junit_Repeat(times = 10)
	@Test
	public void testArrayListNoThreadSafeWithJunitRepeat() throws Exception {
		List<String> list = new ArrayList<String>();
		int threadCount = 1000;
		final int addSize = 10;
		executeTestCode(list, threadCount, addSize);

		int size = list.size();
		if (size != threadCount * addSize) {
			System.err.println("happend list size not equal expected value,current list size=" + size);
		}
	}

	private void executeTestCode(List<String> list, int threadCount, final int addSize) throws InterruptedException {
		// 用来让主线程等待threadCount个子线程执行完毕
		CountDownLatch countDownLatch = new CountDownLatch(threadCount);
		// 启动threadCount个子线程
		for (int i = 0; i < threadCount; i++) {
			Thread thread = new Thread(new AddValueToListThread(list, countDownLatch, addSize));
			thread.start();
		}
		// 主线程等待所有子线程执行完成，再向下执行
		countDownLatch.await();
	}

	class AddValueToListThread implements Runnable {
		private List<String> list;
		private CountDownLatch countDownLatch;
		private int addSize;

		public AddValueToListThread(List<String> list, CountDownLatch countDownLatch, int addSize) {
			this.list = list;
			this.countDownLatch = countDownLatch;
			this.addSize = addSize;
		}

		public void run() {
			try {
				// 每个线程向List中添加100个元素
				for (int i = 0; i < addSize; i++) {
					list.add("test");
				}
			} catch (Exception e) {
				if (e instanceof ArrayIndexOutOfBoundsException) {
					System.err.println("happend array index out of bounds exception,current size=" + list.size());
				}
			} finally {
				// 完成一个子线程
				countDownLatch.countDown();
			}
		}
	}

}
