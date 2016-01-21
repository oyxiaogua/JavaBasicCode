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
}
