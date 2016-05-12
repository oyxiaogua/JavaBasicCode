package com.xiaogua.better.algorithm;

import java.util.LinkedHashMap;
import java.util.List;

public class QueryDataByPage {
	/**
	 * 返回月份,数组[开始下标,结束下标],结束下标-开始下标=查询长度
	 */
	public static LinkedHashMap<String, Integer[]> getQueryTblList(List<String> monthList, int start, int length,
			int[] numArr) {
		LinkedHashMap<String, Integer[]> monthDataMap = new LinkedHashMap<String, Integer[]>();
		int pageBegin = start;
		int pageEnd = pageBegin + length;
		int tmpTotal = 0;
		int[] tmpSumArr = new int[numArr.length];// 数组累加和
		for (int i = 0, len = monthList.size(); i < len; i++) {
			tmpTotal += numArr[i];
			tmpSumArr[i] = tmpTotal;
			// 小于开始
			if (numArr[i] == 0 || tmpTotal <= pageBegin) {
				continue;
			}
			// 大于结束
			if ((i > 0 && tmpSumArr[i - 1] >= pageEnd)) {
				break;
			}
			// 数据<=查询数量
			if (tmpTotal <= pageEnd) {
				Integer[] limitSizeArr = new Integer[2];
				// 第一条或者一直不够
				if (i == 0) {
					limitSizeArr[0] = pageBegin;
					limitSizeArr[1] = numArr[i];
				} else if (tmpSumArr[i - 1] >= pageBegin && tmpTotal - numArr[i] >= 0) {
					// 本次开始满足条件
					limitSizeArr[0] = 0;
					limitSizeArr[1] = numArr[i];
				} else if (tmpSumArr[i - 1] < pageBegin) {
					// 扣除上次数据
					limitSizeArr[0] = numArr[i] + pageBegin - tmpTotal;
					limitSizeArr[1] = numArr[i];
				}
				monthDataMap.put(monthList.get(i), limitSizeArr);
			} else if (tmpTotal > pageEnd) {
				if (i > 0) {
					Integer[] limitSizeArr = new Integer[2];
					if (tmpSumArr[i - 1] > pageBegin) {
						// 前一条不够,本次够
						limitSizeArr[0] = 0;
						// 缺少的
						limitSizeArr[1] = pageBegin + length - tmpSumArr[i - 1];
					} else {
						limitSizeArr[0] = pageBegin - tmpSumArr[i - 1];
						limitSizeArr[1] = Math.min(numArr[i], limitSizeArr[0] + length);
					}
					monthDataMap.put(monthList.get(i), limitSizeArr);
				} else {
					// 本次足够
					Integer[] limitSizeArr = new Integer[2];
					limitSizeArr[0] = pageBegin;
					// 缺少的
					limitSizeArr[1] = Math.min(numArr[i], limitSizeArr[0] + length);
					monthDataMap.put(monthList.get(i), limitSizeArr);
				}
			}
		}
		return monthDataMap;
	}
}
