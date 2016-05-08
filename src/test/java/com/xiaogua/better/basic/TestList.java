package com.xiaogua.better.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.xiaogua.better.bean.Sub_Dog;
import com.xiaogua.better.bean.Super_Animal;

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

	@Test(expected = ConcurrentModificationException.class)
	public void testAddElementException() {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		for (String s : list) {
			if (s.equals("2")) {
				list.add("4");
			}
		}
		System.out.println(list.size());
	}

	@Test
	public void testIsListSortedWithGuava() {
		List<String> secConferenceEast = Lists.newArrayList("Florida", "Georgia", "Missouri", "South Carolina",
				"Tennessee", "Vanderbilt", null);
		boolean isSorted = Ordering.natural().nullsLast().isOrdered(secConferenceEast);
		System.out.println(isSorted);
	}

	@Test
	public void testRemovingNullsWithJava() {
		final List<Integer> list = Lists.newArrayList(null, 1, null);
		while (list.remove(null))
			;
		Assert.assertTrue(list.size() == 1);
		Assert.assertEquals(1, list.get(0).intValue());
	}

	@Test
	public void testRemovingNullsWithJava2() {
		final List<Integer> list = Lists.newArrayList(null, 1, null);
		list.removeAll(Collections.singleton(null));
		Assert.assertTrue(list.size() == 1);
		Assert.assertEquals(1, list.get(0).intValue());
	}

	@Test
	public void testConvertListValue() {
		List<List<String>> list = new ArrayList<List<String>>();
		list.add(Arrays.<String> asList(null, "a"));
		list.add(Arrays.<String> asList(null, "b"));
		list.add(Arrays.<String> asList(null, null));
		list.add(Arrays.<String> asList());
		List<String> rtnList = ListCode.convertListListWithFlatMap(list);
		System.out.println(rtnList);

		List<String> rtnList2 = ListCode.convertListValueWithReduce(list);
		System.out.println(rtnList2);
	}

	@Test
	public void testListSize() {
		List<String> list = new ArrayList<String>(256);
		list.add("test_1");
		list.add("test_2");
		Assert.assertEquals(2, list.size());
	}

	@Test
	public void testListSort() {
		List<Super_Animal> animalList = new ArrayList<Super_Animal>();
		animalList.add(new Super_Animal(1));
		animalList.add(new Super_Animal(33));
		animalList.add(new Super_Animal(21));
		animalList.add(new Super_Animal(12));

		List<Sub_Dog> dogList = new ArrayList<Sub_Dog>();
		dogList.add(new Sub_Dog(1));
		dogList.add(new Sub_Dog(33));
		dogList.add(new Sub_Dog(21));
		dogList.add(new Sub_Dog(12));

		mySort(animalList);
		System.out.println(animalList);

		// mySort(dogList);----->error
		// System.out.println(dogList);

		mySort2(animalList);
		System.out.println(animalList);

		mySort2(dogList);
		System.out.println(dogList);

	}

	@Test
	public void testRemoveListBlankElementWithIterator() {
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add(" ");
		list.add("  ");
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			String s = iterator.next();
			if (StringUtils.isBlank(s)) {
				iterator.remove();
			}
		}
		System.out.println("list:" + list);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveListBlankElement() {
		// 不可修改
		List<String> list = Arrays.asList("1", "2", "3", "", " ");
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			String s = iterator.next();
			if (StringUtils.isBlank(s)) {
				iterator.remove();
			}
		}
		System.out.println("list:" + list);
	}

	@Test
	public void testIteratorList() {
		List<String> nameList = Lists.newArrayList("张三", "李四");
		for (Iterator<String> iterator = nameList.iterator(); iterator.hasNext();) {
			System.out.println("name1:" + iterator.next());
			iterator.remove();
		}
		Assert.assertEquals(0, nameList.size());
		List<String> nameList2 = Lists.newArrayList("赵六", "钱七");
		for (Iterator<String> iterator = nameList2.iterator(); iterator.hasNext();) {
			System.out.println("name2:" + iterator.next());
			iterator.remove();
		}
		Assert.assertEquals(0, nameList2.size());
	}

	@Test
	public void testListInsertWithOrder() {
		// 有序插入 迭代查找
		int[] intArr = new int[] { 1, 0, -1, -3, 9, 3, 5, -4, 3, 2, 4, 3, 5, 10, -1, -2 };
		List<Integer> list = new LinkedList<Integer>();
		ListIterator<Integer> listIterator = null;
		boolean insertFlag = false;
		int i = Integer.MIN_VALUE;
		for (int intValue : intArr) {
			listIterator = list.listIterator();
			insertFlag = false;
			while (listIterator.hasNext()) {
				i = listIterator.next();
				if (i < intValue) {
					continue;
				} else {
					listIterator.set(intValue);
					listIterator.add(i);
					insertFlag = true;
					break;
				}
			}
			if (!insertFlag) {
				listIterator.add(intValue);
			}
		}
		System.out.println(list);
	}

	@Test
	public void testListInsertWithOrder2() {
		// 有序插入 排序
		int[] intArr = new int[] { 1, 0, -1, -3, 9, 3, 5, -4, 3, 2, 4, 3, 5, 10, -1, -2 };
		List<Integer> list = new ArrayList<Integer>();
		for (int intValue : intArr) {
			list.add(intValue);
		}
		Collections.sort(list);
		System.out.println(list);
	}

	@Test
	public void testListInsertWithOrder3() {
		// 有序插入 二分搜索
		int[] intArr = new int[] { 1, 0, -1, -3, 9, 3, 5, -4, 3, 2, 4, 3, 5, 10, -1, -2 };
		List<Integer> list = new LinkedList<Integer>();
		int index = -1;
		for (int intValue : intArr) {
			index = Collections.binarySearch(list, intValue);
			if (index < 0) {
				list.add(Math.abs(index) - 1, intValue);
			} else {
				list.add(index, intValue);
			}
		}
		System.out.println(list);
	}

	@Test
	public void testSwapListValue() {
		List<String> list = new ArrayList<String>();
		list.add("test_1");
		list.add("test_2");
		list.add("test_3");
		System.out.println(list);
		list.set(0, list.set(2, list.get(0)));
		System.out.println(list);
	}

	// T实现了Comparable接口
	public static <T extends Comparable<T>> void mySort(List<T> list) {
		Collections.sort(list);
	}

	// T类型或者是T的父类型必须实现了Comparable接口
	public static <T extends Comparable<? super T>> void mySort2(List<T> list) {
		Collections.sort(list);
	}

}
