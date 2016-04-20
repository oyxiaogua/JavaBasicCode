package com.xiaogua.better.algorithm;

import java.util.Arrays;

import org.junit.Test;

public class TestBitSortCode {

	@Test
	public void testSortAndDelDupValueWithBitMap() {
		int[] intArr = new int[] { 1, 2, 5, 3, 4, 5, 4, 3, 5, 6, 3, 1, 9, 19, 10, 13, 11 };
		int[] rtnArr = BitSortCode.sortAndDelPositiveArrDupValueWithBitMap(intArr);
		System.out.println(Arrays.toString(rtnArr));
	}
}
