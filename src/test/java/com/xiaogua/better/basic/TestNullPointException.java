package com.xiaogua.better.basic;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import org.junit.Assert;

public class TestNullPointException {
	private static Integer count;

	@Test(expected = NullPointerException.class)
	public void testUnBoxNullPointException() {
		// 第二、第三位操作数分别是基本类型和对象。所以对对象进行拆箱操作，由于该对象为null，所以在拆箱过程中调用null.booleanValue()的时候就报了NPE
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		boolean b = (map != null ? map.get("test") : false);
		Assert.fail("fail" + b);
	}

	@Test
	public void testTernaryOperatoRightUsage() {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		Boolean b2 = (map != null ? map.get("test") : Boolean.FALSE);
		Assert.assertNull(b2);
	}

	@Test(expected = NullPointerException.class)
	public void testUnBoxNullPointException2() {
		// 一个原始数据值与一个对象进行比较时，
		// 如果这个对象没有进行初始化或者为Null，在自动拆箱过程中obj.xxxValue，会抛出NullPointerException
		if (count <= 0) {
			System.out.println("Count is not started yet");
		}
	}
}
