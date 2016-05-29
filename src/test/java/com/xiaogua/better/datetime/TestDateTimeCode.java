package com.xiaogua.better.datetime;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.quartz.CronExpression;

public class TestDateTimeCode {
	private static String fullDateFormatStr = "yyyy-MM-dd HH:mm:ss";
	private static DateFormat fullDateFormat = new SimpleDateFormat(fullDateFormatStr);

	@Test
	public void testgetUTCTimeStr() {
		String rtnStr = DateTimeCode.getUTCTimeStr();
		System.out.println(rtnStr);
	}

	@Test
	public void testGetWeekOfDate() {
		String rtnStr = DateTimeCode.getWeekOfDate(new Date());
		System.out.println(rtnStr);
	}

	@Test
	public void testTruncate() {
		Date date = DateTimeCode.truncate(new Date());
		String rtnStr = fullDateFormat.format(date);
		System.out.println(rtnStr);
		Assert.assertTrue(rtnStr.indexOf("00:00:00") > 0);
	}

	@Test
	public void testTransDateStr() {
		String str = "2014-08-25 00:00:00";
		String rtnStr = DateTimeCode.transDateStr(str);
		System.out.println(rtnStr);
		Assert.assertEquals("2014-08-25T00:00:00Z", rtnStr);
	}

	@Test
	public void testTransDateCommonStr() {
		String str = "2014-08-25T00:00:00Z";
		String rtnStr = DateTimeCode.transToCommonDateStr(str);
		System.out.println(rtnStr);
		Assert.assertEquals("2014-08-25 00:00:00", rtnStr);
	}

	@Test
	public void testGetMaxDayOfMonth() {
		int intRtn = DateTimeCode.getMaxDayOfMonth(2015, 3);
		Assert.assertEquals(31, intRtn);
	}

	@Test
	public void testIsWeekend() {
		boolean isWeekend = DateTimeCode.isWeekend(new Date());
		System.out.println(isWeekend);
	}

	@Test
	public void testMJD() {
		double mjdRtn = DateTimeCode.dateToMJD(new Date());
		System.out.println(mjdRtn);
		Date date = DateTimeCode.mjdToDate(mjdRtn);
		String rtnStr = fullDateFormat.format(date);
		System.out.println(rtnStr);
	}

	@Test
	public void testXMLGregorianCalendar() {
		XMLGregorianCalendar xmlCalendar = DateTimeCode.dateToXmlGregorianCalendar(new Date());
		System.out.println(xmlCalendar.toXMLFormat());
		Date date = DateTimeCode.xmlGregorianCalendarToDate(xmlCalendar);
		String rtnStr = fullDateFormat.format(date);
		System.out.println(rtnStr);
	}

	@Test
	public void testParseDateTime() {
		Date date = DateTimeCode.parseDateTime("2015-11-12");
		String rtnStr = fullDateFormat.format(date);
		System.out.println(rtnStr);
	}

	@Test
	public void testTruncateDate() {
		Date date = DateTimeCode.truncateDate(new Date());
		String rtnStr = fullDateFormat.format(date);
		System.out.println(rtnStr);
	}

	@Test
	public void testAddDateToStr() throws Exception {
		String dateStr = "2015-1-1 2:3:4";
		String rtnDateStr = DateTimeCode.addDateToStr(dateStr, fullDateFormatStr, Calendar.DAY_OF_MONTH, 3);
		Assert.assertEquals("2015-01-04 02:03:04", rtnDateStr);
	}

	@Test
	public void testGregorianCalendarFormat() {
		GregorianCalendar calendar = new GregorianCalendar();
		String strDate = DateFormat.getDateTimeInstance().format(calendar.getTime());
		System.out.println(strDate);
	}

	@Test
	public void testDateFormatUnExpectValue() throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = "2016-01-2019 23:49:25";
		Date date = df.parse(dateStr);
		String formatStr = fullDateFormat.format(date);
		System.out.println(formatStr);
		Assert.assertEquals("2021-07-11 00:00:00", formatStr);
	}

	@Test(expected = ParseException.class)
	public void testDateFormatLenient() throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		df.setLenient(false);// 严格匹配
		String dateStr = "2016-01-2019 23:49:25";
		df.parse(dateStr);
	}

	@Test
	public void testJavaUtilSqlDateDiff() {
		java.util.Date utilDate = new java.util.Date();// 有时间日期
		java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());// 只有是日期
		System.out.println(utilDate);
		System.out.println(sqlDate);
	}

	@Test
	public void testGetFormatDateTimeStr() throws Exception {
		String dateStr = "2016年1_2";
		String formatDateStr = null;
		formatDateStr = DateTimeCode.getFormatDateTimeStr(dateStr, "yyyy_MM_dd");
		Assert.assertEquals("2016_01_02", formatDateStr);

		dateStr = "2016_1112";
		formatDateStr = DateTimeCode.getFormatDateTimeStr(dateStr, "yyyy_MM_dd");
		Assert.assertEquals("2016_11_12", formatDateStr);

		dateStr = "2016_012";
		formatDateStr = DateTimeCode.getFormatDateTimeStr(dateStr, "yyyy_MM_dd");
		Assert.assertEquals("2016_01_02", formatDateStr);

		dateStr = "2016_130";
		formatDateStr = DateTimeCode.getFormatDateTimeStr(dateStr, "yyyy_MM_dd");
		Assert.assertEquals("2016_01_30", formatDateStr);

		dateStr = "2016131";
		formatDateStr = DateTimeCode.getFormatDateTimeStr(dateStr, "yyyy_MM_dd");
		Assert.assertEquals("2016_01_31", formatDateStr);

		dateStr = "2016012";
		formatDateStr = DateTimeCode.getFormatDateTimeStr(dateStr, "yyyy_MM_dd");
		Assert.assertEquals("2016_01_02", formatDateStr);

		dateStr = "2016-1-2";
		formatDateStr = DateTimeCode.getFormatDateTimeStr(dateStr, "yyyy_MM_dd");
		Assert.assertEquals("2016_01_02", formatDateStr);

		dateStr = "2016年1_2日3时2:3";
		formatDateStr = DateTimeCode.getFormatDateTimeStr(dateStr, fullDateFormatStr);
		Assert.assertEquals("2016-01-02 03:02:03", formatDateStr);

		dateStr = "2016年1_2  3时2:3";
		formatDateStr = DateTimeCode.getFormatDateTimeStr(dateStr, fullDateFormatStr);
		Assert.assertEquals("2016-01-02 03:02:03", formatDateStr);

		dateStr = "  2016年1月2日    3时2:3秒 ";
		formatDateStr = DateTimeCode.getFormatDateTimeStr(dateStr, fullDateFormatStr);
		Assert.assertEquals("2016-01-02 03:02:03", formatDateStr);

		dateStr = "  2016-1-2 :::3:2:3::: ";
		formatDateStr = DateTimeCode.getFormatDateTimeStr(dateStr, fullDateFormatStr);
		Assert.assertEquals("2016-01-02 03:02:03", formatDateStr);
	}

	@Test
	public void testGetFormatTimeStr() throws Exception {
		String dateStr = "121314";
		String formatTimeStr = null;
		formatTimeStr = DateTimeCode.getFormatTimeStr(dateStr);
		Assert.assertEquals("12:13:14", formatTimeStr);

		dateStr = "1";
		formatTimeStr = DateTimeCode.getFormatTimeStr(dateStr);
		Assert.assertEquals("01:00:00", formatTimeStr);

		dateStr = "1:2:";
		formatTimeStr = DateTimeCode.getFormatTimeStr(dateStr);
		Assert.assertEquals("01:02:00", formatTimeStr);

		dateStr = "1:2:30";
		formatTimeStr = DateTimeCode.getFormatTimeStr(dateStr);
		Assert.assertEquals("01:02:30", formatTimeStr);

		dateStr = "23:3";
		formatTimeStr = DateTimeCode.getFormatTimeStr(dateStr);
		Assert.assertEquals("23:03:00", formatTimeStr);

		dateStr = "24:3";
		formatTimeStr = DateTimeCode.getFormatTimeStr(dateStr);
		Assert.assertEquals("02:04:03", formatTimeStr);

		dateStr = "241:3";
		formatTimeStr = DateTimeCode.getFormatTimeStr(dateStr);
		Assert.assertEquals("02:41:03", formatTimeStr);

		dateStr = "2:586";
		formatTimeStr = DateTimeCode.getFormatTimeStr(dateStr);
		Assert.assertEquals("02:58:06", formatTimeStr);

		dateStr = "24";
		formatTimeStr = DateTimeCode.getFormatTimeStr(dateStr);
		Assert.assertEquals("02:04:00", formatTimeStr);

		dateStr = "241";
		formatTimeStr = DateTimeCode.getFormatTimeStr(dateStr);
		Assert.assertEquals("02:41:00", formatTimeStr);

		dateStr = "24123";
		formatTimeStr = DateTimeCode.getFormatTimeStr(dateStr);
		Assert.assertEquals("02:41:23", formatTimeStr);

		dateStr = "16602";
		formatTimeStr = DateTimeCode.getFormatTimeStr(dateStr);
		Assert.assertEquals("16:06:02", formatTimeStr);

		dateStr = "23461";
		formatTimeStr = DateTimeCode.getFormatTimeStr(dateStr);
		Assert.assertEquals("23:46:01", formatTimeStr);

		dateStr = "234611";
		formatTimeStr = DateTimeCode.getFormatTimeStr(dateStr);
		Assert.assertEquals("23:46:11", formatTimeStr);
	}

	@Test
	public void testFormatEnglishDate() {
		// Fri Jan 02 04:05:06 CST 2015
		DateFormat df = new SimpleDateFormat(DateTimeCode.DATE_DEFAULT_FORMAT, Locale.ENGLISH);
		String dateStr = df.format(new Date());
		System.out.println(dateStr);
	}

	@Test
	public void testGetIntervalPeriods() throws Exception {
		String startDateStr = "2016-12-31 3:4:5";
		String endDateStr = "2017-1-1 23:3:5";
		List<String> rtnList = DateTimeCode.getIntervalPeriods(startDateStr, DateTimeCode.FULL_DATETIME, endDateStr,
				DateTimeCode.FULL_DATETIME, Enum_Date_Dimension.DAY);
		System.out.println(rtnList);
	}

	@Test
	public void testGetLastMonthDateStr() {
		String dateStr = DateTimeCode.getLastMonthDateStr(new Date(), 2);
		System.out.println(dateStr);
	}

	@Test
	public void testGetFragmentInDays() {
		long rtn = org.apache.commons.lang3.time.DateUtils.getFragmentInDays(new Date(), Calendar.YEAR);
		System.out.println(rtn);
	}

	@Test
	public void testConvertDateTimestampLong() throws Exception {
		long timeStamp = DateTimeCode.getTimestampLongFromDate(new Date());
		System.out.println(timeStamp);

		String dateStr = DateTimeCode.convertTimeStampToDateStr(timeStamp, DateTimeCode.FULL_DATETIME);
		System.out.println(dateStr);
	}

	@Test
	public void testConvertTimestampString() throws Exception {
		Timestamp timeStamp = DateTimeCode.convertDateToTimeStamp(new Date());
		String dateStr = DateTimeCode.convertTimeStampToDateStr(timeStamp, DateTimeCode.FULL_DATETIME);
		System.out.println(dateStr);

		Timestamp timeStamp2 = DateTimeCode.convertDateStrToTimeStamp(dateStr);
		System.out.println(timeStamp2);

		dateStr = DateTimeCode.getStrFromDate(new Date(), DateTimeCode.NORM_DATETIME_MS_PATTERN);
		System.out.println(dateStr);

		timeStamp2 = DateTimeCode.convertDateStrToTimeStamp(dateStr);
		System.out.println(timeStamp2);
	}

	@Test
	public void testIsEndOfTheMonth() throws Exception {
		Date date = DateTimeCode.getDateFromStr("2016-5-31", DateTimeCode.YYYY_MM_DD);
		boolean booeleanRtn = DateTimeCode.isEndOfTheMonth(date);
		Assert.assertTrue(booeleanRtn);

		date = DateTimeCode.getDateFromStr("2016-2-29", DateTimeCode.YYYY_MM_DD);
		booeleanRtn = DateTimeCode.isEndOfTheMonth(date);
		Assert.assertTrue(booeleanRtn);
	}

	@Test
	public void testIsEndOfTheYear() throws Exception {
		Date date = DateTimeCode.getDateFromStr("2016-12-31", DateTimeCode.YYYY_MM_DD);
		boolean booeleanRtn = DateTimeCode.isEndOfTheYear(date);
		Assert.assertTrue(booeleanRtn);

		date = DateTimeCode.getDateFromStr("2015-12-31", DateTimeCode.YYYY_MM_DD);
		booeleanRtn = DateTimeCode.isEndOfTheYear(date);
		Assert.assertTrue(booeleanRtn);

		date = DateTimeCode.getDateFromStr("2015-12-30", DateTimeCode.YYYY_MM_DD);
		booeleanRtn = DateTimeCode.isEndOfTheYear(date);
		Assert.assertFalse(booeleanRtn);
	}

	@Test
	public void testDateFormatUtilsFormat() {
		String dateStr = DateFormatUtils.format(System.currentTimeMillis(), DateTimeCode.FULL_DATETIME);
		System.out.println(dateStr);
	}

	@Test
	public void testJodaDateTime() {
		Date firstDate = new DateTime().withTimeAtStartOfDay().toDate();
		System.out.println(DateFormatUtils.format(firstDate, "yyyy-MM-dd HH:mm:ss"));

		Date lastDate = new DateTime().withTime(23, 59, 59, 0).toDate();
		System.out.println(DateFormatUtils.format(lastDate, "yyyy-MM-dd HH:mm:ss"));

		Date past10day = new DateTime().withTime(12, 0, 0, 0).plusMonths(1).minusDays(10).toDate();
		System.out.println(DateFormatUtils.format(past10day, "yyyy-MM-dd HH:mm:ss"));

		DateTime now = new DateTime();
		// 位于本周第几天
		System.out.println(now.getDayOfWeek());
		// 位于本月第几天
		System.out.println(now.getDayOfMonth());
		// 位于本年第几天
		System.out.println(now.getDayOfYear());

		// 几点
		System.out.println(now.getHourOfDay());
		// 几分
		System.out.println(now.getMinuteOfHour());
		// 几秒
		System.out.println(now.getSecondOfMinute());
	}

	@Test
	public void testConvertDateToCronExpression() {
		Date date = new Date();
		String cronStr = DateTimeCode.convertDateToCronExpression(date);
		Assert.assertTrue(CronExpression.isValidExpression(cronStr));
	}

	@Test
	public void testDateFormatParsePosition() {
		SimpleDateFormat df = new SimpleDateFormat(DateTimeCode.FULL_DATETIME, Locale.ENGLISH);
		// 跳过T开始解析
		ParsePosition pos = new ParsePosition(1);
		String dateStr = "T2015-1-2 3:4:5";
		Date date = df.parse(dateStr, pos);
		Assert.assertEquals(-1, pos.getErrorIndex());
		// 成功转换后ParsePosition.getIndex()就是匹配的字符串结尾的索引
		Assert.assertEquals(dateStr.length(), pos.getIndex());
		System.out.println(DateFormatUtils.format(date, DateTimeCode.FULL_DATETIME));

		dateStr = "T2015-1-2";
		date = df.parse(dateStr, pos);
		Assert.assertNull(date);
		Assert.assertTrue(pos.getErrorIndex() > 0);
		System.out.println(pos.getIndex() + "," + pos.getErrorIndex() + "," + DateTimeCode.FULL_DATETIME.length());
	}

	@Test
	public void testDateFormatFieldPosition() {
		// 字段位置
		FieldPosition fieldPosition = new FieldPosition(DateFormat.HOUR_OF_DAY0_FIELD);
		SimpleDateFormat df = new SimpleDateFormat(DateTimeCode.FULL_DATETIME, Locale.ENGLISH);

		StringBuffer sb = new StringBuffer();
		String dateStr = df.format(new Date(), sb, fieldPosition).toString();
		System.out.println(dateStr);
		System.out.println(fieldPosition.getBeginIndex() + "," + fieldPosition.getEndIndex());
		System.out.println(dateStr.substring(fieldPosition.getBeginIndex(), fieldPosition.getEndIndex()));
	}

}
