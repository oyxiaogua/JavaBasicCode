package com.xiaogua.better.basic;

import java.util.Iterator;
import java.util.Vector;

import org.junit.Assert;
import org.junit.Test;

public class TestVector {

	@Test
	public void testVectorWrongDelElement() {
		Vector<Integer> vector = new Vector<Integer>();
		for (int i = 0; i < 10; i++) {
			vector.add(i);
		}
		for (int i = 0; i < vector.size(); i++) {
			vector.remove(i);
		}
		Assert.assertTrue(vector.size() > 0);
	}

	@Test
	public void testVectorDelElement() {
		Vector<Integer> vector = new Vector<Integer>();
		for (int i = 0; i < 10; i++) {
			vector.add(i);
		}
		for (int i = 0; i < vector.size(); i++) {
			vector.remove(i);
			i--;
		}
		Assert.assertTrue(vector.size() == 0);
	}

	@Test
	public void testVectorDelElementWithIterator() {
		Vector<Integer> vector = new Vector<Integer>();
		for (int i = 0; i < 10; i++) {
			vector.add(i);
		}
		Iterator<Integer> it = vector.iterator();
		while (it.hasNext()) {
			it.next();
			it.remove();
		}
		Assert.assertTrue(vector.size() == 0);
	}

}
