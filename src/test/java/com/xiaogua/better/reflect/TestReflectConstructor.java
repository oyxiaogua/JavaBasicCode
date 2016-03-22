package com.xiaogua.better.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.junit.Test;

import com.xiaogua.better.bean.PrivateConstructorBean;

public class TestReflectConstructor {

	@Test
	public void testReflectPublicConstructor() throws Exception {
		Class<?> classZ = Class.forName("com.xiaogua.better.bean.PrivateConstructorBean");
		Class<?>[] parameterTypes = { int.class, String.class, Double.class };
		// only public constructors
		Constructor<?> constructor = classZ.getConstructor(parameterTypes);
		Object[] parameterArr = { 1, "test_1", 300d };
		PrivateConstructorBean bean = (PrivateConstructorBean) constructor.newInstance(parameterArr);
		Method method = classZ.getMethod("getPubInfo");
		String rtnStr = (String) method.invoke(bean, new Object[] {});
		System.out.println(rtnStr);
	}

	@Test
	public void testReflectPrivateConstructor() throws Exception {
		Class<?> classZ = Class.forName("com.xiaogua.better.bean.PrivateConstructorBean");
		Class<?>[] parameterTypes = { int.class, String.class, Double.class, String.class };
		// all the constructors
		Constructor<?> constructor = classZ.getDeclaredConstructor(parameterTypes);
		constructor.setAccessible(true);
		Object[] parameterArr = { 1, "test_1", 300d, "test_2" };
		PrivateConstructorBean bean = (PrivateConstructorBean) constructor.newInstance(parameterArr);
		Method method = classZ.getDeclaredMethod("getPrivateInfo", new Class[] { int.class });
		method.setAccessible(true);
		String rtnStr = (String) method.invoke(bean, new Object[] { 123 });
		System.out.println(rtnStr);
	}

}
