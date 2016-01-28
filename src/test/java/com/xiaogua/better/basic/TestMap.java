package com.xiaogua.better.basic;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

public class TestMap {
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
}
