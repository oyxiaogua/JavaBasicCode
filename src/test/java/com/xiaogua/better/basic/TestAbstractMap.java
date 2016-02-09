package com.xiaogua.better.basic;

import java.util.AbstractMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class TestAbstractMap {
	@Test
	public void test_SimpleEntry_setValue() throws Exception {
		Map.Entry<Integer, String> entry = new AbstractMap.SimpleEntry<Integer, String>(1, "value_1");
		Assert.assertEquals("value_1", entry.getValue());
		entry.setValue("value_2");
		Assert.assertEquals("value_2", entry.getValue());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void test_SimpleImmutableEntry() throws Exception {
		//不可修改
		Map.Entry<Integer, String> entry = new AbstractMap.SimpleImmutableEntry<Integer, String>(1, "value_1");
		Assert.assertEquals("value_1", entry.getValue());
		entry.setValue("value_2");
	}
}
