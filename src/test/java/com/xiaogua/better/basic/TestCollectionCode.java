package com.xiaogua.better.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TestCollectionCode {
	@Test
	public void testSortCnList() {
		List<String> list = new ArrayList<String>();
		list.add("则");
		list.add("铂");
		list.add("啊");
		list.add(null);
		list.add("add");
		list.add("fork");
		CollectionCode.sortCnList(list);
		System.out.println(list);
	}

	@Test
	public void testSortCnArray() {
		List<String> list = new ArrayList<String>();
		list.add("则");
		list.add("铂");
		list.add("啊");
		list.add(null);
		list.add("add");
		list.add("fork");
		String[] strArr = list.toArray(new String[0]);
		CollectionCode.sortCnArray(strArr);
		System.out.println(Arrays.toString(strArr));
	}
	
	@Test
	public void testCollectionsFrequency() {
		String strs = "hello world what a small world";
		System.out.println(strs);
		List<String> tempList = Arrays.asList(strs.split(" "));
		for (String stemp : tempList) {
			System.out.println(stemp + " ->times:" + Collections.frequency(tempList, stemp));
		}
	}
	@Test
	public void testEmptyListIterator() {
		Iterator<String> strItor = Collections.emptyIterator();
		Assert.assertFalse(strItor.hasNext());
	}
	
	@Test
	public void testCollectionsCheckedList(){
		List<String> list = new ArrayList<String>();
		list.add("test");
		list.add("a");
		list.add("b");
		list.add("c");
		//TODO 未知api
		List<String> rtn = Collections.checkedList(list, String.class);
		System.out.println(rtn);
	}
	
	@Test
	public void testCollectionsNCopies(){
		List<Integer> rtnList = Collections.nCopies(3, 1);
		System.out.println(rtnList);
	}
	
	@Test
	public void testGetCollectionEnumeration() {
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		Enumeration<String> enumration = CollectionCode.getCollectionEnumeration(list);
		while (enumration.hasMoreElements()) {
			System.out.println(enumration.nextElement());
		}
	}
}
