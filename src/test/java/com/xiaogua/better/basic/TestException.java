package com.xiaogua.better.basic;

import org.junit.Test;

public class TestException {
	@Test
	@SuppressWarnings("finally")
	public void testLostException() {
		// 此处不会有异常抛出
		try {
			throw new RuntimeException();
		} finally {
			return;
		}
	}
	

}
