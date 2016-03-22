package com.xiaogua.better.test;

import java.util.Arrays;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.hamcrest.collection.IsIn;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.hamcrest.collection.IsIterableWithSize;
import org.hamcrest.collection.IsMapContaining;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsCollectionContaining;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.hamcrest.core.StringContains;
import org.hamcrest.core.StringEndsWith;
import org.hamcrest.core.StringStartsWith;
import org.hamcrest.number.OrderingComparison;
import org.hamcrest.text.IsEmptyString;
import org.hamcrest.text.IsEqualIgnoringCase;
import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Maps;

public class TestHamcrestAssertions {
	@Test
	public void testHamcrestAssertString() {
		Assert.assertThat("FoOBarBaz", Hamcrest_String_ContainsIgnoreCase_Matchers.containsIgnoreCase("oobarbaz"));

		Assert.assertThat("string", IsEqualIgnoringCase.equalToIgnoringCase("STRING"));
		Assert.assertThat("string", StringStartsWith.startsWith("str"));
		Assert.assertThat("string", StringEndsWith.endsWith("ng"));
		Assert.assertThat("string", StringContains.containsString("in"));
		Assert.assertThat("string", Is.is(IsNot.not(IsEqualIgnoringCase.equalToIgnoringCase("STRIN"))));

		// 长度为0
		Assert.assertThat("", IsEmptyString.isEmptyString());
		Assert.assertThat("a", IsNot.not(IsEmptyString.isEmptyString()));

		// null或者长度为0
		Assert.assertThat("", IsEmptyString.isEmptyOrNullString());

		// null
		Assert.assertThat(null, IsNull.nullValue());

		Assert.assertThat("", IsIn.isIn(Arrays.asList("", "a")));

		Assert.assertThat("", IsNull.notNullValue());
	}

	@Test
	public void testHamcrestAssertMap() {
		Map<String, Object> foo = Maps.newHashMap();
		foo.put("A", 1);
		foo.put("B", 2);
		foo.put("C", 3);
		Assert.assertThat(foo, IsMapContaining.hasEntry("A", 1));
		Assert.assertThat(foo, IsMapContaining.hasKey("B"));
		Assert.assertThat(foo, IsMapContaining.hasValue(1));
	}

	@Test
	public void testHamcrestAssertNumber() {
		Assert.assertThat(4, CoreMatchers.is(4));
		Assert.assertThat(1, IsEqual.equalTo(1));
		Assert.assertThat(2, OrderingComparison.lessThanOrEqualTo(3));
		Assert.assertThat(3, OrderingComparison.greaterThanOrEqualTo(2));
	}

	@Test
	public void testHamcrestAssertList() {
		Assert.assertThat(Arrays.asList(0, 1), IsCollectionContaining.hasItem(0));
		Assert.assertThat(Arrays.asList(0, 1), IsIterableContainingInOrder.contains(0, 1));
		Assert.assertThat(Arrays.asList(0, 1), IsIterableContainingInAnyOrder.containsInAnyOrder(1, 0));
		Assert.assertThat(Arrays.asList(0, 1), IsIterableWithSize.iterableWithSize(2));
	}

	@Test(expected = AssertionError.class)
	public void testAssertTrue() {
		String str = "test2";
		String expectStr = "test";
		String errorMsg = String.format("expect %s,but %s", expectStr, str);
		Assert.assertTrue(errorMsg, str.equals(expectStr));
	}

}
