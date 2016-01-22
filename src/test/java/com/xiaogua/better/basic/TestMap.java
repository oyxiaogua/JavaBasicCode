package com.xiaogua.better.basic;

import java.util.Map;

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
}
