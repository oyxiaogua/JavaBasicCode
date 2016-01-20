package com.xiaogua.better.str;

import org.junit.Assert;
import org.junit.Test;

public class TestStrCode {
	@Test
	public void testConvertNumToExcelStr() {
		String rtnStr = StrCode.convertNumToExcelStr(731);
		Assert.assertEquals("ABC", rtnStr);
	}

	@Test
	public void testConvertExcelStrToNumber() throws Exception {
		int intRtn = StrCode.convertExcelStrToNumber("ABC");
		Assert.assertEquals(731, intRtn);
	}

	@Test
	public void testIsHasChinese() {
		String str = "。";
		Assert.assertTrue(StrCode.isHasChinese(str));
	}

	@Test
	public void testIsJavaIdentifier() {
		String str = "java";
		Assert.assertTrue(StrCode.isJavaIdentifier(str));
	}

}
