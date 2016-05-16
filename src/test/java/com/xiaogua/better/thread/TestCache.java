package com.xiaogua.better.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.Test;

public class TestCache {
	private static Logger log = Logger.getLogger(TestCache.class);

	@Test
	public void testUserMapForCache() throws Exception {
		final UserMapForCache cache = new UserMapForCache();
		ExecutorService es = Executors.newFixedThreadPool(20);
		for (int i = 1; i <= 50; i++) {
			final int key = (int) (Math.random() * 10);
			es.execute(new Runnable() {
				public void run() {
					try {
						TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 100));
					} catch (Exception e) {
					}
					String result = cache.get(String.valueOf(key));
					log.error(Thread.currentThread().getName() + ",key=" + key + ",result=" + result);
				}
			});
		}
		TimeUnit.SECONDS.sleep(100);
		es.shutdown();
	}

	@Test
	public void testGuavaForCache() throws Exception {
		final GuavaForCache cache = new GuavaForCache();
		ExecutorService es = Executors.newFixedThreadPool(20);
		for (int i = 1; i <= 50; i++) {
			final int key = (int) (Math.random() * 10);
			es.execute(new Runnable() {
				public void run() {
					try {
						TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 100));
					} catch (Exception e) {
					}
					String result = cache.get(String.valueOf(key));
					log.error(Thread.currentThread().getName() + ",key=" + key + ",result=" + result);
				}
			});
		}
		TimeUnit.SECONDS.sleep(100);
		es.shutdown();
	}
}
