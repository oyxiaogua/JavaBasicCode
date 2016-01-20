package com.xiaogua.better.datetime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeCode {
	private static final String[] WEEKDAYS = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
	private static final int[] DAYS_OF_MONTH = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	public static final double EPOCH_MJD = -40587.0;
	public static final double MILLIS_PER_DAY = 86400000.0;

	/**
	 * 获取GMT时间
	 * 
	 * @return
	 */
	public static String getUTCTimeStr() {
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TimeZone pst = TimeZone.getTimeZone("Etc/GMT+0");
		Date curDate = new Date();
		dateFormatter.setTimeZone(pst);
		return dateFormatter.format(curDate);// 这就是我们想要获取的值
	}

	/**
	 * 删除时分秒
	 * 
	 * @param date
	 * @return
	 */
	public static Date truncate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 将"2014-08-25 00:00:00"转换为"2014-08-25T00:00:00Z"
	 * 
	 * @param str
	 * @return
	 */
	public static String transDateStr(String str) {
		String[] strs = str.split("\\s");
		if (strs.length != 2) {
			throw new RuntimeException("TimeStr=" + str + " converted error!");
		} else {
			return strs[0] + "T" + strs[1] + "Z";
		}
	}

	/**
	 * 将"2014-08-25T00:00:00Z"转换为"2014-08-25 00:00:00"
	 * 
	 * @param str
	 * @return
	 */
	public static String transToCommonDateStr(String str) {
		return str.replace("T", " ").replace("Z", "");
	}

	/**
	 * 获取月份最大天数
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getMaxDayOfMonth(int year, int month) {
		if (month == 2 && (year % 4 == 0 && year % 100 != 0 || year % 400 == 0))
			return 29;
		return DAYS_OF_MONTH[month - 1];
	}

	/**
	 * 判断日期是否是周六周末
	 */
	public static boolean isWeekend(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek == 1 || dayOfWeek == 7;
	}

	/**
	 * 获取星期几
	 * 
	 * @param date
	 * @return
	 */
	public static String getWeekOfDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return WEEKDAYS[w];
	}
	
	/**
	 * @param mjd 修正的儒略日期
	 * @return
	 */
	public static Date mjdToDate(double mjd) {
		return new Date(Math.round((EPOCH_MJD + mjd) * MILLIS_PER_DAY));
	}

	public static double dateToMJD(Date date) {
		return date.getTime() / MILLIS_PER_DAY - EPOCH_MJD;
	}
}
