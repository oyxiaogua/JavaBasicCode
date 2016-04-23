package com.xiaogua.better.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.junit.Test;

import com.xiaogua.better.basic.SerializeCode;
import com.xiaogua.better.bean.StaticInnerClassSingleton;

public class TestSingleton {

	@Test
	public void testStaticInnerClassSingletonWithReflect() throws Exception {
		StaticInnerClassSingleton singleton = StaticInnerClassSingleton.getInstance();
		String classPathStr = "com.xiaogua.better.bean.StaticInnerClassSingleton";
		Class<?> clz = Class.forName(classPathStr);
		Constructor<?> constructor = clz.getDeclaredConstructor(StaticInnerClassSingleton.class);
		constructor.setAccessible(true);
		StaticInnerClassSingleton singleton2 = (StaticInnerClassSingleton) constructor.newInstance(singleton);
		System.out.println(singleton);
		System.out.println(singleton2);

		Method method = clz.getMethod("getInstance");
		StaticInnerClassSingleton singleton3 = (StaticInnerClassSingleton) method.invoke(null);
		System.out.println(singleton3);
	}

	@Test
	public void testStaticInnerClassSingletonWithSerialize() throws Exception {
		StaticInnerClassSingleton singleton = StaticInnerClassSingleton.getInstance();
		byte[] byteArr = SerializeCode.convertObjToByteArr(singleton);
		StaticInnerClassSingleton singleton2 = (StaticInnerClassSingleton) SerializeCode.convertByteArrToObj(byteArr);
		System.out.println(singleton);
		System.out.println(singleton2);
	}
}
