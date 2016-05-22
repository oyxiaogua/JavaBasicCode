package com.xiaogua.better.basic;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class TestSetCode {

	@Test
	public void testHashSetAddAll() {
		Set<String> set = new HashSet<String>(0);
		Set<String> set2 = new HashSet<String>();
		set2.add("test_1");
		set2.add("test_2");
		set.addAll(set2);
		System.out.println(set);
	}
}
