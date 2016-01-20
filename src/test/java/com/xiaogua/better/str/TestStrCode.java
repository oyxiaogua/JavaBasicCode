package com.xiaogua.better.str;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Assert;
import org.junit.Test;

public class TestStrCode {
	@Test
	public void testConvertNumToExcelStr() {
		String rtnStr = StrCode.convertNumToExcelStr(731);
		Assert.assertEquals("ABC", rtnStr);
	}

	@Test
	public void testConvertExcelStrToNumber() throws Exception {
		int intRtn = StrCode.convertExcelStrToNumber("ABC");
		Assert.assertEquals(731, intRtn);
	}

	@Test
	public void testIsHasChinese() {
		String str = "。";
		Assert.assertTrue(StrCode.isHasChinese(str));
	}

	@Test
	public void testIsJavaIdentifier() {
		String str = "java";
		Assert.assertTrue(StrCode.isJavaIdentifier(str));
	}

	@Test
	public void testNullStr() {
		Object a = null;
		String b = (String) a;
		String c = b + "";
		Assert.assertEquals("null", c);
	}

	@Test
	public void testStrIndex() {
		String str = "1,3,5,4,6";
		int index = str.indexOf(3);
		Assert.assertEquals(index, -1);
		index = str.indexOf('3');
		Assert.assertEquals(index, 2);

		str = "彩色灯测试";
		index = str.indexOf("");
		Assert.assertEquals(index, 0);

		index = str.lastIndexOf("");
		Assert.assertEquals(index, 5);
	}

	@Test
	public void testStrObject() {
		Object he = new Object();
		String str = "hello";
		str += he;
		Assert.assertTrue(str.indexOf("hello") >= 0 && str.toString().indexOf("Object") > 0);

		he = new Object();
		str = "hello";
		he = he + str;
		Assert.assertTrue(he.toString().indexOf("Object") > 0);
	}

	@Test
	public void testStrCodePointCount() {
		String str3 = "123\uD800\uDC00456";
		System.out.println(str3);
		// 返回字符串指定范围Unicode码值的个数
		int codePointCount = str3.codePointCount(0, str3.length());
		System.out.println(str3.length() + ":codePointCount : " + codePointCount);
	}

	@Test
	public void testStrContain() {
		String str = "123";
		boolean rtn = str.contains("");
		Assert.assertTrue(rtn);
		rtn = str.contains(" ");
		Assert.assertFalse(rtn);
	}

	@Test
	public void testStrSplit() {
		/**
		 * split根据给定正则表达式的匹配拆分此字符串。 该方法的作用就像是使用给定的表达式和限制参数 0 来调用两参数 split
		 * 方法。因此，所得数组中不包括结尾空字符串。
		 */
		String str1 = "a,b,,,,";
		Assert.assertEquals(2, str1.split(",").length);
		str1 = "a,b,,,,c";
		Assert.assertEquals(6, str1.split(",").length);
	}

	@Test
	public void testStrUnicode() {
		System.out.println("红桃 : \u2665 ");
		System.out.println("方片 :\u2666 ");
		System.out.println("梅花 :\u2663 ");
		System.out.println("黑桃 : \u2660 ");
	}

	@Test
	public void testStrReplace() {
		String str = "iiiiiaaaammmxiaogua";
		String regex = "(.)\\1+";
		str = str.replaceAll(regex, "$1");
		Assert.assertEquals("iamxiaogua", str);
	}

	@Test
	public void testIsRotation() {
		String str1 = "abc";
		String str2 = "cab";
		boolean rtn = StrCode.isRotation(str1, str2);
		Assert.assertTrue(rtn);
	}

	@Test
	public void testHtmlSpacType() {
		System.out.println("不断行的空白,1个字符宽度=" + StringEscapeUtils.unescapeHtml4("&nbsp;&160#;"));
		System.out.println("半个空白,1个字符宽度=" + StringEscapeUtils.unescapeHtml4("&ensp;&8194#;"));
		System.out.println("一个空白,2个字符宽度=" + StringEscapeUtils.unescapeHtml4("&emsp;&8195#;"));
		System.out.println("窄空白,小于1个字符宽度=" + StringEscapeUtils.unescapeHtml4("&thinsp;&8201#;"));
	}

	@Test
	public void testEscapeHtml() {
		String html = "<html><head><title>测试</title></head></html>";
		System.out.println(StringEscapeUtils.escapeHtml3(html));
		System.out.println(StringEscapeUtils.escapeHtml4(html));
		System.out.println(org.springframework.web.util.HtmlUtils.htmlEscape(html));
	}
}
