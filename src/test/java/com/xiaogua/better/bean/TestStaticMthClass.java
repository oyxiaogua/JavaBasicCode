package com.xiaogua.better.bean;

import java.util.Arrays;

public class TestStaticMthClass {
	public static String getValueWithStaticMth(String[] strArr) {
		System.out.println(Arrays.toString(strArr));
		return "success";
	}
}
