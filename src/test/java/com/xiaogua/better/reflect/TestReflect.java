package com.xiaogua.better.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.xiaogua.better.bean.Private_Set_Bean;

public class TestReflect {
	@Test
	public void testPrintStringMethod() {
		List<Method> results = new ArrayList<Method>();
		for (Method m : String.class.getDeclaredMethods()) {
			if (Modifier.isPublic(m.getModifiers()) && m.getReturnType().equals(String.class)
					&& m.getParameterCount() == 0 && m.getName().startsWith("to")) {
				results.add(m);
			}
		}
		for (Method method : results) {
			System.out.println(method.getName());
		}
	}

	@Test
	public void testSetField() {
		Private_Set_Bean bean = new Private_Set_Bean();
		ReflectCode.setField(bean, "name", "testName");
		Assert.assertEquals("testName", bean.getName());
	}
}
