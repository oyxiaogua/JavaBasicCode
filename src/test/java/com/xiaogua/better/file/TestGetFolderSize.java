package com.xiaogua.better.file;

import org.junit.Test;
import org.springframework.util.StopWatch;

public class TestGetFolderSize {
	private GetFolderSize t = new GetFolderSize();
	private String folderStr = "e:/test_tmp/";

	@Test
	public void testGetFolderSizeUsingApacheCommonsIO() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start("testGetFolderSizeUsingApacheCommonsIO");
		long size = t.getFolderSizeUsingApacheCommonsIO(folderStr);
		stopWatch.stop();
		System.out.println(String.format("method=%s,result=%s,time=%s", "testGetFolderSizeUsingApacheCommonsIO", size,
				stopWatch.getTotalTimeMillis()));
	}

	@Test
	public void testGetFolderSizeUsingGuava() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start("testGetFolderSizeUsingGuava");
		long size = t.getFolderSizeUsingGuava(folderStr);
		stopWatch.stop();
		System.out.println(String.format("method=%s,result=%s,time=%s", "testGetFolderSizeUsingGuava", size,
				stopWatch.getTotalTimeMillis()));
	}

	@Test
	public void testGetFolderSizeUsingJava8() throws Exception {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start("testGetFolderSizeUsingJava8");
		long size = t.getFolderSizeUsingJava8(folderStr);
		stopWatch.stop();
		System.out.println(String.format("method=%s,result=%s,time=%s", "testGetFolderSizeUsingJava8", size,
				stopWatch.getTotalTimeMillis()));
	}

	@Test
	public void testGetFolderSizeUsingJava7() throws Exception {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start("testGetFolderSizeUsingJava8");
		long size = t.getFolderSizeUsingJava7(folderStr);
		stopWatch.stop();
		System.out.println(String.format("method=%s,result=%s,time=%s", "testGetFolderSizeUsingJava7", size,
				stopWatch.getTotalTimeMillis()));
	}

	@Test
	public void testGetFolderSizeWithRecursive() throws Exception {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start("testGetFolderSizeWithRecursive");
		long size = t.getFolderSizeUsingJava7(folderStr);
		stopWatch.stop();
		System.out.println(String.format("method=%s,result=%s,time=%s", "testGetFolderSizeWithRecursive", size,
				stopWatch.getTotalTimeMillis()));
	}
}
