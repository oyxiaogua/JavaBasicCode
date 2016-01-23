package com.xiaogua.better.basic;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class TestGzip {
	@Test
	public void testGzip() throws Exception {
		String str = "测试中文压缩gzip123\r\n测试";
		byte[] srcArr = str.getBytes("UTF-8");
		byte[] compressArr = GzipCode.compressByte(srcArr);
		System.out.println(Arrays.toString(compressArr));
		Assert.assertTrue(GzipCode.isGzip(compressArr));
		srcArr = str.getBytes("UTF-8");
		byte[] normalArr = GzipCode.unzipByte(compressArr);
		Assert.assertArrayEquals(srcArr, normalArr);
	}

}
