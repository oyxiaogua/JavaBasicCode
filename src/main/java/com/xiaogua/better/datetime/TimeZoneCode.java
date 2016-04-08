package com.xiaogua.better.datetime;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

public class TimeZoneCode {
	/**
	 * 计算时区,-12 ~ 12
	 * 
	 * @param timeZone
	 * @return
	 */
	public static int getTimeZoneValue(TimeZone timeZone) {
		return timeZone.getRawOffset() / (3600 * 1000);
	}

	/**
	 * 获取系统默认时区
	 * 
	 * @return
	 */
	public static int getCurrentTimeZoneValue() {
		return getTimeZoneValue(TimeZone.getDefault());
	}

	/**
	 * @Description 获取当前中国时区的TIMESTAMP日期
	 * @return
	 */
	public static Timestamp getChinaTimestamp() {
		final TimeZone zone = TimeZone.getTimeZone("GMT+8");// 获取中国时区
		TimeZone.setDefault(zone);// 设置时区
		return new Timestamp((new java.util.Date()).getTime());
	}

	/**
	 * 获取可能的时间组合
	 */
	public static List<String> getAllPossibleTime(String timeStr) {
		if (StringUtils.isEmpty(timeStr)) {
			throw new IllegalArgumentException("timeStr不能为空");
		}
		timeStr = timeStr.replaceAll("\\D+", "");
		if (timeStr.length() < 3) {
			throw new IllegalArgumentException("timeStr错误");
		}
		List<String> rtnList = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		if (timeStr.length() == 3) {
			sb.append('0').append(timeStr.charAt(0)).append(':').append('0').append(timeStr.charAt(1)).append(':')
					.append('0').append(timeStr.charAt(2));
			rtnList.add(sb.toString());
			return rtnList;
		}
		if (timeStr.length() == 6) {
			if (checkTimeStr(timeStr, rtnList, sb)) {
				rtnList.add(sb.toString());
			}
			return rtnList;
		}
		List<List<String>> allList = new ArrayList<List<String>>();
		char[] dataArr = timeStr.toCharArray();
		getAllPossibleSplit(allList, rtnList, dataArr, 0, dataArr.length, 1, 2, 3);
		getLegalData(allList, rtnList, sb);
		return rtnList;
	}

	private static boolean checkTimeStr(String timeStr, List<String> rtnList, StringBuffer sb) {
		sb.setLength(0);
		String str = timeStr.substring(0, 2);
		if (!validateHH(str)) {
			return false;
		}
		sb.append(str).append(':');
		str = timeStr.substring(2, 4);
		if (!validateMMSS(str)) {
			return false;
		}
		sb.append(str).append(':');
		str = timeStr.substring(4);
		if (!validateMMSS(str)) {
			return false;
		}
		sb.append(str);
		return true;
	}

	private static void getLegalData(List<List<String>> rtnList, List<String> dataList, StringBuffer sb) {
		for (List<String> subList : rtnList) {
			sb.setLength(0);
			for (String str : subList) {
				sb.append(formatStr(str));
			}
			if (checkTimeStr(sb.toString(), dataList, sb)) {
				dataList.add(sb.toString());
			}
		}
	}

	private static String formatStr(String str) {
		if (str.length() == 0) {
			return "00";
		}
		return String.format("%2s", str).replace(' ', '0');
	}

	private static void getAllPossibleSplit(List<List<String>> rtnList, List<String> dataList, char[] charArr,
			int index, final int len, int minLen, int maxLen, int num) {
		if (num == 0) {
			if (index == len) {
				rtnList.add(new ArrayList<String>(dataList));
			}
			return;
		}

		for (int j = minLen; j <= maxLen; j++) {
			if (j + index <= len) {
				dataList.add(String.valueOf(charArr, index, j));
				index += j;
				num--;
				getAllPossibleSplit(rtnList, dataList, charArr, index, len, minLen, maxLen, num);
				dataList.remove(dataList.size() - 1);
				num++;
				index -= j;
			} else {
				return;
			}
		}
	}

	private static boolean validateHH(String str) {
		int rtn = Integer.parseInt(str);
		if (rtn > 24) {
			return false;
		}
		return true;
	}

	private static boolean validateMMSS(String str) {
		int rtn = Integer.parseInt(str);
		if (rtn > 59) {
			return false;
		}
		return true;
	}

}
