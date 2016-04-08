package com.xiaogua.better.str;

import org.junit.Assert;
import org.junit.Test;

public class TestStringCommonUtils {
	@Test
	public void testLeftPadding() {
		String str = "A";
		String padStr = StringCommonUtils.leftPad(str, 5, '0');
		Assert.assertEquals("0000A", padStr);
	}

	@Test
	public void testRightPadding() {
		String str = "A";
		String padStr = StringCommonUtils.rightPad(str, 5, '0');
		Assert.assertEquals("A0000", padStr);
	}
}
