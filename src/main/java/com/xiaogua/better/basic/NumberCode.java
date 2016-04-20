package com.xiaogua.better.basic;

public class NumberCode {


	/**
	 * 是否是偶数 A%B=A-(A/B)* B
	 */
	public static boolean isEven(int num) {
		if (num % 2 == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是否是奇数
	 */
	public static boolean isOdd(int num) {
		return num % 2 != 0;
	}

}
