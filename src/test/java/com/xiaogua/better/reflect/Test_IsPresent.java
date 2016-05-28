package com.xiaogua.better.reflect;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.ClassUtils;

public class Test_IsPresent {

	@Test
	public void testSpringUtilClassUtils() {
		boolean isExistMysqlDriver = ClassUtils.isPresent("com.mysql.jdbc.Driver",
				Test_IsPresent.class.getClassLoader());
		Assert.assertTrue(isExistMysqlDriver);
	}
}
