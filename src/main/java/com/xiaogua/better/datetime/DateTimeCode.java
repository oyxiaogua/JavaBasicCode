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
	/** 普通日期时间格式 */
	public final static String FULL_DATETIME = "yyyy-MM-dd HH:mm:ss";

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
	 * 返回正确的时间日期串(规范时间日期格式串,只对规范数据有效,数据不规范会抛出异常)
	 */
	public static String getFormatDateTimeStr(String dateStr, String destFmtStr) throws Exception {
		if (StringUtils.isBlank(dateStr)) {
			throw new IllegalArgumentException("dateStr不能为空");
		}
		if (StringUtils.isBlank(destFmtStr)) {
			destFmtStr = "yyyy-MM-dd";
		}
		dateStr = dateStr.trim();
		if (dateStr.length() < 6) {
			throw new Exception("暂不支持此种格式");
		}
		String[] dateStrArr = null;
		if (dateStr.indexOf("日") > 0) {
			dateStrArr = dateStr.split("日");
		} else {
			dateStrArr = dateStr.split("\\s+");
		}
		String srcFmtStr = "yyyy-MM-dd";
		if (dateStrArr.length == 1 && dateStr.length() > 10) {
			throw new Exception("暂不支持此种格式");
		}
		StringBuffer sb = new StringBuffer();
		String datePartStr = getFormatDateStr(dateStrArr[0]);
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
		format.setLenient(true);
		return format.format(srcDate);
	}

	/**
	 * 字符串转为Date
	 */
	public static Date getDateFromStr(String dateStr, String formatStr) throws Exception {
		DateFormat dateFormatter = new SimpleDateFormat(formatStr);
		return dateFormatter.parse(dateStr);
	}
	
	/**
	 * Date转为字符串
	 */
	public static String getStrFromDate(Date date, String formatStr) throws Exception {
		DateFormat dateFormatter = new SimpleDateFormat(formatStr);
		return dateFormatter.format(date);
	}


	/**
	 * 获取格式化后的时间
	 */
	public static String getFormatTimeStr(String timeStr) throws Exception {
		timeStr = replaceTimeStr(timeStr);
		if (StringUtils.isBlank(timeStr)) {
			return "";
		}
		if (timeStr.length() > 6) {
			throw new IllegalArgumentException("错误的时间");
		}
		if (timeStr.length() == 0) {
			return "00:00:00";
		}
		StringBuffer sb = new StringBuffer();
		if (timeStr.length() == 1) {
			sb.append(formatStr(timeStr)).append(":00:00");
			return sb.toString();
		}
		if (parseIntValue(timeStr.replace(":", "")) > 235959) {
			throw new IllegalArgumentException("错误的时间");
		}
		if (timeStr.indexOf(":") > 0) {
			String[] timeArr = timeStr.split(":", -1);
			if (timeArr.length == 3) {
				// 1:2:3
				if (parseIntValue(timeArr[0]) <= 23 && parseIntValue(timeArr[1]) <= 59
						&& parseIntValue(timeArr[2]) <= 59) {
					sb.append(formatStr(timeArr[0])).append(':').append(formatStr(timeArr[1])).append(':')
							.append(formatStr(timeArr[2]));
					return sb.toString();
				}
			}
			if (timeArr.length == 2) {
				if (timeArr[0].length() == 4) {
					// 1213:4
					if (parseIntValue(timeArr[0].substring(0, 2)) <= 23 && parseIntValue(timeArr[0].substring(2)) <= 59
							&& parseIntValue(timeArr[1]) <= 59) {
						sb.append(timeArr[0].substring(0, 2)).append(':').append(timeArr[0].substring(2, 4)).append(':')
								.append(formatStr(timeArr[1]));
						return sb.toString();
					}
				}
				if (timeArr[1].length() == 4) {
					// 1:1213
					if (parseIntValue(timeArr[0]) <= 23 && parseIntValue(timeArr[1].substring(0, 2)) <= 59
							&& parseIntValue(timeArr[1].substring(2)) <= 59) {
						sb.append(formatStr(timeArr[0])).append(':').append(timeArr[1].substring(0, 2)).append(':')
								.append(timeArr[1].substring(2, 4));
						return sb.toString();
					}
				}
				if (timeArr[0].length() >= 2) {
					// 2:41
					if (parseIntValue(timeArr[0].substring(0, 2)) >= 24 && parseIntValue(timeArr[0].substring(1)) <= 59
							&& parseIntValue(timeArr[1]) <= 59) {
						sb.append(formatStr(timeArr[0].substring(0, 1))).append(':')
								.append(formatStr(timeArr[0].substring(1))).append(':').append(formatStr(timeArr[1]));
						return sb.toString();
					}
				}
				if (timeArr[1].length() >= 2) {
					// 58:1
					if (parseIntValue(timeArr[0]) <= 23 && parseIntValue(timeArr[1].substring(0, 2)) >= 56
							&& parseIntValue(timeArr[1].substring(0, 2)) <= 59) {
						sb.append(formatStr(timeArr[0])).append(':').append(timeArr[1].substring(0, 2)).append(':')
								.append(formatStr(timeArr[1].substring(2)));
						return sb.toString();
					}
				}
				// 1:2
				if (parseIntValue(timeArr[0]) <= 23 && parseIntValue(timeArr[1]) <= 59) {
					sb.append(formatStr(timeArr[0])).append(':').append(formatStr(timeArr[1])).append(":00");
					return sb.toString();
				}
			}
		} else if (timeStr.indexOf(":") < 0) {
			if (timeStr.length() >= 2 && timeStr.length() <= 3) {
				if (parseIntValue(timeStr.substring(0, 2)) > 23) {
					// 2:5
					if (timeStr.length() == 2) {
						sb.append(formatStr(timeStr.substring(0, 1))).append(':')
								.append(formatStr(timeStr.substring(1))).append(":00");
						return sb.toString();
					}
					if (parseIntValue(timeStr.substring(1)) <= 59) {
						// 2:59
						sb.append(formatStr(timeStr.substring(0, 1))).append(':')
								.append(formatStr(timeStr.substring(1))).append(":00");
						return sb.toString();
					}
				}
			}
			if (timeStr.length() == 6) {
				// 121314
				if (parseIntValue(timeStr.substring(0, 2)) <= 23 && parseIntValue(timeStr.substring(2, 4)) <= 59
						&& parseIntValue(timeStr.substring(4, 6)) <= 59) {
					sb.append(timeStr.substring(0, 2)).append(':').append(timeStr.substring(2, 4)).append(':')
							.append(timeStr.substring(4, 6));
					return sb.toString();
				}
			}
			if (timeStr.length() == 5) {
				// 122
				if (parseIntValue(timeStr.substring(0, 2)) > 23 && parseIntValue(timeStr.substring(1, 3)) <= 59
						&& parseIntValue(timeStr.substring(3)) <= 59) {
					sb.append(formatStr(timeStr.substring(0, 1))).append(':').append(timeStr.substring(1, 3))
							.append(':').append(timeStr.substring(3));
					return sb.toString();
				}
				// 212
				if (parseIntValue(timeStr.substring(0, 2)) <= 23 && parseIntValue(timeStr.substring(1, 3)) > 59
						&& parseIntValue(timeStr.substring(1, 3)) > 59 && parseIntValue(timeStr.substring(3)) <= 59) {
					sb.append(timeStr.substring(0, 2)).append(':').append(formatStr(timeStr.substring(2, 3)))
							.append(':').append(timeStr.substring(3));
					return sb.toString();
				}
				// 221
				if (parseIntValue(timeStr.substring(0, 2)) <= 23 && parseIntValue(timeStr.substring(2, 4)) <= 59
						&& parseIntValue(timeStr.substring(3)) > 59) {
					sb.append(timeStr.substring(0, 2)).append(':').append(timeStr.substring(2, 4)).append(':')
							.append(formatStr(timeStr.substring(4)));
					return sb.toString();
				}
			}
		}
		throw new Exception("暂不支持此种格式");
	}

	private static int parseIntValue(String str) throws Exception {
		if (str.length() == 0) {
			return 0;
		}
		int rtn = Integer.parseInt(str);
		if (rtn < 0) {
			throw new Exception("错误的时间");
		}
		return rtn;
	}

	private static String formatStr(String str) {
		if (str.length() == 0) {
			return "00";
		}
		return String.format("%2s", str).replace(' ', '0');
	}

	/**
	 * 获取格式化后的时间
	 */
	private static String getFormatDateStr(String dateStr) throws Exception {
		StringBuffer sb = new StringBuffer();
		// 时间部分
		String numPartStr = replaceDateStr(dateStr, "");
		if (StringUtils.isBlank(numPartStr) || numPartStr.length() < 6) {
			throw new IllegalArgumentException("错误的日期");
		}
		String datePartStr = replaceDateStr(dateStr, "-");
		if (numPartStr.contentEquals(datePartStr)) {
			if (datePartStr.length() == 8) {
				// 20160102
				sb.append(datePartStr.substring(0, 4)).append('-').append(datePartStr.substring(4, 6)).append('-')
						.append(datePartStr.substring(6, 8));
				datePartStr = sb.toString();
				sb.setLength(0);
			} else if (datePartStr.length() == 7) {
				datePartStr = checkStrIfIsDate(dateStr);
			}
		} else {
			if (datePartStr.indexOf("-") < 4) {
				throw new Exception("暂不支持此种格式");
			}
			if (datePartStr.indexOf("-") == datePartStr.lastIndexOf("-")) {
				// 只有一个-
				datePartStr = getRightDateStrIfPossible(datePartStr);
			}
		}
		return datePartStr;
	}

	/**
	 * 返回时间字符串(如果可能)
	 */
	private static String getRightDateStrIfPossible(String dateStr) throws Exception {
		String subStr = dateStr.substring(4);
		String[] subArr = subStr.split("-");
		if (subArr.length == 1) {
			throw new Exception("暂不支持此种格式");
		}
		StringBuffer sb = new StringBuffer();
		sb.append(dateStr.substring(0, 4)).append('-');// 年
		if (StringUtils.isBlank(subArr[0])) {
			if (subArr[1].length() == 4) {
				// 1213
				sb.append(subArr[1].substring(0, 2)).append("-").append(subArr[1].substring(2));
				return sb.toString();
			} else if (subArr[1].length() == 3) {
				// 012
				if (subArr[1].charAt(0) == '0') {
					sb.append(subArr[1].substring(0, 2)).append("-0").append(subArr[1].substring(2));
					return sb.toString();
				} else if (subArr[1].charAt(1) == '3') {
					// 130
					sb.append("0").append(subArr[1].charAt(0)).append("-").append(subArr[1].substring(1));
					return sb.toString();
				}
				throw new Exception("暂不支持此种格式");
			}
		}
		return null;
	}

	/**
	 * 检查是否是日期
	 */
	private static String checkStrIfIsDate(String dateStr) throws Exception {
		StringBuffer sb = new StringBuffer();
		if (dateStr.charAt(4) == '0') {
			// 2016011
			sb.append(dateStr.substring(0, 4)).append("-").append(dateStr.substring(4, 6)).append("-0")
					.append(dateStr.substring(6));
			return sb.toString();
		}
		if (dateStr.charAt(5) == '3' && Character.digit(dateStr.charAt(6), 10) <= 1) {
			// 2016131
			sb.append(dateStr.substring(0, 4)).append("-0").append(dateStr.substring(4, 5)).append("-")
					.append(dateStr.substring(5));
			return sb.toString();
		}
		throw new Exception("暂不支持此种格式");
	}

	/**
	 * 删除非法字符
	 */
	private static String replaceDateStr(String dateStr, String replaceToStr) {
		return dateStr.trim().replaceAll("\\D+", replaceToStr).trim();
	}

	/**
	 * 删除非法字符
	 */
	private static String replaceTimeStr(String timeStr) {
		return timeStr.trim().replaceAll("\\D+$", "").replaceAll("\\D+", ":").replaceAll("^:+", "")
				.replaceAll(":+$", "").trim();
	}

}
