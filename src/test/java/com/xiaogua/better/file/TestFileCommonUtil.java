package com.xiaogua.better.file;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.List;

import org.junit.Test;

public class TestFileCommonUtil {
	@Test
	public void testGetReadableSize() {
		long size = 123456;
		System.out.println(FileCommonUtil.getReadableSize(size));
	}

	@Test
	public void testGetFileTotalLine() throws Exception {
		String filePath = "e:/test_tmp/test_state_xml.xml";
		int result = FileCommonUtil.getFileTotalLine(filePath);
		System.out.println(result);

		LineNumberReader lnr = new LineNumberReader(new FileReader(new File(filePath)));
		lnr.skip(Long.MAX_VALUE);
		System.out.println(lnr.getLineNumber());
		lnr.close();
	}

	@Test
	public void testGetFileLastLine() throws Exception {
		String filePath = "e:/test_tmp/12345.txt";
		String result = FileCommonUtil.getFileLastLine(filePath);
		System.out.println(new String(result.getBytes("ISO-8859-1"), "UTF-8"));
	}

	@Test
	public void testFastCreateBigFile() throws Exception {
		String filePath = "e:/test_tmp/123456.txt";
		FileCommonUtil.fastCreateBigFile(filePath, 1234 * 1024);
	}

	@Test
	public void testGetFileListByDFS() throws Exception {
		List<File> fileList = FileCommonUtil.getFileListByDFS(new File("e:/test_tmp/ftp"));
		for (File file : fileList) {
			System.out.println(file.getCanonicalPath());
		}
	}

	@Test
	public void testGetFileListByBFS() throws Exception {
		List<File> fileList = FileCommonUtil.getFileListByBFS(new File("e:/test_tmp/ftp"));
		for (File file : fileList) {
			System.out.println(file.getCanonicalPath());
		}
	}
}
