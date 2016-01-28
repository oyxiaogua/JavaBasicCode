package com.xiaogua.better.basic;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

public class TestInteger {
	@Test
	public void testIntIncrement() {
		int n = 1;
		for (int i = 0; i < 100; i++) {
			n = n++;
			// 第一： n_copy =n；
			// 第二n++
			// 第三n= n_copy
		}
		Assert.assertEquals(1, n);
	}

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

	@Test
	public void testConvertObjectToPrimitive() {
		List<Integer> integerList = new ArrayList<Integer>();
		integerList.add(1);
		// 不能有null对象
		// integerList.add(null);
		Integer[] integers = integerList.toArray(new Integer[integerList.size()]);
		int[] ints = ArrayUtils.toPrimitive(integers);
		System.out.println(Arrays.toString(ints));
	}

	@Test
	public void testConvertPrimitiveToObject() {
		int[] ints = { 2 };
		Integer[] integersArr = ArrayUtils.toObject(ints);
		List<Integer> integerList = new ArrayList<Integer>();
		Collections.addAll(integerList, integersArr);
		System.out.println(integerList);
	}

	@Test
	public void testGeIntLength() {
		Assert.assertEquals(3, geIntLength(999));
	}

	@Test
	public void testAddNum() {
		Assert.assertEquals(27, addNum(13, 14));
	}

	@Test
	public void testGetAvg() {
		int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };
		double avg = arr[0];
		for (int i = 1, len = arr.length; i < len; i++) {
			avg = (avg * i + arr[i]) / (i + 1);
			System.out.println((i + 1) + "," + avg);
		}
	}

	@Test
	public void testSwapInt() {
		swapInt(Integer.MAX_VALUE, Integer.MIN_VALUE);
	}

	public int addNum(int m, int n) {
		int sum = 0;
		int carry = 0;
		do {
			sum = m ^ n;
			carry = (m & n) << 1;
			m = sum;
			n = carry;
		} while (n != 0);
		return m;
	}

	public int geIntLength(int x) {
		final int[] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE };
		for (int i = 0;; i++) {
			if (x <= sizeTable[i])
				return i + 1;
		}
	}

	public void swapInt(int i, int j) {
		if (i == j)
			return;
		i ^= j;
		j ^= i;
		i ^= j;
		System.out.println(i + "," + j);
	}
}
