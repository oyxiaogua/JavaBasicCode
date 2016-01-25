package com.xiaogua.better.algorithm;

import java.util.Arrays;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.junit.Test;

public class TestGetMaxK {
	@Test
	public void testTopKLargest() {
		int[] intArr = getTestRandomData(10, 1, 15);
		System.out.println(Arrays.toString(GetMaxKValueCode.topKLargest(intArr, 6)));
		Arrays.sort(intArr);
		System.out.println(Arrays.toString(intArr));
	}

	public int[] getTestRandomData(int len, int lower, int upper) {
		int[] resultArr = new int[len];
		for (int i = 0; i < len; i++) {
			resultArr[i] = new RandomDataGenerator().nextInt(lower, upper);
		}
		return resultArr;
	}
}
