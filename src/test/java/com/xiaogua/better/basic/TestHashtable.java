package com.xiaogua.better.basic;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;
import java.util.Map.Entry;

import org.junit.Test;

public class TestHashtable {

	@Test
	public void testEnumerationIterator() {
		int val;
		Random random = new Random();
		Hashtable<String, Integer> table = new Hashtable<String, Integer>();
		for (int i = 0; i < 10; i++) {
			val = random.nextInt(100);
			table.put(String.valueOf(i), val);
		}
		Iterator<Entry<String, Integer>> iter = table.entrySet().iterator();
		Entry<String, Integer> entry;
		while (iter.hasNext()) {
			entry = (Entry<String, Integer>) iter.next();
			System.out.println(entry.getKey() + "," + entry.getValue());
		}
		System.out.println("--------------------------");
		Enumeration<Integer> enu = table.elements();
		Integer value = null;
		while (enu.hasMoreElements()) {
			value = enu.nextElement();
			System.out.println(value);
		}
	}
}
