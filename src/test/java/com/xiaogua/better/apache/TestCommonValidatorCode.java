package com.xiaogua.better.apache;

import org.junit.Assert;
import org.junit.Test;

public class TestCommonValidatorCode {
	@Test
	public void testValidateLuhn() {
		String str = "5432123456788881";
		boolean rtn = CommonValidatorCode.validateLuhn(str);
		Assert.assertTrue(rtn);
		str = "6013821400056772";
		String lastNum = CommonValidatorCode.calculateLuhn(str);
		Assert.assertEquals("3", lastNum);
	}
}
