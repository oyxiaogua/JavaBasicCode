package com.xiaogua.better.file;

import org.junit.Test;

public class TestFileCommonUtil {
	@Test
	public void testGetReadableSize() {
		long size = 123456;
		System.out.println(FileCommonUtil.getReadableSize(size));
	}
}
