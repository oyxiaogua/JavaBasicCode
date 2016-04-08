package com.xiaogua.better.str;

import org.junit.Assert;
import org.junit.Test;

public class TestStringCommonUtils {
	@Test
	public void testLeftPadding() {
		String str = "A B";
		String padStr = StringCommonUtils.leftPad(str, 5, '0');
		Assert.assertEquals("00A B", padStr);
	}

	@Test
	public void testRightPadding() {
		String str = "A B";
		String padStr = StringCommonUtils.rightPad(str, 5, '0');
		Assert.assertEquals("A B00", padStr);
	}
}
