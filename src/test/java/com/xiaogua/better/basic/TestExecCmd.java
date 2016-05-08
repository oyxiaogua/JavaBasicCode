package com.xiaogua.better.basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.junit.Test;

public class TestExecCmd {
	private static Logger logger = Logger.getLogger(TestExecCmd.class);

	@Test
	public void testJavaExecCmd() throws Exception {
		// 禁止执行本地进程
		// jdk.lang.Process.allowAmbigousCommands=false
		String cmd = "calc";
		Runtime run = Runtime.getRuntime();
		Process p = run.exec(cmd);
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		String str = null;
		while ((str = stdInput.readLine()) != null) {
			logger.info(str);
		}
		while ((str = stdError.readLine()) != null) {
			logger.error(str);
		}
		if (p.waitFor() != 0) {
			// 0表示正常结束，1：非正常结束
			if (p.exitValue() == 1)
				logger.error("命令执行失败!");
		}
		stdError.close();
		stdInput.close();

	}
}
