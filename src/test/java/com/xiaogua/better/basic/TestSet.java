package com.xiaogua.better.basic;

import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;

public class TestSet {
	@Test(expected = NullPointerException.class)
	public void testTreeSetAddNullValue() {
		TreeSet<String> treeSet = new TreeSet<String>();
		treeSet.add(null);
	}

	@Test
	public void testTreeSetAddNullValueNormal() {
		TreeSet<String> treeSet = new TreeSet<String>(MyCaseInsensitiveComparator.INSTANCE);
		treeSet.add(null);
		Assert.assertEquals(1, treeSet.size());
	}
}
