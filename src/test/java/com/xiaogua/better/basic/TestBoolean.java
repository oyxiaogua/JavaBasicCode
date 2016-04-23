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
	public void testBooleanInitialize() {
		System.out.println("String 0 : " + new Boolean("0"));// false
		System.out.println("String 1 : " + new Boolean("1"));// false
		System.out.println("Null : " + new Boolean(null));// false
		System.out.println("String true : " + new Boolean("true"));// true
		System.out.println("String false : " + new Boolean("false"));// false
		System.out.println("true : " + new Boolean(true));// true
		System.out.println("false : " + new Boolean(false));// false
	}

	@Test
	public void testBooleanShortCircuit() {
		// A && B
		System.out.println("***** test A && B *****");
		System.out.println(getBooleanValue(true, 1) && getBooleanValue(true, 2));// true
		System.out.println(getBooleanValue(false, 3) && getBooleanValue(true, 4));// false

		// A || B
		System.out.println("***** test A || B *****");
		System.out.println(getBooleanValue(true, 5) || getBooleanValue(true, 6));// true
		System.out.println(getBooleanValue(false, 7) || getBooleanValue(true, 8));// true

		// A && B || C && D -->(A && B)||(C && D)
		System.out.println(getBooleanValue(true, 9) && getBooleanValue(true, 10)
				|| getBooleanValue(true, 11) && getBooleanValue(false, 12));// true
		System.out.println(getBooleanValue(false, 13) && getBooleanValue(true, 14)
				|| getBooleanValue(true, 15) && getBooleanValue(false, 16));// false

		Assert.assertFalse(true ? false : true ? false : true);
		Assert.assertFalse(true ? false : (true ? false : true));
	}

	public boolean getBooleanValue(boolean a, int id) {
		if (a) {
			System.out.println("execute true,id=" + id);
		}
		return a;
	}
}
