package com.xiaogua.better.basic;

import java.lang.reflect.Field;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class TestInteger {
	@Test
	public void testIntegerOverFlow() {
		// 溢出
		long x = Integer.MAX_VALUE + 1;
		Assert.assertTrue(x < 0);
		long y = Integer.MAX_VALUE + (long) 1;
		Assert.assertTrue(y > 0);
	}

	@Test
	public void testMathAbs() {
		// 移除
		int intAbs = Math.abs(Integer.MIN_VALUE);
		Assert.assertTrue(intAbs < 0);
	}

	@Test
	public void testIntegerCache() {
		Integer a = Integer.valueOf(-127);
		Integer b = Integer.valueOf(-127);
		Integer c = new Integer(-127);
		Assert.assertTrue(a == b);
		Assert.assertFalse(a == c);
	}

	@Test
	public void testChangeIntegerCache() throws Exception {
		Class<?> cache = Integer.class.getDeclaredClasses()[0];
		Field myCache = cache.getDeclaredField("cache");
		myCache.setAccessible(true);
		// 获取Integer中的小整数缓存
		Integer[] newCache = (Integer[]) myCache.get(cache);
		Assert.assertEquals(0, newCache[128].intValue());
		// 修改Integer中的小整数缓存，把4修改为5，使2+2=5
		newCache[132] = newCache[133]; // 5
		int a = 2;
		int b = a + a;
		System.out.printf("%d + %d = %d", a, a, b);
	}
	
	@Test
	public void testChangeIntegerCache2() throws Exception {
		Class<?> clazz = Class.forName("java.lang.Integer$IntegerCache");
		Field field = clazz.getDeclaredField("cache");
		field.setAccessible(true);
		Integer[] cache = (Integer[]) field.get(clazz);
		// 改变Integer的缓存
		for (int i = 0; i < cache.length; i++) {
			cache[i] = new Integer(new Random().nextInt(cache.length));
		}
		for (int i = 0; i < 10; i++) {
			System.out.println((Integer) i); // 这个时候1不是1 ，2也不是2
		}
	}

}
