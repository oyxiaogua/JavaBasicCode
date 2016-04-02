package com.xiaogua.better.basic;

import org.junit.Assert;
import org.junit.Test;

public class TestCharacterCode {
	@Test
	public void testCharacterForDigit() {
		int radix = 10;
		char charR = Character.forDigit(9, radix);
		Assert.assertEquals('9', charR);
		int intR = Character.digit(charR, radix);
		Assert.assertEquals(9, intR);

		char oneChar = '१';
		intR = Character.digit(oneChar, radix);
		Assert.assertEquals(1, intR);
	}
	
	
}
