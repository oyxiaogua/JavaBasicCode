package com.xiaogua.better.guava;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.util.concurrent.RateLimiter;

public class TestGuavaRateLimiter {

	@Test
	public void testRateRateLimiter_1() {
		// 桶容量为5且每秒新增5个令牌，即每隔200毫秒新增一个令牌
		RateLimiter limiter = RateLimiter.create(5);
		// 如果当前桶中有足够令牌则成功（返回值为0），如果桶中没有令牌则暂停一段时间
		System.out.println(limiter.acquire());
		System.out.println(limiter.acquire());
		System.out.println(limiter.acquire());
		System.out.println(limiter.acquire());
		System.out.println(limiter.acquire());
		System.out.println(limiter.acquire());
	}

	@Test
	public void testRateRateLimiter_2() {
		RateLimiter limiter = RateLimiter.create(5);
		// 一次性消费5个令牌
		System.out.println(limiter.acquire(5));
		System.out.println(limiter.acquire(1));// 1s
		System.out.println(limiter.acquire(1));// 0.2s
	}

	@Test
	public void testRateRateLimiter_3() {
		RateLimiter limiter = RateLimiter.create(5);
		// 允许消费未来的令牌
		System.out.println(limiter.acquire(10));
		System.out.println(limiter.acquire(1));// 2s
		System.out.println(limiter.acquire(1));// 0.2s
	}

	@Test
	public void testRateRateLimiter_4() throws Exception {
		RateLimiter limiter = RateLimiter.create(2);
		System.out.println(limiter.acquire());
		Thread.sleep(2000L);
		// SmoothBursty中有一个参数：最大突发秒数（maxBurstSeconds）默认值是1s，突发量/桶容量=速率*maxBurstSeconds，桶容量/突发量为2，例子中前两个是消费了之前积攒的突发量
		// 第三个开始就是正常计算的了
		// 允许将一段时间内没有消费的令牌暂存到令牌桶中，留待未来使用，并允许未来请求的这种突发
		System.out.println(limiter.acquire());
		System.out.println(limiter.acquire());
		System.out.println(limiter.acquire());
		System.out.println(limiter.acquire());// 0.5s
		System.out.println(limiter.acquire());// 0.5s
	}

	@Test
	public void testRateRateLimiter_SmoothWarmingUp_1() throws Exception {
		// 平滑预热限流
		//每秒新增的令牌数:5
		//从冷启动速率过渡到平均速率的时间间隔 1
		RateLimiter limiter = RateLimiter.create(5, 1000, TimeUnit.MILLISECONDS);
		//速率是梯形上升速率的
		for (int i = 1; i < 5; i++) {
			System.out.println(limiter.acquire());
		}
		Thread.sleep(1000L);
		for (int i = 1; i < 5; i++) {
			System.out.println(limiter.acquire());
		}

	}
}
