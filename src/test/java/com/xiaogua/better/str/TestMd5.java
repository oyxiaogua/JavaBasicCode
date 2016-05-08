package com.xiaogua.better.str;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class TestMd5 {
	@Test
	public void testMd5() {
		String str = "测试Md5摘要";
		String md5Str = DigestUtils.md5Hex(str);
		System.out.println("DigestUtils=" + md5Str);

		System.out.println("md5Str=" + md5Str.substring(8, 24));

	}

}
