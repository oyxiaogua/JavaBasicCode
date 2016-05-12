package com.xiaogua.better.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class TestQueryDataByPage {
	@Test
	public void testGetQueryTblList() {
		List<String> monthList = new ArrayList<String>();
		monthList.add("201601");
		monthList.add("201602");
		monthList.add("201603");
		monthList.add("201604");
		monthList.add("201605");
		monthList.add("201606");
		monthList.add("201607");
		int[] numArr = new int[] { 1, 31, 0, 6, 1, 4, 7 };
		LinkedHashMap<String, Integer[]> rtnMap = QueryDataByPage.getQueryTblList(monthList, 40, 7, numArr);
		for (Map.Entry<String, Integer[]> entry : rtnMap.entrySet()) {
			System.out.println(entry.getKey() + "," + Arrays.toString(entry.getValue()));
		}
	}
}
