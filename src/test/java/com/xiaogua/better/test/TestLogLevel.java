package com.xiaogua.better.test;

import java.util.Enumeration;

import org.junit.Test;
import org.slf4j.impl.StaticLoggerBinder;

public class TestLogLevel {

	@Test
	public void testPrintLogLevel() {
		String logType = StaticLoggerBinder.getSingleton().getLoggerFactoryClassStr();
		System.out.println(logType);

		if ("org.slf4j.impl.Log4jLoggerFactory".equals(logType)) {
			Enumeration<?> enumeration = org.apache.log4j.LogManager.getCurrentLoggers();
			while (enumeration.hasMoreElements()) {
				org.apache.log4j.Logger logger = (org.apache.log4j.Logger) enumeration.nextElement();
				if (logger.getLevel() != null) {
					System.out.println(logger.getName() + "," + logger.getLevel());
				}
			}
		}

	}
}
