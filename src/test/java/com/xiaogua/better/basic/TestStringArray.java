package com.xiaogua.better.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestStringArray {

	@Test(expected = UnsupportedOperationException.class)
	public void testStringArray() {
		List<String> list = new ArrayList<String>();
		list.add("key_1");
		list.add("key_2");
		String[] strArr = list.toArray(new String[0]);
		System.out.println(Arrays.toString(strArr));
		//不可变
		List<String> list2 = Arrays.asList(strArr);
		System.out.println(list2);
		list2.add("key_3");
	}

	@Test
	public void testAppendTo() {
		String[] strArr = new String[] { "key_1" };
		String[] newStrArr = appendTo(strArr, "key_2");
		System.out.println(Arrays.toString(newStrArr));
	}

	public String[] appendTo(String[] oldArray, String str) {
		if (null == oldArray) {
			return new String[] { str };
		}
		String[] newArray = new String[oldArray.length + 1];
		System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
		newArray[oldArray.length] = str;
		return newArray;
	}
}
