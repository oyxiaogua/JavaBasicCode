package com.xiaogua.better.algorithm;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Test;

public class TestNumbericalAlgorithmCode {

	@Test
	public void testDoMuliply() {
		int x = 12;
		int y = 31;
		int rtn = NumbericalAlgorithmCode.doMuliply(x, y);
		Assert.assertEquals(x * y, rtn);
	}

	@Test
	public void testDoDivide() {
		int x = 31;
		int y = 12;
		Pair<Integer, Integer> rtnPair = NumbericalAlgorithmCode.doDivide(x, y);
		System.out.println(rtnPair);
	}

	@Test
	public void testPowerMod() {
		int x = 31;
		int y = 23;
		int n = 13;
		int rtn = NumbericalAlgorithmCode.powerMod(x, y, n);
		System.out.println(rtn);
	}

}
