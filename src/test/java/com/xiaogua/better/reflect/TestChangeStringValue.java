package com.xiaogua.better.reflect;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Test;

public class TestChangeStringValue {
	@Test
	public void testChangeStringValue() throws Exception {
		String s = "Hello World";

		Field valueFieldOfString = String.class.getDeclaredField("value");
		// 改变value属性的访问权限
		valueFieldOfString.setAccessible(true);
		// 获取s对象上的value属性的值
		char[] value = (char[]) valueFieldOfString.get(s);
		// 改变value所引用的数组中的第5个字符
		value[5] = '_';
		Assert.assertEquals("Hello_World", s);
	}
}
