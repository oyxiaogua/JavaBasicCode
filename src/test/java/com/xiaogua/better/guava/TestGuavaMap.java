package com.xiaogua.better.guava;

import org.junit.Test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;

public class TestGuavaMap {
	@Test
	public void testArrayListMultimap() {
		ArrayListMultimap<String, String> m = ArrayListMultimap.create();
		m.put("test", "1");
		m.put("test", "1");
		m.put("test", "2");
		m.put("test2", "3");
		System.out.println(m.get("test"));
		System.out.println(m.get("test2"));
	}

	@Test
	public void testHashMultimap() {
		HashMultimap<String, String> m = HashMultimap.create();
		m.put("test", "1");
		m.put("test", "1");
		m.put("test", "2");
		m.put("test2", "3");
		System.out.println(m.get("test"));// 1,2
		System.out.println(m.get("test2"));
	}

	@Test
	public void testBiMap() {
		BiMap<String, String> m = HashBiMap.create();
		m.put("test", "1");
		m.forcePut("test2", "1");
		m.put(null, null);
		System.out.println(m.get("test"));// null
		System.out.println(m.get("test2"));
		System.out.println(m.inverse().get("1"));
		System.out.println(m.containsKey(null));
	}

}
