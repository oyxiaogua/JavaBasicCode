package com.xiaogua.better.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class TestFuture {
	private static Logger log = Logger.getLogger(TestFuture.class);

	@Test(expected = CancellationException.class)
	public void testFutureCancel() throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(1);
		GetSysTimeTask task = new GetSysTimeTask(3000, false);
		Future<String> future = executor.submit(task);
		TimeUnit.SECONDS.sleep(2);
		future.cancel(true);
		Assert.assertTrue(future.isCancelled());
		Assert.assertTrue(future.isDone());
		log.error("future get:" + future.get());
		executor.shutdown();
	}

	@Test
	public void testFutureException() throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(1);
		GetSysTimeTask task = new GetSysTimeTask(3000, true);
		Future<String> future = executor.submit(task);
		try {
			future.get();
		} catch (Exception e) {
		}
		Assert.assertFalse(future.isCancelled());
		Assert.assertTrue(future.isDone());
		executor.shutdown();
	}

	@Test(expected = TimeoutException.class)
	public void testFutureTimeOutException() throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(1);
		GetSysTimeTask task = new GetSysTimeTask(3000, false);
		Future<String> future = executor.submit(task);
		try {
			future.get(2000, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			Assert.assertFalse(future.isCancelled());
			Assert.assertFalse(future.isDone());
			if (e instanceof TimeoutException) {
				throw e;
			}
		} finally {
			executor.shutdown();
		}
	}

	@Test
	public void testProcessData() throws Exception {
		List<String> processIdList = new ArrayList<String>(256);
		ExecutorService executor = Executors.newFixedThreadPool(5);
		List<Future<String>> futureList = new ArrayList<Future<String>>(256);
		Future<String> future = null;
		List<Map<String, String>> dataList = null;
		int loopTime = 10;
		String idStr = null;
		Iterator<Future<String>> it = null;
		int processNum = 0;
		int totalNum = 0;
		int dumpNum = 0;
		for (int i = 0; i < loopTime; i++) {
			dataList = generateRandomData(RandomUtils.nextInt(0, 200));
			totalNum += dataList.size();
			if (dataList.size() > 0) {
				for (Map<String, String> map : dataList) {
					idStr = map.get("id");
					if (!processIdList.contains(idStr)) {
						future = executor.submit(new ProcessDataTask(map));
						futureList.add(future);
						processIdList.add(idStr);
					} else {
						log.error("ignore dump id=" + idStr);
						dumpNum++;
					}
				}
			}
			it = futureList.iterator();
			while (it.hasNext()) {
				future = it.next();
				if (future.isCancelled()) {
					it.remove();
					continue;
				}
				if (future.isDone()) {
					try {
						idStr = future.get();
						processIdList.remove(idStr);
						log.error("finish process id=" + idStr);
						processNum++;
					} catch (Exception e) {
						log.error("exception msg=" + e.getMessage());
					}
					it.remove();
				}
			}
			TimeUnit.SECONDS.sleep(10);
			log.error("loop time=" + i + ",dataList size=" + dataList.size() + ",futureList size=" + futureList.size());
		}
		log.error("process total num=" + processNum + ",totalNum=" + totalNum + ",dumpNum=" + dumpNum);
		executor.shutdown();
	}

	private List<Map<String, String>> generateRandomData(int num) {
		List<Map<String, String>> rtnList = new ArrayList<Map<String, String>>();
		Map<String, String> dataMap = null;
		for (int i = 0; i < num; i++) {
			dataMap = new HashMap<String, String>();
			dataMap.put("id", Integer.toString(RandomUtils.nextInt(1, 300)));
			dataMap.put("name", RandomStringUtils.random(10));
			rtnList.add(dataMap);
		}
		return rtnList;
	}
}
