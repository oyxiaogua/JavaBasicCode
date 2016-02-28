package com.xiaogua.better.reflect;

import java.lang.reflect.Method;

import org.junit.Test;

public class TestReflectStaticMethod {

	@Test
	public void testReflectStaticMethod() throws Exception {
		Class<?> classZ = Class.forName("com.xiaogua.better.bean.TestStaticMthClass");
		Method method = classZ.getMethod("getValueWithStaticMth", String[].class);
		String[] strArr = new String[] { "Cafe", "Baby" };
		String rtnStr = (String) method.invoke(null, new Object[] { strArr });
		System.out.println(rtnStr);
	}
}
