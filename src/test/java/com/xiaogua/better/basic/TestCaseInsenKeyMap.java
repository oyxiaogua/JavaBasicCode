package com.xiaogua.better.basic;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.junit.Assert;
import org.junit.Test;

public class TestCaseInsenKeyMap {
	@Test
	public void testMapCaseInsenKeyWithTreeMap() {
		Map<String, Integer> treeMap = new TreeMap<String, Integer>(MyCaseInsensitiveComparator.INSTANCE);
		treeMap.put("keY_caSe_In", 1);
		treeMap.put("KeY_2", 2);
		treeMap.put(null, null);
		System.out.println(treeMap);
		System.out.println(treeMap.get("key_2"));
		System.out.println(treeMap.get(null));
		Assert.assertNotNull(treeMap.get("key_case_in"));
	}

	@Test
	public void testMapCaseInsenKeyWithTreeMap2() {
		Map<String, Integer> treeMap = new TreeMap<String, Integer>(
				Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER));
		treeMap.put("keY_caSe_In", 1);
		treeMap.put("KeY_2", 2);
		treeMap.put(null, null);
		System.out.println(treeMap);
		System.out.println(treeMap.get("key_2"));
		System.out.println(treeMap.get(null));
		Assert.assertNotNull(treeMap.get("key_case_in"));
	}

	@Test
	public void testMapCaseInsenKeyWithApacheCaseInsensitiveMap() {
		Map<String, Integer> treeMap = new CaseInsensitiveMap<String, Integer>();
		treeMap.put("keY_caSe_In", 1);
		treeMap.put("KeY_2", 2);
		treeMap.put(null, 3);
		treeMap.put("kEY_4", null);
		System.out.println(treeMap);
		System.out.println(treeMap.get("key_2"));
		System.out.println(treeMap.get(null));
		Assert.assertNotNull(treeMap.get("key_case_in"));
	}
}
