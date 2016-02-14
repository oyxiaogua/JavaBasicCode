package com.xiaogua.better.basic;

import org.junit.Test;

public class TestMath {
	@Test
	public void testMathToRadians() {
		double value = 90;
		// 角度转弧度 不精确
		double rtnVal = Math.toRadians(value);
		System.out.println(rtnVal);

		double rtnVal2 = Math.toDegrees(rtnVal);
		System.out.println(rtnVal2);
	}
	
	@Test
	public void testMathNextDown() {
		System.out.println(Math.nextDown(100));
		System.out.println(Math.nextDown(100.365));
	}
}
