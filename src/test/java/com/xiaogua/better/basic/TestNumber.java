package com.xiaogua.better.basic;

import java.math.BigDecimal;
import java.math.MathContext;

import org.junit.Assert;
import org.junit.Test;

import com.xiaogua.better.str.StringCommonUtils;

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

	@Test
	public void testIfDivisibleByThree() {
		// 检查二进制字符串是否被3整除
		String str = RandomCode.getRandomBinaryStr(63);
		char[] charArr = str.toCharArray();
		int charLen = charArr.length;
		long total = 1;// 位数太多total会溢出
		int remain = 1;// 余数
		for (int i = 1; i < charLen; i++) {
			if (charArr[i] == '1') {
				total = total * 2 + 1;
				remain = remain * 2 + 1;
			} else if (charArr[i] == '0') {
				total = total * 2;
				remain = remain * 2;
			}
			remain = remain % 3;
		}
		System.out.println(str + "," + total);
		System.out.println(total % 3 + "," + remain + "," + (remain == 0));
	}

	@Test
	public void testIsNumber() {
		System.out.println(isNumberWithCommonsLang3("-11"));
		System.out.println(isNumberWithCommonsLang3("1e6"));
		System.out.println(isNumberWithCommonsLang3("0011"));
		System.out.println(isNumberWithCommonsLang3("+11"));
	}

	@Test
	public void testNumberDiv() {
		System.out.println(1.0d / 0); // Infinity
		System.out.println(0.0d / 0);// NaN
		// System.out.println(1 / 0); 报错
		// System.out.println(0 / 0); 报错
	}

	@Test
	public void testNumberInfinity() {
		double i = Double.POSITIVE_INFINITY;
		Assert.assertTrue(i == i + 1);
		Assert.assertTrue(Double.isInfinite(i));
		i = Double.NEGATIVE_INFINITY;
		Assert.assertTrue(i == i + 1);
		Assert.assertTrue(Double.isInfinite(i));
		
		float floatA=Float.POSITIVE_INFINITY;
		Assert.assertTrue(floatA== Double.POSITIVE_INFINITY);
		Assert.assertTrue(Float.isNaN(floatA*0));
		Assert.assertTrue(Float.isInfinite(floatA*1));
		Assert.assertFalse(Float.isFinite(floatA*1));
		Assert.assertFalse(Float.isFinite(floatA*0));



	}

	@Test
	public void testNumberNaN() {
		double i = Double.NaN;
		Assert.assertTrue(i != i);
		Assert.assertTrue(i != i + 1);
		Assert.assertFalse(Double.NaN == Double.NaN);
		Double a = new Double(Double.NaN);
		Double b = new Double(Double.NaN);
		Assert.assertTrue(a.equals(b));
		Assert.assertTrue(Double.isNaN(a));
	}

	@Test
	public void testFloatCompare() {
		float negZero = -0.0f;
		float zero = 0.0f;
		Assert.assertTrue(zero == negZero);
		Assert.assertEquals(1, Float.compare(zero, negZero));
		Assert.assertEquals(-1, Float.compare(negZero, zero));
	}
	
	@Test
	public void testDoubleFloat(){
		Float a=1.1f;
		Float b=1.1f;
		Assert.assertFalse(a==b);
		Double a2=1.1d;
		Double b2=1.1d;
		Assert.assertFalse(a2==b2);
	}

	public final static boolean isNumberWithCommonsLang3(String str) {
		if (StringCommonUtils.isBlank(str)) {
			return false;
		}
		str = str.trim();
		if (str.startsWith("+")) {
			str = str.substring(1);
		}
		return org.apache.commons.lang3.math.NumberUtils.isNumber(str);
	}

	public int getMiddleValue(int low, int high) {
		int mid = low + ((high - low) / 2);
		return mid;
	}
}
