package com.xiaogua.better.algorithm;

public class GrayCode {
	// 产生 0, 1 两个字符串。
	// 在第一步的基础上，每一个字符串都加上0和1，但是每次只能加一个，所以得做两次。这样就变成了 00,01,11,10
	// 在第二步的基础上，再给每个字符串都加上0和1，同样，每次只能加一个，这样就变成了 000,001,011,010,110,111,101,100
	public static String[] generateGrayCode(int n) {
		if (n < 1) {
			throw new IllegalArgumentException();
		}
		// 数组的大小是2的n次方
		String[] grayCodeArr = new String[(int) Math.pow(2, n)];
		if (n == 1) {
			grayCodeArr[0] = "0";
			grayCodeArr[1] = "1";
			return grayCodeArr;
		}
		// n-1 位格雷码的生成方式
		String[] before = generateGrayCode(n - 1);
		for (int i = 0; i < before.length; i++) {
			grayCodeArr[i] = "0" + before[i];
			grayCodeArr[grayCodeArr.length - 1 - i] = "1" + before[i];
		}
		return grayCodeArr;
	}

}
