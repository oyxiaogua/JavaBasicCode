package com.xiaogua.better.datetime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class TestDateTimeCode {
	private static DateFormat fullDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
	public void testMJD(){
		double mjdRtn=DateTimeCode.dateToMJD(new Date());
		System.out.println(mjdRtn);
		Date date=DateTimeCode.mjdToDate(mjdRtn);
		String rtnStr = fullDateFormat.format(date);
		System.out.println(rtnStr);
	}
}
