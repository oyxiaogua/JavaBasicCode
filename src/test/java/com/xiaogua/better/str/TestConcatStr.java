package com.xiaogua.better.str;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.xiaogua.better.bean.StringConcatBean;

public class TestConcatStr {

	@Test
	public void testConcatStr() {
		List<String> strList = new ArrayList<String>();
		strList.add("test_1");
		strList.add("test_2");
		strList.add("test_3");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("test_key_1", "test_value_1");
		map.put("test_key_2", "test_value_2");
		map.put("test_key_3", "test_value_3");
		StringConcatBean bean = new StringConcatBean(1, "test_name", "test_address", strList, new BigDecimal("1234456"),
				map);
		System.out.println("---useConcatToStr--- ");
		String rtnStr = bean.useConcatToStr();
		System.out.println(rtnStr);
		System.out.println("---useStringBuilderToStr--- ");
		rtnStr = bean.useStringBuilderToStr();
		System.out.println(rtnStr);
		System.out.println("---useStringBufferToStr--- ");
		rtnStr = bean.useStringBufferToStr();
		System.out.println(rtnStr);
		System.out.println("---userGuavaToStringHelperToStr--- ");
		rtnStr = bean.userGuavaToStringHelperToStr();
		System.out.println(rtnStr);
		System.out.println("---useCommonLang3ToStringBuilderToString--- ");
		rtnStr = bean.useCommonLang3ToStringBuilderToString();
		System.out.println(rtnStr);
		System.out.println("---useCommonLang3ReflectionToStringBuilderToString--- ");
		rtnStr = bean.useCommonLang3ReflectionToStringBuilderToString();
		System.out.println(rtnStr);
	}
}
