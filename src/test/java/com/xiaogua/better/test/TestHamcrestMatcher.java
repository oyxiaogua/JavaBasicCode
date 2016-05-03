package com.xiaogua.better.test;

import org.junit.Assert;
import org.junit.Test;

public class TestHamcrestMatcher {

	@Test
	public void testJavaHamcrestMatchesPattern() {
		// 使用java-hamcrest 可以使用正则匹配(regex)
		// Assert.assertThat("FooBarBaz", Matchers.matchesPattern("^Foo"));
	}

	@Test
	public void testMyHamcrestMatchesPattern() {
		String str = "abbc";
		Assert.assertThat(str, MyRegexMatcher.matchesRegex("a*b*c*"));
	}
}
