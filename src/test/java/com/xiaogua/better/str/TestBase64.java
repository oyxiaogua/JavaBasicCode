package com.xiaogua.better.str;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class TestBase64 {
	private final String UTF8 = "UTF-8";

	@Test
	public void testCommonsCodecBase64() throws Exception {
		String str = "测试中文base64";
		String base64Str = Base64.encodeBase64String(str.getBytes(UTF8));
		System.out.println(base64Str);

		byte[] decodeBytes = Base64.decodeBase64(base64Str);
		System.out.println(new String(decodeBytes, UTF8));
	}

	@Test
	public void testBouncycastleBase64() throws Exception {
		String str = "测试中文base64";
		byte[] encodeBytes = org.bouncycastle.util.encoders.Base64.encode(str.getBytes(UTF8));
		System.out.println(new String(encodeBytes, UTF8));
		byte[] decodeBytes = org.bouncycastle.util.encoders.Base64.decode(encodeBytes);
		System.out.println(new String(decodeBytes, UTF8));
	}
}
