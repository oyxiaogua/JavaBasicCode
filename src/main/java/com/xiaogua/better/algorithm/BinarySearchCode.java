package com.xiaogua.better.algorithm;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

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
		// 避免死循环(只有两个元素时候left right不变导致死循环)
		while (left < right) {
			// 避免连续的两个数取平均时会得到第一个数
			int middle = left + ((right - left + 1) >> 1);
			if (array[middle] <= value) {
				// 忽略前一半
				left = middle;
			} else {
				// 忽略后一半
				right = middle - 1;
			}
		}
		// 判断当前的元素是否为目标元素，如果是就返回下标，否则返回-1
		return array[left] == value ? left : -1;
	}

	/**
	 * 查找重复元素第一次出现的位置
	 */
	public static int getFirstIndexWithBinarySearch(int[] array, int value) {
		int left = 0;
		int right = array.length - 1;
		// 只有一个元素的时候死循环(left==right)
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
		// 判断当前的元素是否为目标元素，如果是就返回下标，否则返回-1
		return array[left] == value ? left : -1;
	}

	/**
	 * 查询插入的位置,插入后仍有序
	 */
	public static int getInsertIndexWithBinarySearch(int[] array, int value) {
		int left = 0;
		int right = array.length - 1;
		// 如果>=最大值
		if (array[right] <= value) {
			return right + 1;
		}
		// 如果<=最小值
		if (array[left] >= value) {
			return left;
		}
		while (left < right) {
			int mid = left + ((right - left) >> 1);
			if (array[mid] >= value) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return right;
	}

	/**
	 * 获取值等于value的所有下标
	 */
	public static int[] getAllIndexWithBinarySearch(int[] array, int value) {
		List<Integer> result = new ArrayList<Integer>();
		int left = 0, right = array.length - 1;
		while (left <= right) {
			int mid = left + ((right - left) >> 1);
			if (array[mid] == value) {
				if (mid > 0) {
					// 前一个元素等于目标元素
					if (array[mid - 1] == value) {
						for (int i = mid - 1; i >= 0; i--) {
							if (array[i] == value) {
								result.add(i);
							} else {
								break;
							}
						}
					}
				}
				// 等于value的索引
				result.add(mid);
				if (mid < right) {
					// 后一个元素等于目标元素
					if (array[mid + 1] == value) {
						for (int i = mid + 1; i <= right; i++) {
							if (array[i] == value) {
								result.add(i);
							} else {
								break;
							}
						}
					}
				}
				return ArrayUtils.toPrimitive(result.toArray(new Integer[0]));
			} else if (array[mid] > value) {
				right = mid - 1;
			} else if (array[mid] < value) {
				left = mid + 1;
			}
		}
		return ArrayUtils.toPrimitive(result.toArray(new Integer[0]));
	}

}
