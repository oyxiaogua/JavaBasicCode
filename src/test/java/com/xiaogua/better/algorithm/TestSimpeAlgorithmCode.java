package com.xiaogua.better.algorithm;

import org.junit.Assert;
import org.junit.Test;

public class TestSimpeAlgorithmCode {
	@Test
	public void testIterativeAtoi() {
		int rtnNum = iterativeAtoi("-1");
		Assert.assertEquals(-1, rtnNum);
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
