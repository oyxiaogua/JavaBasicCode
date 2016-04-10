package com.xiaogua.better.algorithm;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Find_MajorityElementCode {

	// Majority Element 的数量大于 n/2 的元素,最后剩余的Majority Element个数最少为： n - ((n - k) +
	// (n - k)) = 2k - n
	public static int findMajorityElement(int[] numArr) {
		// 遍历数组,当碰到两个不一样的数字时,同时丢弃这两个数字
		int candidate = 0;
		for (int i = 0, count = 0, len = numArr.length; i < len; i++) {
			if (count == 0) {
				count++;
				candidate = numArr[i];
			} else if (candidate != numArr[i]) {
				count--;
			} else {
				count++;
			}
		}
		return candidate;
	}

	/**
	 * 找到大于n/3的元素
	 */
	public static List<Integer> findMajorityElementGtOneThird(int[] numArr) {
		int candidate0 = -1, candidate1 = 0, count0 = 0, count1 = 0;
		for (int i = 0, len = numArr.length; i < len; i++) {
			if (candidate0 == numArr[i]) {
				// 当前数字等于一号候选数字
				count0++;
			} else if (candidate1 == numArr[i]) {
				// 当前数字等于二号候选数字
				count1++;
			} else if (count0 == 0) {
				// 当前数字不等于 两个 candidate中的任意一个
				// 同时必须满足 count 等于 0，因为如果 count != 0，说明还有候选数字在等待与它一组的另外两个数字
				count0++;
				candidate0 = numArr[i];
			} else if (count1 == 0) {
				count1++;
				candidate1 = numArr[i];
			} else {
				// 只有 不满足以上所有条件我们才能对 count 进行减操作
				count0--;
				count1--;
			}
		}

		// 检验次数
		return Stream.of(candidate0, candidate1).distinct().filter(num -> {
			int count = 0;
			for (int i = 0, len = numArr.length; i < len; i++) {
				if (numArr[i] == num) {
					count++;
				}
			}
			return count > numArr.length / 3;
		}).collect(Collectors.toList());
	}

}
