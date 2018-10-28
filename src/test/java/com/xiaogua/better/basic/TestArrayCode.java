package com.xiaogua.better.basic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Spliterator.OfInt;
import java.util.function.IntConsumer;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

public class TestArrayCode {
	@Test
	public void testResizeArray() {
		int[] a = { 1, 2, 3 };
		a = (int[]) ArrayCode.resizeArray(a, 5);
		Assert.assertEquals(5, a.length);
	}

	@Test
	public void testPrintArr() {
		int[] intArr = new int[] { 1, 2, 3, 4, 5 };
		System.out.println(Arrays.toString(intArr));

		int[][] mutilArr = new int[3][];
		mutilArr[0] = new int[] { 1, 2, 3 };
		mutilArr[1] = new int[] { 4, 5, 6, 7, 8 };
		mutilArr[2] = new int[] { 9, 10, 11, 12, 13 };
		System.out.println(Arrays.deepToString(mutilArr));
	}

	@Test
	public void testGetArrayDimension() {
		String[][] resultArr = new String[][] { new String[] { "key_1", null }, new String[] {} };
		Assert.assertEquals(2, ArrayCode.getArrayDimension(resultArr.getClass()));

		String[] resultArr2 = new String[] {};
		Assert.assertEquals(1, ArrayCode.getArrayDimension(resultArr2.getClass()));
	}

	@Test
	public void testDimensionalityReduction() {
		String[][] result = new String[][] { new String[] { "key_1", null }, new String[] {} };
		String[] rtnArr = ArrayCode.dimensionalityReduction(result);
		System.out.println(Arrays.toString(rtnArr));
		Assert.assertEquals(2, rtnArr.length);
	}

	@Test
	public void testMergeArray() {
		String[] strArr = new String[] { "test_1", "test_2" };
		String[] strArr2 = new String[] { "test_3" };
		String[] restArr = ArrayCode.mergeArray(strArr, strArr2);
		Assert.assertEquals("test_3", restArr[2]);
	}

	@Test
	public void testRemoveArrElement() {
		String[] strArr = new String[] { "key_1", "key_2", "key_3" };
		strArr = ArrayCode.removeArrElement(strArr, 1);
		Assert.assertEquals("key_3", strArr[1]);
		strArr = ArrayCode.removeArrElement(strArr, 1);
		Assert.assertEquals("key_1", strArr[0]);
	}

	@Test
	public void testArraysCopy() {
		String[] strArr = new String[] { "key_1", "key_2", "key_3", "key_3", "key_4" };
		String[] rtnArr = Arrays.copyOf(strArr, 2);
		Assert.assertEquals(2, rtnArr.length);
		rtnArr[0] = "update__dest_1";
		Assert.assertEquals("key_1", strArr[0]);

		strArr = new String[] { "key_1", "key_2", "key_3", "key_3", "key_4" };
		rtnArr = Arrays.copyOf(strArr, 2);
		strArr[0] = "update_src_2";
		Assert.assertEquals("key_1", rtnArr[0]);

		strArr = new String[] { "key_1", "key_2", "key_3", "key_3", "key_4" };
		rtnArr = Arrays.copyOfRange(strArr, 1, 3);// [start,end)
		Assert.assertEquals(2, rtnArr.length);
		rtnArr[0] = "update__dest_1";
		Assert.assertEquals("key_2", strArr[1]);

		strArr = new String[] { "key_1", "key_2", "key_3", "key_3", "key_4" };
		rtnArr = Arrays.copyOfRange(strArr, 1, 3);// [start,end)
		Assert.assertEquals(2, rtnArr.length);
		strArr[1] = "update_src_2";
		Assert.assertEquals("key_2", rtnArr[0]);
	}

	@Test
	public void testCreateMutilDimensionArray() {
		int[] dims = new int[] { 5, 10, 15 };//
		Object array = Array.newInstance(Integer.TYPE, dims);// 三维数组
		Object arrayObj = Array.get(array, 3);// 下标为3
		Class<?> cls = arrayObj.getClass().getComponentType();
		// 范围对象的主键类型,即二维数组
		System.out.println(cls);
		arrayObj = Array.get(arrayObj, 5);// 下标为3，5
		Array.setInt(arrayObj, 10, 37);// 下标为3，5，10的值设为37
		int arrayCast[][][] = (int[][][]) array;
		Assert.assertEquals(37, arrayCast[3][5][10]);
	}

	@Test
	public void testCreateSimpleArr() throws Exception {
		Class<?> classType = Class.forName("java.lang.String");
		// 创建一个长度为10的字符串数组
		Object array = Array.newInstance(classType, 10);
		// 把索引位置为5的元素设为"hello"
		Array.set(array, 5, "hello");
		// 获得索引位置为5的元素的值
		String s = (String) Array.get(array, 5);
		Assert.assertEquals("hello", s);
	}

	@Test
	public void testRemoveEmptyStrings() {
		String[] strArr = new String[] { " ", null, "    ", "", "a" };
		String[] rtnArr = ArrayCode.removeEmptyStrings(strArr, true);
		System.out.println(Arrays.toString(rtnArr));
	}

	@Test
	public void testCreateMutilDimensionArray2() {
		int[] initArr = new int[] { 1, 2, 3, 4, 5 };
		int[][] intArr = new int[][] { initArr };// int[1][initArr.length]
		System.out.println(intArr.length + "," + intArr[0].length);
		System.out.println(Arrays.deepToString(intArr));
	}

	@Test
	public void testArraySpliterator() {
		int len = 10;
		int[] intArr = new int[len];
		Arrays.setAll(intArr, i -> i);
		OfInt rtnInt = Arrays.spliterator(intArr);
		System.out.println(rtnInt.getExactSizeIfKnown());
		OfInt trySplitInt = rtnInt.trySplit();
		System.out.println(trySplitInt.getClass());
		System.out.println(trySplitInt.getExactSizeIfKnown() + "," + rtnInt.getExactSizeIfKnown());
		OfInt split2 = trySplitInt.trySplit();
		System.out.println(split2.getExactSizeIfKnown() + "," + trySplitInt.getExactSizeIfKnown() + ","
				+ rtnInt.getExactSizeIfKnown());
		IntConsumer consumer = (i2) -> System.out.println(i2);
		split2.forEachRemaining(consumer);
		System.out.println("-----------------");
		trySplitInt.forEachRemaining(consumer);
		System.out.println("-----------------");
		rtnInt.forEachRemaining(consumer);
	}

	@Test
	public void testApacheCommonArrayUtilsContain() {
		// better way to check array contain value
		int[] intArr = new int[] { 1, 3, 90, 11, -8, 0, 34, 55 };
		boolean isContain = ArrayUtils.contains(intArr, 11);
		Assert.assertTrue(isContain);
	}

	@Test
	public void testGetArrayLen() {
		int[][][] mutilArr = { { { 0 }, { 1, 2 }, { 3, 4, 5 } }, { { 1, 2, 3, 4 } } };
		System.out.println(Arrays.deepToString(mutilArr[0]));
		Class<?> cls = mutilArr.getClass();
		boolean isArr = cls.isArray();
		Assert.assertTrue(isArr);
		String clsName = cls.getName();
		System.out.println("name=" + clsName);
		int nrDims = 1 + clsName.lastIndexOf('[');
		Object obj = mutilArr;
		int arrLen = 0;
		for (int n = 0; n < nrDims; n++) {
			arrLen = Array.getLength(obj);
			System.out.println("dim[" + n + "]=" + arrLen);
			if (arrLen > 0) {
				obj = Array.get(obj, 0);
			}
		}
		List<Integer> dimList = new ArrayList<Integer>();
		printArrLen(mutilArr, dimList);
	}

	@Test
	public void testArrayUtilsToPrimitive() {
		Integer[] intPkgArr = new Integer[] { 1, 2, 3, 4 };
		int[] intArr = ArrayUtils.toPrimitive(intPkgArr);
		System.out.println(ArrayUtils.toString(intArr));
		Integer[] intPkgArr2 = ArrayUtils.toObject(intArr);
		System.out.println(ArrayUtils.toString(intPkgArr2));
	}

	@Test
	public void testArrayUtilsAdd() {
		String[] strArr = { "1", "2", "3" };
		String[] newStrArr = ArrayUtils.add(strArr, null);
		System.out.println(ArrayUtils.toString(newStrArr));
	}

	public void printArrLen(Object obj, List<Integer> dimList) {
		int arrLen = Array.getLength(obj);
		Object tmpObj = null;
		System.out.println("arr" + dimList + " len=" + arrLen);
		for (int n = 0; n < arrLen; n++) {
			tmpObj = Array.get(obj, n);
			if (tmpObj.getClass().isArray()) {
				dimList.add(n);
				printArrLen(tmpObj, dimList);
				dimList.remove(dimList.size() - 1);
			}
		}
	}

}
