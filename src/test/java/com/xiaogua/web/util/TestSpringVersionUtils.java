package com.xiaogua.web.util;

import org.junit.Assert;
import org.junit.Test;

public class TestSpringVersionUtils {

	@Test
	public void testSpringVersion() {
		boolean isSpring30 = SpringVersionUtils.isSpring30AtLeast();
		Assert.assertTrue(isSpring30);

		boolean isSpring31 = SpringVersionUtils.isSpring31AtLeast();
		Assert.assertTrue(isSpring31);

		boolean isSpring32 = SpringVersionUtils.isSpring32AtLeast();
		Assert.assertTrue(isSpring32);

		boolean isSpring4 = SpringVersionUtils.isSpring40AtLeast();
		Assert.assertTrue(isSpring4);
	}
}
