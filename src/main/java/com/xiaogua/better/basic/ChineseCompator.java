package com.xiaogua.better.basic;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

public class ChineseCompator implements Comparator<String> {
	private static Collator cmp = Collator.getInstance(Locale.CHINA);

	public int compare(String o1, String o2) {
		if (o1 == null) {
			return o2 == null ? 0 : 1;
		}
		return cmp.compare(o1, o2);
	}
}