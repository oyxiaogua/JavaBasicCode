package com.xiaogua.better.basic;

import java.util.Map;
import java.util.TreeMap;

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
		//提供一个能容忍null值的排序方法
		TreeMap<String, Object> treeMap = new TreeMap<String, Object>(MyCaseInsensitiveComparator.INSTANCE);
		treeMap.put(null, "test_1");
		Assert.assertEquals("test_1", treeMap.get(null));
	}

}
