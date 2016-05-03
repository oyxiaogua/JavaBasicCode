package com.xiaogua.better.test;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class MyRegexMatcher extends TypeSafeMatcher<String> {

	private final String regex;

	public MyRegexMatcher(final String regex) {
		this.regex = regex;
	}

	public void describeTo(final Description description) {
		description.appendText("matches regular expression=`" + regex + "`");
	}

	public boolean matchesSafely(final String string) {
		return string.matches(regex);
	}

	public static MyRegexMatcher matchesRegex(final String regex) {
		return new MyRegexMatcher(regex);
	}
}