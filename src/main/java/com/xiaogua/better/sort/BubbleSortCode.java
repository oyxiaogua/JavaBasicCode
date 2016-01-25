package com.xiaogua.better.sort;

public class BubbleSortCode {
	public static void bubbleSort(int[] arr) {
		boolean flag = true;
		for (int i = 0; i < arr.length - 1 && flag; i++) {
			flag = false;
			for (int j = arr.length - 1; j > i; j--) {
				if (arr[j] < arr[j - 1]) {
					flag = true;
					int tmp = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = tmp;
				}
			}
		}
	}
}
