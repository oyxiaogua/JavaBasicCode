package com.xiaogua.better.reflect;

import org.junit.Assert;
import org.junit.Test;

import com.xiaogua.better.bean.Abs_Outer_Public_Inner_Class;

public class Test_Instance_Public_Inner_Class extends Abs_Outer_Public_Inner_Class {

	@Test
	public void testInstancePublicInnerClass() {
		Abs_Outer_Public_Inner_Class.Inner_Public_Class innerClass = new Inner_Public_Class();
		String nameStr = innerClass.getClassName();
		Assert.assertEquals("Test_Instance_Public_Inner_Class$Inner_Public_Class", nameStr);
	}
}
