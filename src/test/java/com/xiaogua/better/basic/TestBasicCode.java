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
}
