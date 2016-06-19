package com.xiaogua.better.guava;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class TestGuavaCache {

	@Test
	public void testLoadingCache() throws Exception {
		LoadingCache<Long, AtomicLong> counterCache = CacheBuilder.newBuilder().expireAfterWrite(2, TimeUnit.SECONDS)
				.build(new CacheLoader<Long, AtomicLong>() {
					public AtomicLong load(Long seconds) throws Exception {
						return new AtomicLong(0);
					}
				});
		long limit = 10;
		while (true) {
			// 得到当前秒
			long currentSeconds = System.currentTimeMillis() / 1000;
			if (counterCache.get(currentSeconds).incrementAndGet() > limit) {
				//System.out.println("限流了:" + currentSeconds);
				continue;
			}
			// 业务处理
			System.out.println(currentSeconds);
		}
	}
}
