package com.xiaogua.better.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TestList {
	@Test
	public void tesCollectionstSynchronizedList() {
		final List<String> list = new ArrayList<String>(Arrays.asList("1", "2", "3"));
		final List<String> synchronizedList = Collections.synchronizedList(list);
		Assert.assertTrue(synchronizedList.size() == 3);
		list.remove(0);
		// 原始数据改变,synchronizedList跟着改变
		Assert.assertTrue(synchronizedList.size() == 2);
	}
}
