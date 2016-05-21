package com.xiaogua.better.str;

import org.junit.Test;

public class TestSplitValueToSql {

	@Test
	public void testSplitValueToSql() {
		String value = "1,2,3,4";
		String sqlStr = SqlCode.splitValueToSql("name", value, String.class, "=", " or ");
		System.out.println(sqlStr);

		sqlStr = SqlCode.splitValueToSql("name", value, String.class, "!=", " and ");
		System.out.println(sqlStr);
	}
}
