package com.xiaogua.better.algorithm;

import java.util.Arrays;

import org.junit.Test;

public class TestGetMinKValueCode {

	@Test
	public void testGetSortedArrMinKValue() {
		int[] sortArr = new int[] { -1, 1, 2, 2, 3, 4, 4, 5, 11 };
		int[] sortArr2 = new int[] { 2, 3, 3, 4, 6, 6, 7, 9 };
		int[] minKArr = GetMinKValueCode.getSortedArrMinKValue(sortArr, sortArr2, 12);
		System.out.println(Arrays.toString(minKArr));
	}
}
