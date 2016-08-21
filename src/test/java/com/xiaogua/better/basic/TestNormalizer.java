package com.xiaogua.better.basic;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class TestNormalizer {

	@Test
	public void testNormalizer() {
		String str = "\uFE64" + "a" + "\uFE65";
		Pattern pattern = Pattern.compile("[<>]");
		Matcher matcher = pattern.matcher(str);
		Assert.assertFalse(matcher.find());

		// 文本执行归一化操作 兼容的分解方式(将输入转换成可与目标进行安全比较的等效标准格式)
		//归一化可以确保具有相同意义的字符串具有统一的二进制描述
		str = Normalizer.normalize(str, Form.NFKC);
		matcher = pattern.matcher(str);
		Assert.assertTrue(matcher.find());

		str = "\u00E9"; // using a composed é
		Assert.assertEquals(1, str.length());
		String strNFD = Normalizer.normalize(str, Normalizer.Form.NFD);
		Assert.assertEquals(2, strNFD.length());
		Assert.assertTrue(Normalizer.isNormalized(strNFD, Normalizer.Form.NFD));
		Assert.assertFalse(Normalizer.isNormalized(strNFD, Normalizer.Form.NFC));
		String strNFC = Normalizer.normalize(strNFD, Form.NFC);
		Assert.assertEquals(str, strNFC);
	}
}
