package com.xiaogua.better.datetime;

import java.sql.Timestamp;
import java.util.TimeZone;

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

}
