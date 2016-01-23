package com.xiaogua.better.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

public class TestList {
	@Test
	public void tesCollectionstUnmodifiableList() {
		final List<String> list = new ArrayList<String>(Arrays.asList("1", "2", "3"));
		final List<String> unmodifiableList = Collections.unmodifiableList(list);
		Assert.assertTrue(unmodifiableList.size() == 3);
		list.remove(0);
		// 原始数据改变,unmodifiableList跟着改变
		Assert.assertTrue(unmodifiableList.size() == 2);
	}
	
	@Test
	public void tesCollectionstSynchronizedList() {
		final List<String> list = new ArrayList<String>(Arrays.asList("1", "2", "3"));
		final List<String> synchronizedList = Collections.synchronizedList(list);
		Assert.assertTrue(synchronizedList.size() == 3);
		list.remove(0);
		// 原始数据改变,synchronizedList跟着改变 线程安全(原子方法)
		Assert.assertTrue(synchronizedList.size() == 2);
		synchronizedList.add("4");
		Assert.assertEquals("4", list.get(2));
	}

	@Test
	public void testIntPrimitiveArrToList() {
		int[] a = new int[] { 1, 2, 3, 4, 5 };// 基本类型的数组
		List<?> aList = Arrays.asList(a);
		Assert.assertTrue(aList.size() == 1);
		System.out.println(aList.get(0).getClass().getName());
		Assert.assertEquals(2, ((int[]) aList.get(0))[1]);

		Integer[] a3 = new Integer[] { 1, 2, 3, 4, 5 };
		List<Integer> objList = Arrays.asList(a3);
		Assert.assertTrue(objList.size() == 5);
		System.out.println(objList.get(0).getClass().getName());
		Assert.assertEquals(2, objList.get(1).intValue());

		List<Integer> objList2 = Arrays.asList(ArrayUtils.toObject(a));
		System.out.println(objList2);
	}

	@Test
	public void testDelListElement() {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add(null);
		list.add("6");
		for (int i = 0; i < list.size(); i++) {
			list.remove(i);
			i--;// 调整i
		}
		Assert.assertTrue(list.size() == 0);
	}

	@Test
	public void testDelListElement_2() {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add(null);
		list.add("6");
		// 从后面删除
		for (int i = list.size() - 1; i >= 0; i--) {
			list.remove(i);
		}
		Assert.assertTrue(list.size() == 0);
	}

	@Test
	public void testDelListElement_3() {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add(null);
		list.add("6");
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			it.next();
			// 迭代器删除
			it.remove();
		}
		Assert.assertTrue(list.size() == 0);
	}
}
