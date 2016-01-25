package com.xiaogua.better.sort;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public class TestBubbleSortCode {
	@Test
	public void testBubbleSort() {
		int[] arr1 = new int[100];
		for (int i = 0; i < arr1.length; i++) {
			arr1[i] = new Random().nextInt(800) + 1;
		}
		BubbleSortCode.bubbleSort(arr1);
		System.out.println(Arrays.toString(arr1));
	}
}
