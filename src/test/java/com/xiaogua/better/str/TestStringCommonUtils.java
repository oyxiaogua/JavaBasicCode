package com.xiaogua.better.str;

import java.security.MessageDigest;
import java.util.Arrays;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
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

	@Test
	public void testStringUtilsSplit() {
		String s1 = "A_B_C_DC";
		// 单个字符的切割
		System.out.println(Arrays.deepToString(StringUtils.split(s1, '_')));
		System.out.println(Arrays.deepToString(StringUtils.splitByWholeSeparator(s1, "C_")));
	}

	@Test
	public void testStringUtilsSubstringBetween() {
		String s1 = "A0A123A456B78B9B";
		System.out.println(StringUtils.substringBetween(s1, "A", "B"));
		System.out.println(StringUtils.substringAfter(s1, "A"));
		System.out.println(StringUtils.substringAfterLast(s1, "A"));
		System.out.println(StringUtils.substringBefore(s1, "B"));
		System.out.println(StringUtils.substringBeforeLast(s1, "B"));

		System.out.println("-------------------------------");
		String s2 = "A1BA2BCDA";
		// 返回字符串数组
		System.out.println(Arrays.deepToString(StringUtils.substringsBetween(s2, "A", "B")));
	}

	@Test
	public void testStringUtilsIgnoreCase() {
		String s1 = "adxwedwe";
		System.out.println(StringUtils.startsWithIgnoreCase(s1, "A"));
		System.out.println(StringUtils.endsWithIgnoreCase(s1, "E"));
		System.out.println(StringUtils.indexOfIgnoreCase(s1, "D"));
	}

	@Test
	public void testStringUtilsIsAlpha() {
		String s1 = "asdfgw";
		// 是否全有英文字符组成
		System.out.println(StringUtils.isAlpha(s1));

		String s2 = "asq12dfeAsdfASAr";
		// 是否全有英文或者数字组成
		System.out.println(StringUtils.isAlphanumeric(s2));
	}

	@Test
	public void testStringUtilsIsNumeric() {
		String s1 = "100";
		// 仅整型数字
		Assert.assertTrue(StringUtils.isNumeric(s1));
		String s2 = "100.1";
		Assert.assertFalse(StringUtils.isNumeric(s2));

		// 仅整型数字
		Assert.assertFalse(NumberUtils.isDigits(s2));
		// 判断出浮点型, 需要用到
		// org.apache.commons.lang3.math.NumberUtils.isNumber(String)
		Assert.assertTrue(NumberUtils.isNumber(s2));
	}

	@Test
	public void testStringUtilsContain() {
		String[] dataArr = new String[] { "hello", null, "Test" };
		boolean contain = StringCommonUtils.containsIgnoreCase("tEST", dataArr);
		Assert.assertTrue(contain);

		contain = StringCommonUtils.containsAny("tEST", dataArr);
		Assert.assertFalse(contain);

		contain = StringCommonUtils.containsAny(null, dataArr);
		System.out.println(contain);// false
	}

	@Test
	public void testStartsWithIgnoreCase() {
		boolean isStart = StringCommonUtils.startsWithIgnoreCase("TESTFOR", "tesT");
		Assert.assertTrue(isStart);
	}

	@Test
	public void testContainsAll() {
		String[] dataArr = new String[] { "hello", "world", "Test" };
		boolean isContain = StringCommonUtils.containsAll("Test hello world", dataArr);
		Assert.assertTrue(isContain);

		isContain = StringCommonUtils.containsAll("Tes_hello world", dataArr);
		Assert.assertFalse(isContain);
	}

	@Test
	public void testCountSubstr() {
		String str = "aaaaaaaaaaa";
		String subStr = "aaa";
		int total = StringCommonUtils.countSubstr(str, subStr, true);
		System.out.println(total);

		total = StringCommonUtils.countSubstr(str, subStr, false);
		System.out.println(total);
	}
}
