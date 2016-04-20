package com.xiaogua.better.str;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;
import org.junit.Assert;
import org.junit.Test;

public class TestStringCommonUtils {

	@Test
	public void testLeftPadding() {
		String str = "A B";
		String padStr = StringCommonUtils.leftPad(str, 5, '0');
		Assert.assertEquals("00A B", padStr);
	}

	@Test
	public void testRightPadding() {
		String str = "A B";
		String padStr = StringCommonUtils.rightPad(str, 5, '0');
		Assert.assertEquals("A B00", padStr);
	}

	@Test
	public void testMd5Str() throws Exception {
		String str = "test数据Md5值";
		String md5Str = StringCommonUtils.md5Hex(str);

		String md5Str2 = StringCommonUtils.md5HexWithDigestUtils(str);
		Assert.assertEquals(md5Str2, md5Str);
	}

	@Test
	public void testMd5DigestOneTimeUse() throws Exception {
		String str = "test";
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(str.getBytes());
		String md5 = Hex.encodeHexString(md.digest());// 只能被调用一次,否则重置到初始状态
		System.out.println("md5 = " + md5);

		String md5Again = Hex.encodeHexString(md.digest());
		System.out.println("md5Again = " + md5Again);

		str = "";
		md = MessageDigest.getInstance("MD5");
		md.update(str.getBytes());
		String blankStrMd5 = Hex.encodeHexString(md.digest());
		Assert.assertEquals(md5Again, blankStrMd5);
	}

	@Test
	public void testMd5DigestClone() throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		String str = "test";
		md.update(str.getBytes());
		MessageDigest mdClone = (MessageDigest) md.clone();
		byte[] byteArr = md.digest();
		System.out.println(Hex.encodeHexString(byteArr));
		md.update(str.getBytes());
		byteArr = mdClone.digest();
		System.out.println(Hex.encodeHexString(byteArr));
	}

}
