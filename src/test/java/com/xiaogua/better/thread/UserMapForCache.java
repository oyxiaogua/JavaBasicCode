package com.xiaogua.better.thread;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.xiaogua.better.datetime.DateTimeCode;

public class UserMapForCache {
	private static ConcurrentHashMap<String, FutureTask<String>> concurrentHashMap = new ConcurrentHashMap<String, FutureTask<String>>();
	private static Logger log = Logger.getLogger(UserMapForCache.class);

	public String get(String key) {
		String result = "default";
		FutureTask<String> task = concurrentHashMap.get(key);
		if (task == null) {
			FutureTask<String> tempTask = new FutureTask<String>(new Callable<String>() {
				public String call() throws Exception {
					TimeUnit.SECONDS.sleep(3);
					return "complete:" + DateTimeCode.getStrFromDate(new Date(), DateTimeCode.FULL_DATETIME);
				}
			});
			task = concurrentHashMap.putIfAbsent(key, tempTask);
			if (task == null) {
				task = tempTask;
				log.error("start query key=" + key);
				task.run();
			}
		}
		try {
			log.error("start get result ,key=" + key);
			result = task.get();
			log.error("finish get result ,key=" + key);
		} catch (InterruptedException e) {
			task.cancel(true);
			concurrentHashMap.remove(key);
			log.error("get interruptedexception", e);
		} catch (Exception e) {
			concurrentHashMap.remove(key);
			log.error("get error", e);
		}
		return result;
	}
}
