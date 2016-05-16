package com.xiaogua.better.thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.xiaogua.better.datetime.DateTimeCode;

public class GuavaForCache {
	private static Logger log = Logger.getLogger(UserMapForCache.class);
	private static LoadingCache<String, String> mycache = CacheBuilder.newBuilder().concurrencyLevel(4)
			.expireAfterAccess(30, TimeUnit.SECONDS).build(new CacheLoader<String, String>() {
				public String load(String key) throws Exception {
					log.error("start query key=" + key);
					TimeUnit.SECONDS.sleep(3);
					return "complete:" + DateTimeCode.getStrFromDate(new Date(), DateTimeCode.FULL_DATETIME);
				}
			});

	public String get(String key) {
		String rtn = null;
		try {
			rtn = mycache.get(key);
		} catch (Exception e) {
			log.error("get key error", e);
		}
		return rtn;
	}

}
