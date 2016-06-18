package com.xiaogua.better.apache;

import java.util.List;

import org.apache.commons.lang3.ClassUtils;
import org.junit.Assert;
import org.junit.Test;

import com.xiaogua.better.class_init.Sub_Class_B;
import com.xiaogua.better.class_init.Super_Class_A;

public class TestClassUtils {

	@Test
	public void testClassUtilsIsAssignableFrom() {
		boolean rtn = ClassUtils.isAssignable(Sub_Class_B.class, Super_Class_A.class);
		Assert.assertTrue(rtn);
	}

	@Test
	public void testClassUtilsGgetAllInterfaces() {
		List<Class<?>> rtnList = ClassUtils.getAllInterfaces(Test.class);
		System.out.println(rtnList);
	}

}
