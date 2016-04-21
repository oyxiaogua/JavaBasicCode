package com.xiaogua.better.algorithm;

public class GetMinKValueCode {
	/**
	 * 返回2个已排序数组合并后最小的k个值
	 */
	public static int[] getSortedArrMinKValue(int[] sortedArr, int[] sortedArr2, int k) {
		int i = 0, m = 0, n = 0;
		int[] result = new int[k];
		for (; i < k; i++) {
			if (m < sortedArr.length && n < sortedArr2.length) {
				if (sortedArr[m] <= sortedArr2[n]) {
					result[i] = sortedArr[m++];
				} else {
					result[i] = sortedArr2[n++];
				}
			} else if (m < sortedArr.length) {
				result[i] = sortedArr[m++];
			} else {
				result[i] = sortedArr2[n++];
			}
		}
		return result;
	}
}
