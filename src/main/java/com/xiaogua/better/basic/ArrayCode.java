package com.xiaogua.better.basic;

import java.util.Arrays;

public class ArrayCode {
	/**
	 * 调整数组大小
	 * 
	 * @param oldArray
	 * @param newSize
	 * @return
	 */
	public static Object resizeArray(Object oldArray, int newSize) {
		int oldSize = java.lang.reflect.Array.getLength(oldArray);
		Class<?> elementType = oldArray.getClass().getComponentType();
		Object newArray = java.lang.reflect.Array.newInstance(elementType, newSize);
		int preserveLength = Math.min(oldSize, newSize);
		if (preserveLength > 0) {
			System.arraycopy(oldArray, 0, newArray, 0, preserveLength);
		}
		return newArray;
	}

	/**
	 * 获取数组维度
	 * 
	 * @param clazz
	 * @return
	 */
	public static int getArrayDimension(Class<?> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("class is null");
		}
		if (!clazz.isArray()) {
			return 0;
		}
		Class<?> arrType = clazz;
		int dim = 0;
		while (arrType != null && arrType.isArray()) {
			dim++;
			arrType = arrType.getComponentType();
		}
		return dim;
	}

	/**
	 * 把二维数组降为一维数组
	 */
	public static final <T> T[] dimensionalityReduction(T[][] arrTwo) {
		int size = 0;
		for (T[] ary : arrTwo) {
			size += ary.length;
		}
		T[] rs = Arrays.copyOf(arrTwo[0], size);
		int i = 0;
		for (T[] ary : arrTwo) {
			for (T str : ary) {
				rs[i] = str;
				i++;
			}
		}
		return rs;
	}

	/**
	 * 数组合并
	 * 
	 * @param arr0
	 * @param arr1
	 * @return
	 */
	public static final <T> T[] mergeArray(T[] arr0, T[] arr1) {
		if (null == arr0 || null == arr1) {
			return null;
		}
		final int baseLen = null == arr0 ? 0 : arr0.length;
		final int addLen = null == arr1 ? 0 : arr1.length;
		T[] rs = Arrays.copyOf(arr0, baseLen + addLen);
		System.arraycopy(arr1, 0, rs, baseLen, addLen);
		return rs;
	}

	/**
	 * 删除数组元素
	 * 
	 * @param data
	 * @param index
	 * @return
	 */
	public static final <T> T[] removeArrElement(T[] data, int index) {
		if (data == null || data.length == 0) {
			return data;
		}
		if (index < 0 || index > data.length - 1) {
			throw new IllegalArgumentException("index out of range");
		}
		T[] temp = Arrays.copyOf(data, data.length - 1);
		if (index < data.length - 1) {
			System.arraycopy(data, index + 1, temp, index, temp.length - index);
		}
		return temp;
	}
	
	public static String[] removeEmptyStrings(String[] original, boolean trim) {
		if (original==null||original.length == 0) {
			return original;
		}
		String[] strings = new String[original.length];
		int length = 0;
		for (String s : original) {
			if (s != null) {
				String trimmed = trim ? s.trim() : s;
				if (!trimmed.isEmpty()) {
					strings[length++] = s;
				}
			}
		}
		return Arrays.copyOfRange(strings, 0, length);
	}
}
