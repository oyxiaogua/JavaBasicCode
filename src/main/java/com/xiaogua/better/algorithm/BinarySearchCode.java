package com.xiaogua.better.algorithm;

public class BinarySearchCode {
	/**
	 * 二分搜索算法
	 */
	public static int binarySearchWithIteration(int[] array, int value) {
		int left = 0;
		int right = array.length - 1;
		while (left <= right) {
			int middle = left + ((right - left) >> 1);
			if (array[middle] > value) {
				right = middle - 1;
			} else if (array[middle] < value) {
				left = middle + 1;
			} else
				return middle;
		}
		return -1;
	}

	/**
	 * 查找重复元素最后一次出现的位置
	 */
	public static int getLastIndexWithBinarySearch(int[] array, int value) {
		int left = 0;
		int right = array.length - 1;
		//避免死循环(只有两个元素时候left right不变导致死循环)
		while (left < right) {
			//避免连续的两个数取平均时会得到第一个数
			int middle = left + ((right - left + 1) >> 1);
			if (array[middle] <= value) {
				//忽略前一半
				left = middle;
			} else {
				//忽略后一半
				right = middle - 1;
			}
		}
		//判断当前的元素是否为目标元素，如果是就返回下标，否则返回-1
		return array[left] == value ? left : -1;
	}

	/**
	 * 查找重复元素第一次出现的位置
	 */
	public static int getFirstIndexWithBinarySearch(int[] array, int value) {
		int left = 0;
		int right = array.length - 1;
		//只有一个元素的时候死循环(left==right)
		while (left < right) {
			int middle = left + ((right - left) >> 1);
			if (array[middle] >= value) {
				// 忽略后一半
				right = middle;
			} else {
				// 忽略前一半
				left = middle + 1;
			}
		}
		//判断当前的元素是否为目标元素，如果是就返回下标，否则返回-1
		return array[left] == value ? left : -1;
	}

}
