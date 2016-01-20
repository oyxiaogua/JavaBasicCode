package com.xiaogua.better.str;

import java.text.MessageFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class TestMsgFormat {

	@Test
	public void testMsgFormat() {
		// Message.format对用两个单引号括起来的表示不格式化
		String s1 = "this is '{0}' and this is {1} ";
		String s2 = MessageFormat.format(s1, "test", "test2");
		Assert.assertEquals("this is {0} and this is test2 ", s2);
	}

	@Test
	public void testMsgFormatEscape() {
		// 两个连接的单引号代表一个单引号 转义
		String s1 = "this is ''{0}'' and this is {1} ";
		String s2 = MessageFormat.format(s1, "test", "test2");
		Assert.assertEquals("this is 'test' and this is test2 ", s2);
	}

	@Test
	public void testMsgFormatNormal() {
		String pig = "{0}{1}{2}{3}{4}{5}{6}{7}{8}{9}{10}{11}{12}{13}{14}{15}{16}";
		Object[] array = new Object[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
				"Q" };
		String value = MessageFormat.format(pig, array);
		Assert.assertEquals("ABCDEFGHIJKLMNOPQ", value);

		value = MessageFormat.format("At {1,time} on {1,date}, there was {2} on planet {0,number,integer}.", 7,
				new Date(), "test");
		System.out.println(value);

		double num = 1.252;
		String str = MessageFormat.format("{0,number,#.#}", num);
		Assert.assertEquals("1.3", str);
	}

	@Test
	public void testMsgFormatParser() throws Exception {
		String sid = "u1234q5678";
		Object[] values = new MessageFormat("u{0,number,integer}q{1,number,integer}").parse(sid);
		for (Object v : values) {
			System.out.println(v + "(" + v.getClass().getName() + ")");
		}
	}
}
