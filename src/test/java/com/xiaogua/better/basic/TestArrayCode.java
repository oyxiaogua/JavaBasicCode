package com.xiaogua.better.basic;

import org.junit.Test;

import org.junit.Assert;

public class TestArrayCode {
	@Test
	public void testResizeArray() {
		int[] a = { 1, 2, 3 };
		a = (int[]) ArrayCode.resizeArray(a, 5);
		Assert.assertEquals(5, a.length);
	}
}
