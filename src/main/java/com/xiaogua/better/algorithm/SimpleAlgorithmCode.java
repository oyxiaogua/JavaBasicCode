package com.xiaogua.better.algorithm;

import java.util.ArrayList;
import java.util.List;

public class SimpleAlgorithmCode {

	/**
	 * 连续数字和等于N <br/>
	 * 2 number:x +(x+1)= n -->2x = n - 1 <br/>
	 * 3 number:x+(x+1)+(x+2)=n -->3x=n-1-2<br/>
	 */
	public static List<List<Integer>> findSerialNumberEqualN(int n) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		int step = 1;
		int nums = 2;
		while ((n - step) > 0) {
			if ((n - step) % nums == 0) {
				List<Integer> item = new ArrayList<Integer>();
				for (int i = 0; i < nums; i++) {
					item.add((n - step) / nums + i);
				}
				list.add(item);
			}
			step += nums;
			nums++;
		}
		return list;
	}

	/**
	 * 负数排在前面,正数排在后面
	 */
	public static void changeArrValueWithSpecialRules(int[] dataArr) {
		int begin = 0;
		int end = dataArr.length - 1;
		int current = 0;
		while (current <= end) {
			if (dataArr[current] < 0) {
				if (current != begin) {
					swapArrValue(dataArr, current, begin);
				}
				current++;
				begin++;
			} else if (dataArr[current] == 0) {
				current++;
			} else {
				if (current != end) {
					swapArrValue(dataArr, current, end);
				}
				end--;
			}
		}
	}

	public static void swapArrValue(int[] dataArr, int i, int j) {
		dataArr[j] ^= dataArr[i];
		dataArr[i] ^= dataArr[j];
		dataArr[j] ^= dataArr[i];
	}
}
