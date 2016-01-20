package com.xiaogua.better.str;

import java.lang.reflect.Field;

import org.junit.Test;

public class TestStrSubString {
	@Test
	public void testNormalSubString() throws Exception {
		String s = "0123456789";
		String t = s.substring(1); // 注意看这里
		System.out.println("t.charAt(3)为" + t.charAt(3));
		Field f = s.getClass().getDeclaredField("value");
		f.setAccessible(true);
		f.set(s, new char[] { 'a', 'b', 'c' });
		System.out.println("t.charAt(3)为" + t.charAt(3));
	}

	@Test(expected = StringIndexOutOfBoundsException.class)
	public void testSubString() throws Exception {
		String s = "0123456789";
		String t = s.substring(0); // 注意看这里
		System.out.println("t.charAt(3)为" + t.charAt(3));
		Field f = s.getClass().getDeclaredField("value");
		f.setAccessible(true);
		f.set(s, new char[] { 'a', 'b', 'c' });
		System.out.println("t.charAt(3)为" + t.charAt(3));
	}
}
