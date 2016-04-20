package com.xiaogua.better.algorithm;

public class BitSortCode {

	/**
	 * 利用位图的方法给数组去重(不能有负数和0)
	 */
	public static int[] sortAndDelPositiveArrDupValueWithBitMap(int[] arr) {
		// 数据最大值与最小值
		int max = 0, min = 0;
		// 遍历数组，找出数组最大，最小值
		for (int intVal : arr) {
			if (max < intVal)
				max = intVal;
			if (min > intVal)
				min = intVal;
		}
		// 得到了最大，最小值就可以创建位图数组
		int[] flagArr = new int[max - min + 1];
		for (int i = 0, len = arr.length; i < len; i++) {
			flagArr[arr[i] - min] = arr[i];
		}

		int index = 0;
		// 以位图为标准对原数据进行重新赋值操作
		for (int j = 0, jLen = flagArr.length; j < jLen; j++) {
			if (flagArr[j] > 0) {
				arr[index++] = j + min;
			}
		}
		int[] newArr = new int[index];
		System.arraycopy(arr, 0, newArr, 0, index);
		return newArr;
	}

}
