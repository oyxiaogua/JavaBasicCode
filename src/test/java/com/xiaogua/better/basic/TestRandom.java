package com.xiaogua.better.basic;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class TestRandom {
	@Test
	public void testShuffleCharArr() {
		String str = "1234567890";
		char[] charArr = str.toCharArray();
		shuffleCharArr(charArr);
		System.out.println(new String(charArr));
	}

	@Test
	public void testRandFixedValue() {
		// 1 1 1 1 1 1 1 1 1 1
		Random random1 = new Random(441287210);
		int[] resultArr = new int[10];
		int[] expectArr = new int[10];

		for (int i = 0; i < 10; i++) {
			resultArr[i] = random1.nextInt(10);
			expectArr[i] = 1;
		}
		Assert.assertArrayEquals(expectArr, resultArr);
	}

	@Test
	public void testRandFixedValue2() {
		// 0 1 2 3 4 5 6 7 8 9
		Random random = new Random(-6732303926L);
		int[] resultArr = new int[10];
		int[] expectArr = new int[10];
		for (int i = 0; i < 10; i++) {
			resultArr[i] = random.nextInt(10);
			expectArr[i] = i;
		}
		Assert.assertArrayEquals(expectArr, resultArr);
	}

	@Test
	public void testGetRandomColor() {
		System.out.println(getRandomColor());
	}

	@Test
	public void testGetRandRate() {
		double rateA[] = { 0.1, 0.3, 0.2, 0.4 };
		int index = getRandRate(rateA);
		System.out.println(index);
	}

	@Test
	public void testShuffle() {
		int[] intArr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
		RandomCode.shuffle(intArr);
		System.out.println(Arrays.toString(intArr));
	}

	@Test
	public void testRandomString() {
		System.out.println(RandomCode.randomString(10));
	}

	private int getRandRate(double rateA[]) {
		double random = new Random().nextDouble();
		for (int i = 0; i < rateA.length; i++) {
			if (random <= rateA[i]) {
				return i;
			}
			random -= rateA[i];
		}
		return rateA.length;
	}

	private String getRandomColor() {
		// 红色
		String red = null;
		// 绿色
		String green = null;
		// 蓝色
		String blue = null;
		// 生成随机对象
		Random random = new Random();
		// 生成红色颜色代码
		red = Integer.toHexString(random.nextInt(256)).toUpperCase();
		// 生成绿色颜色代码
		green = Integer.toHexString(random.nextInt(256)).toUpperCase();
		// 生成蓝色颜色代码
		blue = Integer.toHexString(random.nextInt(256)).toUpperCase();

		// 判断红色代码的位数
		red = red.length() == 1 ? "0" + red : red;
		// 判断绿色代码的位数
		green = green.length() == 1 ? "0" + green : green;
		// 判断蓝色代码的位数
		blue = blue.length() == 1 ? "0" + blue : blue;
		// 生成十六进制颜色值
		String color = "#" + red + green + blue;
		return color;
	}

	private void shuffleCharArr(char[] ar) {
		Random rnd = new Random();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			char a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}

}
