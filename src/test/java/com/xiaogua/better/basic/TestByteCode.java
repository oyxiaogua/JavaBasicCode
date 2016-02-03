package com.xiaogua.better.basic;

import org.junit.Assert;
import org.junit.Test;

public class TestByteCode {

	@Test
	public void testIndexOf() {
		byte[] srcArr = new byte[] { 1, 0, 0, 1, 0, 1, 0, 1, 0, 2, 0, 1, 2, 0, 1, 1, 2, 1 };
		byte[] subArr = new byte[] { 0, 1, 2 };
		int index = ByteCode.indexOf(srcArr, subArr);
		Assert.assertEquals(10, index);

		byte[] subArr2 = new byte[] { 2, 1, 1 };
		index = ByteCode.indexOf(srcArr, subArr2);
		Assert.assertEquals(-1, index);
	}
}
