package com.xiaogua.better.file;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.base.Stopwatch;

public class TestReadContentWithLineNum {
	private String filePath = "e:/test_tmp/big_csv.csv";
	private String encoding = "utf-8";
	private int lineNum = 1980065;

	@Test
	public void testReadFileByLineNumWithJava8() throws Exception {
		Stopwatch stopwatch = Stopwatch.createStarted();
		String content = ReadContentWithLineNum.readFileByLineNumWithJava8(filePath, encoding, lineNum);
		stopwatch.stop();
		long millis = stopwatch.elapsed(TimeUnit.MILLISECONDS);
		System.out.println("file content:" + content);
		System.out.println("method readFileByLineNumWithJava8 cost time=" + millis);
	}

	@Test
	public void testReadFileByLineWithBufferedReader() throws Exception {
		Stopwatch stopwatch = Stopwatch.createStarted();
		String content = ReadContentWithLineNum.readFileByLineWithBufferedReader(filePath, encoding, lineNum);
		stopwatch.stop();
		long millis = stopwatch.elapsed(TimeUnit.MILLISECONDS);
		System.out.println("file content:" + content);
		System.out.println("method readFileByLineNumWithJava8 cost time=" + millis);
	}
}
