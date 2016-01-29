package com.xiaogua.better.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.assertj.core.data.MapEntry;
import org.assertj.core.data.Offset;
import org.junit.Test;

import com.google.common.collect.Maps;

public class TestAssertj {
	@Test
	public void testAssertjSimple() {
		// 忽略大小写
		Assertions.assertThat("teSt").isEqualTo("teSt").isEqualToIgnoringCase("TesT");
		// 包含次数
		Assertions.assertThat("todoDo").containsOnlyOnce("do");
		// 断言开始字符串 结束字符穿 字符串长度
		Assertions.assertThat("Frodo").startsWith("Fro").endsWith("do").hasSize(5);
		Assertions.assertThat("Frodo").doesNotStartWith("fro").doesNotEndWith("don");
		// 断言包含字符串 不包含字符串
		Assertions.assertThat("Frodo").contains("rod").doesNotContain("fro");
		// 无序包含
		Assertions.assertThat("Gandalf the grey").contains("gre", "and");
		// 包含忽略大小写
		Assertions.assertThat("Gandalf the GREY").containsIgnoringCase("gre");
		// 判断正则匹配
		Assertions.assertThat("Frodo").matches(".*o.o").doesNotMatch(".*d");
		// 只包含数字
		Assertions.assertThat("3210").containsOnlyDigits();
	}

	@Test
	public void testAssertjDate() {
		Date date1 = new Date();
		Date date2 = new Date(date1.getTime() + 100);
		Assertions.assertThat(date1).isBefore(date2);
		Assertions.assertThat(date1).isEqualToIgnoringSeconds(date2);
		Assertions.assertThat(date1).isInSameDayAs(date2);
	}

	@Test
	public void testAssertjMap() {
		Map<String, Object> foo = Maps.newHashMap();
		foo.put("A", 1);
		foo.put("B", 2);
		foo.put("C", 3);

		// 断言 map 不为空 size
		Assertions.assertThat(foo).isNotEmpty().hasSize(3);
		// 断言 map 包含元素
		Assertions.assertThat(foo).contains(MapEntry.entry("A", 1), MapEntry.entry("B", 2));
		// 断言 map 包含key
		Assertions.assertThat(foo).containsKeys("A", "B", "C");
		// 断言 map 包含value
		Assertions.assertThat(foo).containsValue(3);
	}

	@Test
	public void testAssertjNumber() {
		Assertions.assertThat(8.1).isEqualTo(8.0, Offset.offset(0.1));
		Assertions.assertThat(8.1f).isEqualTo(8.2f, Offset.offset(0.1f));
		Assertions.assertThat(8.0).isEqualTo(8.1, Assertions.within(0.1));

		Assertions.assertThat(8.1).isCloseTo(8.0, Assertions.within(0.1));
		Assertions.assertThat(5.0).isCloseTo(6.0, Assertions.withinPercentage(20.0));
		Assertions.assertThat(8.2f).isCloseTo(8.0f, Assertions.within(0.2f));
		Assertions.assertThat(new BigDecimal("8.0")).isGreaterThanOrEqualTo(new BigDecimal("8.00"));

		// 断言大于 大于等于
		Assertions.assertThat(42).isGreaterThan(38).isGreaterThanOrEqualTo(38);
		// 断言小于 小于等于
		Assertions.assertThat(42).isLessThan(58).isLessThanOrEqualTo(58);
	}
}
