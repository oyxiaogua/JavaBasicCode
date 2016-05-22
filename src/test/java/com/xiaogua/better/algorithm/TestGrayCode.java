package com.xiaogua.better.algorithm;

import java.util.Arrays;

import org.junit.Test;

public class TestGrayCode {

	@Test
	public void testGenerateGrayCode() {
		String[] grayCodeArr = GrayCode.generateGrayCode(4);
		System.out.println(Arrays.toString(grayCodeArr));
	}
}
