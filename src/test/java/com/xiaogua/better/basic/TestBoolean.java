package com.xiaogua.better.basic;

import org.junit.Assert;
import org.junit.Test;

public class TestBoolean {
	@Test
	public void testBoolean() {
		/// 当且仅当以参数命名的系统属性存在，且等于 "true" 字符串时，才返回 true
		Assert.assertFalse(Boolean.getBoolean("true"));
		boolean trueBoolean = Boolean.valueOf("true").booleanValue();
		Assert.assertTrue(trueBoolean);
		System.setProperty("true", "true");
		Assert.assertTrue(Boolean.getBoolean("true"));
	}

	@Test
	public  void booleanInitialize() {
		System.out.println("String 0 : " + new Boolean("0"));//false
		System.out.println("String 1 : " + new Boolean("1"));//false
		System.out.println("Null : " + new Boolean(null));//false
		System.out.println("String true : " + new Boolean("true"));//true
		System.out.println("String false : " + new Boolean("false"));//false
		System.out.println("true : " + new Boolean(true));//true
		System.out.println("false : " + new Boolean(false));//false
	}
	
}
