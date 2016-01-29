package com.xiaogua.better.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.junit.Test;

import org.junit.Assert;

public class TestValidate {

	@Test
	public void testValidateWithJava8Object() {
		Map<String, String> map = new HashMap<String, String>();
		validateWithJava8Object("", map);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateWithCommonLang3() {
		Map<String, String> map = new HashMap<String, String>();
		validateWithCommonLang3(3, "  ", map);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testValidateIndexWithCommonLang3() {
		List<String> list = new ArrayList<String>();
		list.add("1");
		validateIndexWithCommonLang3(list, -1);
	}

	@Test
	public void testassertWithSpringFrameWork() {
		assertWithSpringFrameWork(" ", 1, 2);
	}

	@Test
	public void testNotNullPredicateWithJava() {
		boolean check = notNullPredicateWithJava("    ");
		Assert.assertFalse(check);
	}

	@Test
	public void testNotNullPreconditionsWithGuava() {
		notNullPreconditionsWithGuava("", 1, 2);
	}

	public void notNullPreconditionsWithGuava(String name, int start, int end) {
		com.google.common.base.Preconditions.checkNotNull(name, "Name must not be null");
		com.google.common.base.Preconditions.checkArgument(start < end, "Start (%s) must be smaller than end (%s)",
				start, end);
	}

	public boolean notNullPredicateWithJava(String str) {
		java.util.function.Predicate<String> notNullPre = new java.util.function.Predicate<String>() {
			public boolean test(String t) {
				return StringUtils.isNotBlank(t);
			}
		};
		return notNullPre.test(str);
	}

	public void assertWithSpringFrameWork(String name, int start, int end) {
		org.springframework.util.Assert.notNull(name, "Name must not be null");
		// 不会过滤空格
		org.springframework.util.Assert.hasLength(name, "Name must have content");
		org.springframework.util.Assert.isTrue(start < end,
				"Start (" + start + ") must be smaller than end (" + end + ")");
	}

	public void validateIndexWithCommonLang3(List<String> list, int index) {
		Validate.validIndex(list, index);
	}

	public void validateWithCommonLang3(int age, String name, Map<String, String> map) {
		Validate.isTrue(age > 0 && age < 150, "age should be lt 150 and gt 0");
		Validate.notBlank(name, "name must not blank");
		Validate.notEmpty(map);
	}

	public void validateWithJava8Object(String str, Map<String, String> map) {
		Objects.requireNonNull(str, "first is Null.");
		Objects.requireNonNull(map, "map is null");
	}
}
