package com.xiaogua.better.algorithm;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class TestBinarySearchCode {
	@Test
	public void testBinarySearchWithIteration() {
		int[] intArr = new int[] { 0, 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5 };

		int searchValue = -1;
		int index = -1, binaryIndex = -1;
		index = BinarySearchCode.binarySearchWithIteration(intArr, searchValue);
		binaryIndex = Arrays.binarySearch(intArr, searchValue);
		Assert.assertEquals(-1, index);
		Assert.assertEquals(binaryIndex, index);

		searchValue = 0;
		index = BinarySearchCode.binarySearchWithIteration(intArr, searchValue);
		binaryIndex = Arrays.binarySearch(intArr, searchValue);
		Assert.assertEquals(0, index);
		Assert.assertEquals(binaryIndex, index);

		searchValue = 1;
		index = BinarySearchCode.binarySearchWithIteration(intArr, searchValue);
		binaryIndex = Arrays.binarySearch(intArr, searchValue);
		Assert.assertEquals(1, index);
		Assert.assertEquals(binaryIndex, index);

		searchValue = 2;
		// 不是首次出现
		index = BinarySearchCode.binarySearchWithIteration(intArr, searchValue);
		binaryIndex = Arrays.binarySearch(intArr, searchValue);
		Assert.assertEquals(binaryIndex, index);
		System.out.println(String.format("value=%s,index=%s", searchValue, index));

		searchValue = 3;
		index = BinarySearchCode.binarySearchWithIteration(intArr, searchValue);
		binaryIndex = Arrays.binarySearch(intArr, searchValue);
		Assert.assertEquals(binaryIndex, index);
		System.out.println(String.format("value=%s,index=%s", searchValue, index));

		searchValue = 4;
		index = BinarySearchCode.binarySearchWithIteration(intArr, searchValue);
		binaryIndex = Arrays.binarySearch(intArr, searchValue);
		Assert.assertEquals(binaryIndex, index);
		System.out.println(String.format("value=%s,index=%s", searchValue, index));

		searchValue = 5;
		index = BinarySearchCode.binarySearchWithIteration(intArr, searchValue);
		binaryIndex = Arrays.binarySearch(intArr, searchValue);
		Assert.assertEquals(binaryIndex, index);
		System.out.println(String.format("value=%s,index=%s", searchValue, index));

		searchValue = 6;
		index = BinarySearchCode.binarySearchWithIteration(intArr, searchValue);
		binaryIndex = Arrays.binarySearch(intArr, searchValue);
		Assert.assertEquals(-1, index);
		Assert.assertNotEquals(binaryIndex, index);
		System.out.println(String.format("value=%s,index=%s,binary index=%s", searchValue, index, binaryIndex));
	}

	@Test
	public void testGetLastIndexWithBinarySearch() {
		int[] intArr = new int[] { 0, 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5 };
		int searchValue = -1;
		int index = -1;

		index = BinarySearchCode.getLastIndexWithBinarySearch(intArr, searchValue);
		Assert.assertEquals(-1, index);

		searchValue = 0;
		index = BinarySearchCode.getLastIndexWithBinarySearch(intArr, searchValue);
		Assert.assertEquals(0, index);

		searchValue = 1;
		index = BinarySearchCode.getLastIndexWithBinarySearch(intArr, searchValue);
		Assert.assertEquals(1, index);

		searchValue = 2;
		index = BinarySearchCode.getLastIndexWithBinarySearch(intArr, searchValue);
		Assert.assertEquals(3, index);

		searchValue = 3;
		index = BinarySearchCode.getLastIndexWithBinarySearch(intArr, searchValue);
		Assert.assertEquals(6, index);

		searchValue = 4;
		index = BinarySearchCode.getLastIndexWithBinarySearch(intArr, searchValue);
		Assert.assertEquals(10, index);

		searchValue = 5;
		index = BinarySearchCode.getLastIndexWithBinarySearch(intArr, searchValue);
		Assert.assertEquals(15, index);

		searchValue = 6;
		index = BinarySearchCode.getLastIndexWithBinarySearch(intArr, searchValue);
		Assert.assertEquals(-1, index);
	}

	@Test
	public void testGetFirstIndexWithBinarySearch() {
		int[] intArr = new int[] { 0, 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5 };
		int searchValue = -1;
		int index = -1;

		index = BinarySearchCode.getFirstIndexWithBinarySearch(intArr, searchValue);
		Assert.assertEquals(-1, index);

		searchValue = 0;
		index = BinarySearchCode.getFirstIndexWithBinarySearch(intArr, searchValue);
		Assert.assertEquals(0, index);

		searchValue = 1;
		index = BinarySearchCode.getFirstIndexWithBinarySearch(intArr, searchValue);
		Assert.assertEquals(1, index);

		searchValue = 2;
		index = BinarySearchCode.getFirstIndexWithBinarySearch(intArr, searchValue);
		Assert.assertEquals(2, index);

		searchValue = 3;
		index = BinarySearchCode.getFirstIndexWithBinarySearch(intArr, searchValue);
		Assert.assertEquals(4, index);

		searchValue = 4;
		index = BinarySearchCode.getFirstIndexWithBinarySearch(intArr, searchValue);
		Assert.assertEquals(7, index);

		searchValue = 5;
		index = BinarySearchCode.getFirstIndexWithBinarySearch(intArr, searchValue);
		Assert.assertEquals(11, index);

		searchValue = 6;
		index = BinarySearchCode.getFirstIndexWithBinarySearch(intArr, searchValue);
		Assert.assertEquals(-1, index);
	}

	@Test
	public void testGetInsertIndexWithBinarySearch() {
		int[] intArr = new int[] { 0, 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5 };
		int index = -1, binaryIndex = -1;
		int insertNum = -1;
		index = BinarySearchCode.getInsertIndexWithBinarySearch(intArr, insertNum);
		binaryIndex = Arrays.binarySearch(intArr, insertNum);
		System.out.println(String.format("value=%s,insert index=%s,binary index=%s,insert index=%s", insertNum, index,
				binaryIndex, Math.abs(binaryIndex) - 1));

		insertNum = 0;
		index = BinarySearchCode.getInsertIndexWithBinarySearch(intArr, insertNum);
		Assert.assertEquals(0, index);

		insertNum = 1;
		index = BinarySearchCode.getInsertIndexWithBinarySearch(intArr, insertNum);
		Assert.assertEquals(1, index);

		insertNum = 2;
		index = BinarySearchCode.getInsertIndexWithBinarySearch(intArr, insertNum);
		System.out.println(String.format("value=%s,insert index=%s", insertNum, index));

		insertNum = 3;
		index = BinarySearchCode.getInsertIndexWithBinarySearch(intArr, insertNum);
		System.out.println(String.format("value=%s,insert index=%s", insertNum, index));

		insertNum = 4;
		index = BinarySearchCode.getInsertIndexWithBinarySearch(intArr, insertNum);
		System.out.println(String.format("value=%s,insert index=%s", insertNum, index));

		insertNum = 5;
		index = BinarySearchCode.getInsertIndexWithBinarySearch(intArr, insertNum);
		System.out.println(String.format("value=%s,insert index=%s", insertNum, index));

		insertNum = 6;
		index = BinarySearchCode.getInsertIndexWithBinarySearch(intArr, insertNum);
		binaryIndex = Arrays.binarySearch(intArr, insertNum);
		System.out.println(String.format("value=%s,insert index=%s,binary index=%s,insert index=%s", insertNum, index,
				binaryIndex, Math.abs(binaryIndex) - 1));

	}

	@Test
	public void testGetAllIndexWithBinarySearch() {
		int[] intArr = new int[] { 0, 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5 };
		int[] indexArr = null;
		int searchValue = -1;
		indexArr = BinarySearchCode.getAllIndexWithBinarySearch(intArr, searchValue);
		Assert.assertArrayEquals(new int[0], indexArr);

		searchValue = 0;
		indexArr = BinarySearchCode.getAllIndexWithBinarySearch(intArr, searchValue);
		Assert.assertArrayEquals(new int[] { 0 }, indexArr);

		searchValue = 1;
		indexArr = BinarySearchCode.getAllIndexWithBinarySearch(intArr, searchValue);
		Assert.assertArrayEquals(new int[] { 1 }, indexArr);

		searchValue = 2;
		indexArr = BinarySearchCode.getAllIndexWithBinarySearch(intArr, searchValue);
		Assert.assertArrayEquals(new int[] { 2, 3 }, indexArr);

		searchValue = 3;
		indexArr = BinarySearchCode.getAllIndexWithBinarySearch(intArr, searchValue);
		Assert.assertArrayEquals(new int[] { 4, 5, 6 }, indexArr);

		searchValue = 4;
		indexArr = BinarySearchCode.getAllIndexWithBinarySearch(intArr, searchValue);
		Assert.assertArrayEquals(new int[] { 7, 8, 9, 10 }, indexArr);

		searchValue = 5;
		indexArr = BinarySearchCode.getAllIndexWithBinarySearch(intArr, searchValue);
		Assert.assertArrayEquals(new int[] { 11, 12, 13, 14, 15 }, indexArr);

		searchValue = 6;
		indexArr = BinarySearchCode.getAllIndexWithBinarySearch(intArr, searchValue);
		Assert.assertArrayEquals(new int[0], indexArr);
	}
}
