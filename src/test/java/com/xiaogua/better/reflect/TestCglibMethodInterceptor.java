package com.xiaogua.better.reflect;

import org.junit.Test;

import com.xiaogua.better.bean.Abs_ExecuteManager;
import com.xiaogua.better.bean.CglibLookupOverrideMethodInterceptor;

import net.sf.cglib.proxy.Enhancer;

public class TestCglibMethodInterceptor {

	@Test
	public void testCglibMethodInterceptor() {
		Enhancer en = new Enhancer();
		en.setSuperclass(Abs_ExecuteManager.class);
		en.setCallback(new CglibLookupOverrideMethodInterceptor());
		Abs_ExecuteManager cm = (Abs_ExecuteManager) en.create();
		Object rtnObj = null;
		for (int i = 0; i < 3; i++) {
			rtnObj = cm.process();
			System.out.println(rtnObj);
		}
		System.out.println("----------------");
		for (int i = 0; i < 3; i++) {
			rtnObj = cm.process("sit down");
			System.out.println(rtnObj);
		}
	}
}
