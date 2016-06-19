package com.xiaogua.better.basic;

import org.junit.Assert;
import org.junit.Test;

import com.xiaogua.better.bean.StudentBean;

public class TestBasicCode {
	@Test
	public void testObjectToString() {
		StudentBean bean = new StudentBean();
		String toString = bean.toString();
		String hashCode = Integer.toHexString(bean.hashCode());
		Assert.assertTrue(toString.endsWith(hashCode));
	}

	@Test
	public void testForLoop() {
		int i = 0;
		for (checkCdt("A"); checkCdt("B") && i < 2; checkCdt("C")) {
			checkCdt("D");
			i++;
		}
	}

	private boolean checkCdt(String name) {
		System.out.print(name);
		return true;
	}

}
