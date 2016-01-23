package com.xiaogua.better.basic;

import java.util.Random;

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
}
