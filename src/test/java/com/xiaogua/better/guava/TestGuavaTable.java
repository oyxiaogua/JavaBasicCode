package com.xiaogua.better.guava;

import org.junit.Test;

import com.google.common.collect.HashBasedTable;

public class TestGuavaTable {
	@Test
	public void testHashBasedTable() {
		HashBasedTable<Integer, Integer, String> t = HashBasedTable.create();
		t.put(100, 10, "test");
		t.put(100, 20, "test2");
		t.put(10, 20, "test3");
		// 包含
		System.out.println(t.contains(100, 10));
		// 包含列
		System.out.println(t.containsColumn(20));
		// 通过列取值
		System.out.println(t.column(20));
		// 包含行
		System.out.println(t.containsRow(50));
		// 通过行取值
		System.out.println(t.row(100));
		// 包含值
		System.out.println(t.containsValue("test3"));
		System.out.println(t.get(10, 20));
		System.out.println(t.remove(100, 10));
		System.out.println(t);
	}
}
