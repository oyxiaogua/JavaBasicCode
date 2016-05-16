package com.xiaogua.better.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomUtils;

public class ProcessDataTask implements Callable<String> {
	private Map<String, String> dataMap;

	public ProcessDataTask(Map<String, String> dataMap) {
		super();
		this.dataMap = new HashMap<String, String>();
		this.dataMap.putAll(dataMap);
	}

	public String call() throws Exception {
		int randomSleep = RandomUtils.nextInt(1, 8);
		TimeUnit.SECONDS.sleep(randomSleep);
		if (randomSleep>=7) {
			throw new RuntimeException("exception msg");
		}
		return this.dataMap.get("id");
	}

}
