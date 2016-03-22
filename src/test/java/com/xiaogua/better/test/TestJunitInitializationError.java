package com.xiaogua.better.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestJunitInitializationError {
	private  String str;

	@BeforeClass
	// 只执行一次，且必须为static void
	//public static void beforeClass() {
	public  void beforeClass() {
		str = "test";
	}

	@Test
	public void testJunitInitializationError() {
		Assert.assertEquals("test", str);
	}
}
