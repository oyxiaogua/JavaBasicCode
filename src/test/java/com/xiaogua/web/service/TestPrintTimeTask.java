package com.xiaogua.web.service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.xiaogua.better.bean.Global_Count_Bean;
import com.xiaogua.better.datetime.DateTimeCode;

public class TestPrintTimeTask {
	private static final Logger logger = Logger.getLogger(TestPrintTimeTask.class);

	public void executeTask() throws Exception {
		int tmpCount = Global_Count_Bean.getAndIncrement();
		try {
			if (tmpCount == 0) {
				TimeUnit.SECONDS.sleep(13);
				logger.error(tmpCount + ",sleep 12s");
			}
		} catch (Exception e) {
			logger.error("sleep error", e);
		}
		String currentDateStr = DateTimeCode.getStrFromDate(new Date(), DateTimeCode.FULL_DATETIME);
		logger.error(tmpCount + "---------->" + currentDateStr);
	}

}
