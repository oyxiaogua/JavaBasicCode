package com.xiaogua.better.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

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
}
