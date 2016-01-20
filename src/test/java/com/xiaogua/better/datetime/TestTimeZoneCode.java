package com.xiaogua.better.datetime;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestTimeZoneCode {
	private static DateFormat fullDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Before
	public void resetTimeZone() {
		fullDateFormat.setTimeZone(TimeZone.getDefault());
	}

	@Test
	public void testParseDateStr() throws Exception {
		String dateStr = "Wed Jan 20 19:06:47 CST 2016";
		DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
		String fullStr = fullDateFormat.format(df.parse(dateStr));
		System.out.println(fullStr);
		Assert.assertNotNull(fullStr);
	}

	@Test
	public void testGetCurrentTimeZoneValue() {
		int timeZone = TimeZoneCode.getCurrentTimeZoneValue();
		Assert.assertEquals(8, timeZone);
	}

	@Test
	public void testGetChinaTimestamp() {
		Timestamp timeStamp = TimeZoneCode.getChinaTimestamp();
		System.out.println(fullDateFormat.format(timeStamp));
	}

	@Test
	public void testGetAngelesCurrentDate() {
		fullDateFormat.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
		System.out.println("Los_Angeles :" + fullDateFormat.format(new Date()));
	}

}
