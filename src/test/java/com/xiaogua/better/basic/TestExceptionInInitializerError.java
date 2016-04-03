package com.xiaogua.better.basic;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaogua.better.bean.Init_Error_Class;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestExceptionInInitializerError {
	private static final Logger log = LoggerFactory.getLogger(TestExceptionInInitializerError.class);

	@Test(expected = NoClassDefFoundError.class)
	public void testExceptionInInitializerError() {
		try {
			Init_Error_Class.doSomething();
		} catch (Throwable e) {
			log.error("testExceptionInInitializerError", e);
		}
		Init_Error_Class.doSomething();
	}

	@Test(expected = NoClassDefFoundError.class)
	public void testNoClassDefFoundError() {
		Init_Error_Class.doSomething();
	}

}