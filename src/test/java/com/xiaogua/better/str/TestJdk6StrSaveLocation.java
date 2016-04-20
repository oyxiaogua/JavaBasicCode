package com.xiaogua.better.str;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestJdk6StrSaveLocation {

	@Test
	/**
	 * -XX:PermSize=10M -XX:MaxPermSize=10M 适合jdk1.6及以下版本
	 */
	public void testJdk6ConstantPoolPermGenException() {
		List<String> list = new ArrayList<String>();
		int i = 0;
		while (true) {
			list.add(Integer.toString(i++).intern());
		}
	}
}
