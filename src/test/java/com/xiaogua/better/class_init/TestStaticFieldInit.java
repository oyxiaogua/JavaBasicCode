package com.xiaogua.better.class_init;

import org.junit.Assert;
import org.junit.Test;

public class TestStaticFieldInit {
	public static Ref_Static_Field_Class t = new Ref_Static_Field_Class();
	public static int a = 0;
	public static int b;

	@Test
	public void testStaticFieldInit() {
		// 静态变量声明(默认值)-->设置初始值
		Assert.assertEquals(0, TestStaticFieldInit.a);
		Assert.assertEquals(1, TestStaticFieldInit.b);
	}
}

class Ref_Static_Field_Class {
	public Ref_Static_Field_Class() {
		TestStaticFieldInit.a++;
		TestStaticFieldInit.b++;
	}
}