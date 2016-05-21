package com.xiaogua.better.str;

public class SqlCode {
	public static String splitValueToSql(String name, String value, Class<?> clazz, String operatorStr,
			String logicOperator) {
		String fh = "";
		if (clazz == String.class) {
			fh = "'";
		}
		String arr[] = value.split(",");
		int length = arr.length;
		StringBuilder sqlSb = new StringBuilder("( ");
		for (int i = 0; i < length - 1; i++) {
			String did = arr[i];
			sqlSb.append(name).append(operatorStr).append(fh).append(did).append(fh).append(logicOperator);
		}
		sqlSb.append(name).append(operatorStr).append(fh).append(arr[length - 1]).append(fh);
		sqlSb.append(" )");
		return sqlSb.toString();
	}
}
