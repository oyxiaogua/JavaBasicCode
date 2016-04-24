package com.xiaogua.better.algorithm;

import org.junit.Test;

public class TestBinaryCode {

	@Test
	public void testBinaryAdd() {
		String str = "01111";
		String str2 = "1111";
		String rtnStr = BinaryCode.addBinary(str, str2);
		System.out.println(rtnStr);
	}
}
