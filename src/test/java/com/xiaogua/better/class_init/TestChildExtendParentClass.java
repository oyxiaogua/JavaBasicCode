package com.xiaogua.better.class_init;

import java.util.Arrays;

import org.junit.Test;

public class TestChildExtendParentClass {

	@Test
	public void testChildExtendParentClass() {
		Abs_Parent_Class clz = new Child_Extend_Abs_Parent_Class();
		clz.setField("test_value");
		System.out.println(clz.getField());
		clz.printName();
	}

	@Test
	public void testAnonymousClass() {
		final String printNameField = "AnonymousClass";
		// 使用数组绕过final不能修改的限制
		final boolean[] booleanArr = new boolean[] { false };
		new Abs_Parent_Class() {
			public void printName() {
				// Abs_Parent_Class,有访问权限(protected),private则访问本地final变量
				System.out.println(printNameField);
				if ("Abs_Parent_Class".equals(printNameField)) {
					booleanArr[0] = true;
				}
			}
		}.printName();
		System.out.println(Arrays.toString(booleanArr));
	}
}
