package com.xiaogua.better.reflect;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TestGetClassCode {
	@Test
	public void testGetClass() throws Exception {
		Class<?> clz = this.getClass();
		System.out.println(clz);// TestGetClassCode
		Object obj = this.getClass().newInstance();
		if (obj instanceof Object) {
			System.out.println("obj instanceof Object");
		}
		TestGetClassCode thisClz = this.getClass().newInstance();
		System.out.println(thisClz);
	}

	@Test
	public void testGetClassName() {
		List<String> list = new ArrayList<String>();
		System.out.println(list.getClass());
		Assert.assertEquals("java.util.ArrayList", list.getClass().getName());
	}

}
