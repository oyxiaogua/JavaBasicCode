package com.xiaogua.better.thread;

import java.util.Date;
import java.util.Random;

import com.xiaogua.better.datetime.DateTimeCode;

public class PrintSysDateThread implements Runnable {

	public void run() {
		System.err.println("------------current time=" + getSysDate());
	}

	private String getSysDate() {
		if (new Random().nextInt(10) >= 5) {
			System.err.println("------------begin throw exception");
			int a = 0;
			int b = 3 / a;
			System.out.println(b);
		}
		try {
			return DateTimeCode.getStrFromDate(new Date(), DateTimeCode.FULL_DATETIME);
		} catch (Exception e) {

		}
		return null;
	}

}
