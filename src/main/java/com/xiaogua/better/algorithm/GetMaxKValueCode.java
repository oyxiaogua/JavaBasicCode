package com.xiaogua.better.algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

public class GetMaxKValueCode {

	public static int[] topKLargest(int[] input, int k) {
		if (input == null || input.length == 0 || k < 1) {
			throw new IllegalArgumentException("invalid arguments");
		}
		// 升序
		PriorityQueue<Integer> minheap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				if (o1 < o2) {
					return -1;
				} else if (o1 > o2) {
					return 1;
				} else {
					return 0;
				}
			}
		});

		for (int i : input) {
			minheap.add(i);
			if (minheap.size() > k) {
				// 删除第一个
				minheap.poll();
			}
		}

		int[] out = new int[k];
		for (int i = 0; i < out.length; i++) {
			out[i] = minheap.poll();
		}
		return out;
	}
}
