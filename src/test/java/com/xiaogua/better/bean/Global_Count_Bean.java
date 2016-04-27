package com.xiaogua.better.bean;

import java.util.concurrent.atomic.AtomicInteger;

public class Global_Count_Bean {
	public static AtomicInteger printTime = new AtomicInteger(0);

	public static int getAndIncrement() {
		return printTime.getAndIncrement();
	}

}
