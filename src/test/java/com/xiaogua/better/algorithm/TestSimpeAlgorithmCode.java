package com.xiaogua.better.algorithm;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TestSimpeAlgorithmCode {
	@Test
	public void testIterativeAtoi() {
		int rtnNum = iterativeAtoi("-1");
		Assert.assertEquals(-1, rtnNum);
	}

	@Test
	public void testFindSerialNumberEqualN() {
		List<List<Integer>> rtnList = SimpleAlgorithmCode.findSerialNumberEqualN(39);
		System.out.println(rtnList);
	}

	@Test
	public void testChangeArrWithSpecialRules() {
		int[] dataArr = new int[] { -1, 1, 0, 3, 5, -1, -2, 1, -2, 3, -3 };
		SimpleAlgorithmCode.changeArrValueWithSpecialRules(dataArr);
		System.out.println(Arrays.toString(dataArr));
	}

	@Test
	public void testGetMaxIntegerBreak() {
		int max = getMaxIntegerBreak(10);
		Assert.assertEquals(36, max);// 3*3*4
		max = getMaxIntegerBreak(15);
		System.out.println(max);
	}

	@Test
	public void testIsPowerOfFour() {
		int intValue = (int) Math.pow(2, 30);
		System.out.println(isPowerOfFour(intValue));
	}

	/**
	 * 是否是4的幂
	 */
	public boolean isPowerOfFour(int num) {
		// (num & num - 1) num为2的幂
		// num & 0x2AAAAAAA 出现在奇数位置上
		return (num > 0) && (num & num - 1) == 0 && (num & 0x2AAAAAAA) == 0;
	}

	/**
	 * 给定一个自然数 n (n ≥ 2），将它拆分成 不少于 两个自然数之和，对这些拆分后的自然数求积，求最大的乘积
	 */
	public int getMaxIntegerBreak(int n) {
		org.springframework.util.Assert.isTrue(n >= 2, "n>=2");
		if (n == 2) {
			return 1;
		}
		if (n == 3) {
			return 2;
		}
		int k = (int) Math.floor(n / 3);
		int r = n % 3;
		if (r == 0) {
			return (int) Math.pow(3, k);
		} else if (r == 1) {
			return (int) (4 * Math.pow(3, k - 1));
		} else {
			return (int) (2 * Math.pow(3, k));
		}
	}

	private static int iterativeAtoi(String number) {
		int result = 0;
		char digit = number.charAt(0);
		short symbol = (short) 1;
		if (digit == '+' || digit == '-') {
			symbol = (digit == '+') ? (short) 1 : (short) -1;
		} else {
			result = digit - '0';
		}
		for (int i = 1, len = number.length(); i < len; i++) {
			digit = number.charAt(i);
			result = 10 * result + digit - '0';
		}
		return symbol * result;
	}

}
