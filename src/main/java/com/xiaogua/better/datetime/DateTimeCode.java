package com.xiaogua.better.datetime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

public class DateTimeCode {
	private static final String[] WEEKDAYS = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
	private static final int[] DAYS_OF_MONTH = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	public static final double EPOCH_MJD = -40587.0;
	public static final double MILLIS_PER_DAY = 86400000.0;
	/** HTTP头中日期时间格式 */
	public final static String HTTP_DATETIME_PATTERN = "EEE, dd MMM yyyy HH:mm:ss z";
	/** 标准日期时间格式，精确到毫秒 */
	public final static String NORM_DATETIME_MS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

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
	 * @param mjd
	 *            修正的儒略日期
	 * @return
	 */
	public static Date mjdToDate(double mjd) {
		return new Date(Math.round((EPOCH_MJD + mjd) * MILLIS_PER_DAY));
	}

	public static double dateToMJD(Date date) {
		return date.getTime() / MILLIS_PER_DAY - EPOCH_MJD;
	}

	public static Date xmlGregorianCalendarToDate(XMLGregorianCalendar webserviceDate) {
		GregorianCalendar ca = webserviceDate.toGregorianCalendar();
		return ca.getTime();
	}

	public static XMLGregorianCalendar dateToXmlGregorianCalendar(Date date) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		XMLGregorianCalendar gc = null;
		try {
			gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gc;
	}

	/**
	 * 字符串转为时间
	 * 
	 * @param timeString
	 * @return
	 */
	public static Date parseDateTime(String timeString) {
		try {
			return org.apache.commons.lang3.time.DateUtils.parseDate(timeString,
					new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "yyyyMMdd" });
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 时分秒置为0
	 */
	public static Date truncateDate(Date date) {
		return org.apache.commons.lang3.time.DateUtils.truncate(date, Calendar.DATE);
	}

	public static String addDateToStr(String dateStr, String formatStr, int type, int addValue) throws Exception {
		DateFormat dateFormatter = new SimpleDateFormat(formatStr);
		Date date = dateFormatter.parse(dateStr);
		date = getDateAfterAddSpecifiedTime(date, type, addValue);
		return dateFormatter.format(date);
	}

	public static Date getDateAfterAddSpecifiedTime(Date date, int type, int afterDay) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(type, afterDay);
		return calendar.getTime();
	}

	/**
	 * 返回正确的时间日期串(规范时间日期格式串)
	 */
	public static String getFormatDateTimeStr(String dateStr, String destFmtStr) throws Exception {
		if (StringUtils.isBlank(dateStr)) {
			throw new IllegalArgumentException("dateStr不能为空");
		}
		if (StringUtils.isBlank(destFmtStr)) {
			destFmtStr = "yyyy-MM-dd";
		}
		String[] dateStrArr = null;
		if (dateStr.indexOf("日") > 0) {
			dateStrArr = dateStr.split("日");
		} else {
			dateStrArr = dateStr.split("\\s+");
		}
		String srcFmtStr = "yyyyMMdd";
		if (dateStrArr.length == 1 && dateStr.length() > 9) {
			throw new Exception("暂不支持此种格式");
		}
		StringBuffer sb = new StringBuffer();
		// 时间部分
		String datePartStr = getFormatDateStr(dateStrArr[0]);
		if (datePartStr.length() == 6) {
			// 201612
			sb.append(datePartStr.substring(0, 4)).append('0').append(datePartStr.substring(4, 5)).append('0')
					.append(datePartStr.substring(5, 6));
			datePartStr = sb.toString();
			sb.setLength(0);
		}
		sb.append(datePartStr);
		if (dateStrArr.length == 2) {
			String timePartStr = getFormatTimeStr(dateStrArr[1]);
			sb.append(" ").append(timePartStr);
			dateStr = sb.toString();
			sb.setLength(0);
			sb.append(srcFmtStr).append(" ").append("HH:mm:ss");
			srcFmtStr = sb.toString();
		} else {
			dateStr = sb.toString();
		}
		Date srcDate = DateUtils.parseDate(dateStr, srcFmtStr);
		DateFormat format = new SimpleDateFormat(destFmtStr);
		return format.format(srcDate);
	}

	private static String getFormatDateStr(String dateStr) {
		return dateStr.replaceAll("\\D", "").trim();
	}

	private static String getFormatTimeStr(String timeStr) {
		return timeStr.trim().replace("秒", "").replaceAll("\\D", ":").trim();
	}

}
