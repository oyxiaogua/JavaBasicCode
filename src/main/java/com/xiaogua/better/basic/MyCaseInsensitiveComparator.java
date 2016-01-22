package com.xiaogua.better.basic;

import java.util.Comparator;

public class MyCaseInsensitiveComparator implements Comparator<String> {
	public static final MyCaseInsensitiveComparator INSTANCE = new MyCaseInsensitiveComparator();

	public int compare(String first, String second) {
		if (first == null) {
			return second == null ? 0 : 1;
		}
		return first.compareToIgnoreCase(second);
	}
}