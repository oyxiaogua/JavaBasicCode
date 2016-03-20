package com.xiaogua.better.basic;

import org.junit.Assert;
import org.junit.Test;

import com.xiaogua.better.bean.NormalClass;
import com.xiaogua.better.bean.SubDateClass;

public class TestSubDateClass {

	@Test
	public void testGetSubDateClassName() {
		SubDateClass subDate = new SubDateClass();
		Assert.assertEquals("com.xiaogua.better.bean.SubDateClass", subDate.getSuperClassName());
		Assert.assertEquals("com.xiaogua.better.bean.SubDateClass", subDate.getThisClassName());
		Assert.assertEquals("java.util.Date", subDate.getRealSuperClassName());
		Assert.assertEquals("java.util.Date", subDate.getRealSuperClassName2());
	}

	@Test
	public void testGetNormalClassName() {
		NormalClass normalClass = new NormalClass();
		Assert.assertEquals("com.xiaogua.better.bean.NormalClass", normalClass.getSuperClassName());
		Assert.assertEquals("com.xiaogua.better.bean.NormalClass", normalClass.getThisClassName());
		Assert.assertEquals("java.lang.Object", normalClass.getRealSuperClassName());
		Assert.assertEquals("java.lang.Object", normalClass.getRealSuperClassName2());

	}
}