package com.xiaogua.better.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;

public class TestReadContentWithTimeOut {

	@Test
	public void testGetStreamAsStringWithTimeOut() throws Exception {
		File file = new File("E:/test_tmp/his/test_gbk.txt");
		InputStream in = new FileInputStream(file);
		long startTime=System.currentTimeMillis();
		String content = ReadContentWithTimeOut.getStreamAsStringWithTimeOut(in, "GBK", 2000);
		System.out.println(content);
		in.close();
		System.out.println("cost time="+(System.currentTimeMillis()-startTime));

	}
}
