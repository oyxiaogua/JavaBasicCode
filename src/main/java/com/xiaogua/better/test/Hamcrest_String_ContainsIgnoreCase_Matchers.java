package com.xiaogua.better.test;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public final class Hamcrest_String_ContainsIgnoreCase_Matchers {

	private Hamcrest_String_ContainsIgnoreCase_Matchers() {
	}

	public static Matcher<String> containsIgnoreCase(final String substring) {
		return new TypeSafeDiagnosingMatcher<String>() {

			protected boolean matchesSafely(String item, Description mismatchDescription) {
				mismatchDescription.appendValue(item).appendText(" did not contain ignoring case: ")
						.appendValue(substring);
				return StringUtils.containsIgnoreCase(item, substring);
			}

			public void describeTo(Description description) {
				description.appendText("contains ignoring case ").appendValue(substring);
			}
		};
	}
}
