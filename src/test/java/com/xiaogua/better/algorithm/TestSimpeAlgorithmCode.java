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
		int[] dataArr = new int[] { -1,1, 0, 3, 5, -1, -2, 1, -2, 3,-3 };
		SimpleAlgorithmCode.changeArrValueWithSpecialRules(dataArr);
		System.out.println(Arrays.toString(dataArr));
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
