package com.xiaogua.better.guava;

import java.util.List;

import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class TestGuavaLazyLoad {

	@Test(expected = ArithmeticException.class)
	public void testGuavaLazyLoad() {
		List<Integer> dataList = Lists.newArrayList(1, 2, 0, 3);
		List<Integer> resultList = Lists.transform(dataList, new Function<Integer, Integer>() {
			public Integer apply(Integer input) {
				return 12 / input;
			}
		});
		System.out.println(resultList.get(0));
		System.out.println(resultList.get(1));
		System.out.println(resultList.get(2));

	}
}
