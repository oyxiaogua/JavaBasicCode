package com.xiaogua.better.thread;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
}
