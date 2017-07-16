package com.xiaogua.better.algorithm;

import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.Test;

public class TestGetKth {
	@Test
	public void testGetKth() {
		int[] nums = new int[] { 9, 1, 7, 6, 8, 2, 3 };
		int result = getKth(nums, 5);
		System.out.println(result);
	}

	public int getKth(int[] nums, int k) {
		Queue<Integer> heap = new PriorityQueue<>(k);
		for (int i = 0; i < nums.length; i++) {
			heap.add(nums[i]);
			if (i > k - 1)
				heap.poll();
		}

		return heap.poll();
	}
}
