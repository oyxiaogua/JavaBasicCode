package com.xiaogua.better.class_init;

import org.junit.Test;

public class TestNormalClassInit {

	@Test
	public void testNormalClassInit() {
		new NormalClassInit();
	}

	@Test
	public void testClassForNameInitClassStaticMember() throws Exception {
		Class.forName("com.xiaogua.better.class_init.NormalClassInit", true,
				TestNormalClassInit.class.getClassLoader());
	}
}
