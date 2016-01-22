package com.xiaogua.better.basic;

import java.math.BigDecimal;
import java.math.MathContext;

import org.junit.Test;

import org.junit.Assert;

public class TestNumber {

	@Test
	public void testBigDecimalMathContext() {
		// 15个有效数字
		MathContext mc = new MathContext(15);
		BigDecimal bg = new BigDecimal("1.23456789012345678E+10", mc);
		String plainStr = bg.toPlainString();
		Assert.assertEquals("12345678901.2346", plainStr);
	}

	@Test
	public void testGetMiddleValue() {
		int low = Integer.MAX_VALUE;
		int high = Integer.MAX_VALUE;
		Assert.assertEquals(Integer.MAX_VALUE, getMiddleValue(low, high));
	}

	public int getMiddleValue(int low, int high) {
		int mid = low + ((high - low) / 2);
		return mid;
	}
}
