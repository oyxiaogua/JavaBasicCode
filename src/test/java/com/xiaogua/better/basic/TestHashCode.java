package com.xiaogua.better.basic;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.xiaogua.better.bean.Parent_Normal_Bean;

public class TestHashCode {

	@Test
	public void testLongHashCode() {
		Long a = 0L + Integer.MAX_VALUE;
		Assert.assertEquals(Integer.MAX_VALUE, Long.hashCode(a));
		a = 0L + Integer.MIN_VALUE;
		Assert.assertEquals(Integer.MAX_VALUE, Long.hashCode(a));
	}

	@Test
	public void testIntegerHashCode() {
		Integer a = Integer.MIN_VALUE;
		Assert.assertEquals(Integer.MIN_VALUE, Integer.hashCode(a));
		a = Integer.MAX_VALUE;
		Assert.assertEquals(Integer.MAX_VALUE, Integer.hashCode(a));
	}

	@Test
	public void testFloatHashCode() {
		Float a = 3.14f;
		int aHashCode = Float.floatToIntBits(a);
		System.out.println(aHashCode);
	}

	@Test
	public void testDoubleHashCode() {
		Double a = 3.14;
		long longA = Double.doubleToLongBits(a);
		System.out.println(Long.hashCode(longA));
	}

	@Test
	public void testBooleanHashCode() {
		boolean flag = false;
		Assert.assertEquals(1237, Boolean.hashCode(flag));
		flag = true;
		Assert.assertEquals(1231, Boolean.hashCode(flag));
	}

	@Test
	public void testArraysHashCode() {
		int[] intArr = new int[] { 1, 2 };
		int hashCode = 31 * (31 + 1) + 2;
		Assert.assertEquals(hashCode, Arrays.hashCode(intArr));
	}

	@Test
	public void testHashCode() {
		Parent_Normal_Bean bean = new Parent_Normal_Bean();
		Parent_Normal_Bean bean2 = new Parent_Normal_Bean(null, 1, null);
		int beanHashCode = System.identityHashCode(bean);
		int bean2HashCode = System.identityHashCode(bean2);
		System.out.println(beanHashCode + "," + bean2HashCode);
	}
}
