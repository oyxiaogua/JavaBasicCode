package com.xiaogua.better.basic;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

public class TestMap {
	@Test
	public void testMapCompute() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key_1", "value_1");
		String key = "key_1";
		map.compute(key, (existingKey, existingValue) -> {
			return existingKey + "," + existingValue;
		});
		System.out.println(map);
	}

	@Test
	public void testConvertStringArrayToMap() {
		String[][] strArr = { { "key_1", "value_2" }, { null, "null_1" }, { "key_2", null }, { "key_3", "" },
				{ null, null } };
		Map<Object, Object> map = ArrayUtils.toMap(strArr);
		Assert.assertEquals("value_2", map.get("key_1"));
		Assert.assertEquals(null, map.get(null));
		Assert.assertTrue(map.containsKey(null));
		Assert.assertFalse(map.containsKey("key_4"));
	}

	@Test
	public void testTreeMap() {
		TreeMap<String, Object> treeMap = new TreeMap<String, Object>();
		treeMap.put("1", "a");
		treeMap.put("2", "b");
		treeMap.put("3", "c");
		treeMap.put("4", "d");
		// 返回大于或等于给定的键，或null，如果不存在这样的键
		Assert.assertEquals("2", treeMap.ceilingKey("1.5"));
		Assert.assertNull(treeMap.ceilingKey("4.0"));
	}

	@Test(expected = NullPointerException.class)
	public void testTreeMapError() {
		TreeMap<String, Object> treeMap = new TreeMap<String, Object>();
		treeMap.put(null, "test_1");
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testSingletonMap() {
		// 不可修改
		Map<String, String> map = Collections.singletonMap("key_1", "value_1");
		map.put("key_2", "value_2");
	}

	@Test
	public void testTreeMapNormal() {
		// 提供一个能容忍null值的排序方法
		TreeMap<String, Object> treeMap = new TreeMap<String, Object>(MyCaseInsensitiveComparator.INSTANCE);
		treeMap.put(null, "test_1");
		Assert.assertEquals("test_1", treeMap.get(null));
	}

	@Test
	public void testIdentityHashMap() {
		// 在 IdentityHashMap 中，当且仅当 (k1==k2) 时，才认为两个键 k1 和 k2 相等
		IdentityHashMap<String, Object> map = new IdentityHashMap<String, Object>();
		String fsString = new String("xx");
		String secString = new String("xx");
		map.put(fsString, "first");
		map.put(secString, "second");
		for (Entry<String, Object> entry : map.entrySet()) {
			System.out.print(entry.getKey() + "    ");
			System.out.println(entry.getValue());
		}
		System.out.println("idenMap=" + map.containsKey(fsString));
		System.out.println("idenMap=" + map.get(fsString));

		System.out.println("idenMap=" + map.containsKey(secString));
		System.out.println("idenMap=" + map.get(secString));
	}

	@Test
	public void testConvertPropertiesToMap() {
		Properties properties = new Properties();
		properties.put("database.username", "test");
		properties.put("database.password", "1234");
		properties.put("database.driver", "com.mysql.jdbc.Driver");
		properties.put("database.url", "jdbc:mysql://localhost:3306/test");
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<?> propertyNames = properties.propertyNames();
		String key = null;
		while (propertyNames.hasMoreElements()) {
			key = (String) propertyNames.nextElement();
			map.put(key, properties.getProperty(key));
		}
		Assert.assertTrue(map.size() == 4);
		Assert.assertEquals("test", map.get("database.username"));
	}

	@Test
	public void testConvertPropertiesToMapWithJava8() {
		Properties properties = new Properties();
		properties.put("database.username", "test");
		properties.put("database.password", "1234");
		properties.put("database.driver", "com.mysql.jdbc.Driver");
		properties.put("database.url", "jdbc:mysql://localhost:3306/test");
		Stream<Entry<Object, Object>> stream = properties.entrySet().stream();
		Map<String, String> map = stream
				.collect(Collectors.toMap(e -> String.valueOf(e.getKey()), e -> String.valueOf(e.getValue())));
		Assert.assertTrue(map.size() == 4);
		Assert.assertEquals("test", map.get("database.username"));
	}

	@Test
	public void testMapEquals() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key_1", "value_1");
		map.put(null, "");

		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("key_1", "value_1");
		map2.put(null, null);
		boolean isEqual = mapEquals(map, map2);
		Assert.assertFalse(isEqual);
	}

	@Test
	public void testJava8MapMerge() {
		Set<String> set = new HashSet<String>();
		set.add("test_1");
		set.add("test_2");
		set.add("test_3");

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("test_1", 1);
		map.put("test_4", 0);

		// set key merge to map
		set.stream().forEach(match -> map.merge(match, 0, Integer::sum));
		System.out.println(map);
	}

	@Test
	public void testSortedMapTailMap() {
		SortedMap<Integer, String> sortedMap = new TreeMap<Integer, String>();
		for (int i = 10; i >0; i--) {
			sortedMap.put(i, "test_" + i);
		}
		SortedMap<Integer, String> subMap = sortedMap.tailMap(5);
		Integer i = subMap.firstKey();
		System.out.println(i + "," + subMap.get(i));
		System.out.println(subMap);
	}
	
	
	@Test
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void testIteratorHashMap(){
		//hashmap遍历
		HashMap map=new HashMap();
		map.put("test_key_1", "test_value_1");
		map.put("test_key_2", "test_value_2");
		map.put("test_key_3", "test_value_3");
		map.put("test_key_4", "test_value_4");
		Set<Map.Entry> entrySet = map.entrySet();
		for (Entry entry : entrySet) {
			System.out.println(entry.getKey()+","+entry.getValue());
		}
	}

	public static boolean mapEquals(Map<?, ?> m1, Map<?, ?> m2) {
		if (m1.size() != m2.size()) {
			return false;
		}

		for (Map.Entry<?, ?> e : m1.entrySet()) {
			Object o = m2.get(e.getKey());
			if (e.getValue() == null && o != null) {
				return false;
			}
			if (o == null && e.getValue() != null) {
				return false;
			}
			if (o != null && !e.getValue().equals(o)) {
				return false;
			}
		}
		return true;
	}
}
