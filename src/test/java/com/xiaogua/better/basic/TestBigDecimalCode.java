package com.xiaogua.better.basic;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class TestBigDecimalCode {
	@Test
	public void testGetWithoutTrailingZeroes() {
		//清除结尾多余的0
		BigDecimal decimal = new BigDecimal("1.233300e3");
		System.out.println(decimal.toString());
		BigDecimal rtnDecimal = BigDecimalCode.getWithoutTrailingZeroes(decimal);
		Assert.assertEquals("1233.3", rtnDecimal.toString());
		
		decimal = new BigDecimal("6e2");
		rtnDecimal = BigDecimalCode.getWithoutTrailingZeroes(decimal);
		Assert.assertEquals("600", rtnDecimal.toString());
	}
}
