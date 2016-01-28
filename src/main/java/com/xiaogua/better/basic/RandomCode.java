package com.xiaogua.better.basic;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomCode {
	protected static final String baseChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	public static int[] shuffle(int[] array) {
		int index;
		Random random = new Random();
		for (int i = array.length - 1; i > 0; i--) {
			index = random.nextInt(i + 1);
			if (index != i) {
				array[index] ^= array[i];
				array[i] ^= array[index];
				array[index] ^= array[i];
			}
		}

		return array;
	}

	public static String randomString(int len) {
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(baseChars.charAt(rnd.nextInt(baseChars.length())));
		return sb.toString();
	}

	public static String randomStringWithCommonLang3(int len) {
		return RandomStringUtils.random(len, baseChars);
	}

	public static String getRandomBinaryStr(int len) {
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		sb.append('1');
		for (int i = 1; i < len; i++) {
			sb.append(rnd.nextInt(2));
		}
		return sb.toString();
	}

	/**
	 * 产生len个[min,max)随机数
	 * 
	 * @param min
	 * @param max
	 * @param len
	 * @return
	 */
	public static int[] generateRandomArray(int min, int max, int len) {
		int[] rtnArr = new int[len];
		for (int i = 0; i < len; i++) {
			rtnArr[i] = randomInt(min, max);
		}
		return rtnArr;
	}

	/**
	 * 产生[min,max)范围的随机数
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randomInt(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min) + min;
	}

	/**
	 * 产生len个[min,max)随机数(jdk7)
	 * 
	 * @param min
	 * @param max
	 * @param len
	 * @return
	 */
	public static int[] generateRandomArrayWithJava7(int min, int max, int len) {
		int[] randomArray = new int[len];
		for (int i = 0; i < len; i++) {
			randomArray[i] = ThreadLocalRandom.current().nextInt(min, max);
		}
		return randomArray;
	}

	/**
	 * 产生len个[min,max)随机数(jdk8)
	 * 
	 * @param min
	 * @param max
	 * @param len
	 * @return
	 */
	public static int[] generateRandomArrayWithJava8(int min, int max, int len) {
		Random random = new Random();
		IntStream intStream = random.ints(len, min, max);
		return intStream.toArray();
	}

	/**
	 * 随机产生和为S的N个随机正整数
	 * 
	 * @param sumOfN
	 * @param len
	 * @param ordered
	 * @return
	 */
	public static int[] generateRandomArrSumOfN(int sumOfN, int len, boolean ordered) {
		Set<Integer> set = new TreeSet<Integer>();
		/*
		 * 先将最两端的刻度加入到集合中去。
		 */
		set.add(0);
		set.add(sumOfN);

		Random random = new Random();
		while (set.size() < len + 1) {
			set.add(random.nextInt(sumOfN - 1) + 1);
		}
		Integer[] locations = new Integer[set.size()];
		set.toArray(locations);

		int[] result = new int[len];
		/*
		 * 计算相邻刻度之间的长度，得到的数值就可以认为是随机数:
		 */
		for (int i = 0; i < result.length; i++) {
			result[i] = locations[i + 1] - locations[i];
		}

		if (ordered) {
			Arrays.sort(result);
		}
		return result;
	}
}
