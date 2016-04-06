package com.xiaogua.better.datetime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

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

		dateStr = "2016_12";
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

		dateStr = "2016年1_2日3时2:3";
		formatDateStr = DateTimeCode.getFormatDateTimeStr(dateStr, fullDateFormatStr);
		Assert.assertEquals("2016-01-02 03:02:03", formatDateStr);

		dateStr = "2016年1_2  3时2:3";
		formatDateStr = DateTimeCode.getFormatDateTimeStr(dateStr, fullDateFormatStr);
		Assert.assertEquals("2016-01-02 03:02:03", formatDateStr);

		dateStr = "  2016年1月2日    3时2:3秒 ";
		formatDateStr = DateTimeCode.getFormatDateTimeStr(dateStr, fullDateFormatStr);
		Assert.assertEquals("2016-01-02 03:02:03", formatDateStr);

		dateStr = "  2016-1-2 3:2:3 ";
		formatDateStr = DateTimeCode.getFormatDateTimeStr(dateStr, fullDateFormatStr);
		Assert.assertEquals("2016-01-02 03:02:03", formatDateStr);
	}
}
