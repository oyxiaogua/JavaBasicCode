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
