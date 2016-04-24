package com.xiaogua.better.str;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class TestStrRegex {

	@Test
	public void testStrRegexFindAlphanumeric() {
		String str = "b2Ca3Ac";
		String regexStr = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{3,3}";
		printMatcherInfo(str, regexStr);
	}

	@Test
	public void testStrRegexFindHexColor() {
		String str = "#aabbcc,#123#45ab";
		String regexStr = "#([a-fA-F]|[0-9]){3,6}";
		printMatcherInfo(str, regexStr);
	}

	private void printMatcherInfo(String str, String regexStr) {
		Pattern p = Pattern.compile(regexStr);
		Matcher m = p.matcher(str);
		while (m.find()) {
			System.out.println(String.format("group=%s,start=%s,end=%s", m.group(), m.start(), m.end()));
		}
	}
}
