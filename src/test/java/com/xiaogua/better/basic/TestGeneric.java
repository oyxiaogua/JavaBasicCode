package com.xiaogua.better.basic;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TestGeneric {

	@Test
	public void testGenericErase() throws Exception {
		List<Integer> list = new ArrayList<Integer>();
		Method method = list.getClass().getMethod("add", Object.class);
		method.invoke(list, "abc");
		Assert.assertEquals("abc", list.get(0));
	}
}
