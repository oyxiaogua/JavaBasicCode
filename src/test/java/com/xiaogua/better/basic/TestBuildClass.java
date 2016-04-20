package com.xiaogua.better.basic;

import org.junit.Test;

import com.xiaogua.better.bean.BuildClass;

public class TestBuildClass {

	@Test
	public void testBuildClass() {
		BuildClass buildClass = new BuildClass.Builder().setId(1).setName("name").setAge(22).setSex("man")
				.setAddress("address").build();
		System.out.println(buildClass);
	}
}
