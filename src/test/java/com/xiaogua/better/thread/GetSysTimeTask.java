package com.xiaogua.better.thread;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.xiaogua.better.datetime.DateTimeCode;

public class GetSysTimeTask implements Callable<String> {
	private static Logger log = Logger.getLogger(GetSysTimeTask.class);
	private int sleepMills;
	private boolean isThrowException;

	public GetSysTimeTask(int sleepMills, boolean isThrowException) {
		super();
		this.sleepMills = sleepMills;
		this.isThrowException = isThrowException;
	}

	public String call() throws Exception {
		return getSysDate();
	}

	public void run() {
		log.error("------>" + getSysDate());
	}

	private String getSysDate() {
		if (isThrowException) {
			throw new RuntimeException("exception msg");
		}
		try {
			TimeUnit.MILLISECONDS.sleep(sleepMills);
			return DateTimeCode.getStrFromDate(new Date(), DateTimeCode.FULL_DATETIME);
		} catch (Exception e) {

		}
		return null;
	}

}
