package com.xiaogua.better.basic;

import java.math.BigInteger;

import org.junit.Test;

public class TestBigInteger {

	@Test
	public void testBigIntegerDivideAndRemainder() {
		BigInteger aBig = new BigInteger("5025");
		BigInteger bBig = new BigInteger("1024");
		BigInteger[] biArray = aBig.divideAndRemainder(bBig);
		System.out.println("商:" + biArray[0]);
		System.out.println("余数:" + biArray[1]);
	}

}
