package com.xiaogua.better.file;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class TestZipFileCode {
	@Test
	public void testReadZipWithJava() throws Exception {
		String filePath = "E:/test_tmp/11.zip";
		Charset gbkCharset = Charset.forName("GBK");
		java.util.zip.ZipFile zip = new java.util.zip.ZipFile(new File(filePath), gbkCharset);
		Enumeration<? extends java.util.zip.ZipEntry> enumeration = zip.entries();
		java.util.zip.ZipEntry zipEntry = null;
		InputStream in = null;
		while (enumeration.hasMoreElements()) {
			zipEntry = enumeration.nextElement();
			System.out.println(zipEntry.getName());
			if (!zipEntry.isDirectory()) {
				in = zip.getInputStream(zipEntry);
				printFileContent(in, gbkCharset);
				IOUtils.closeQuietly(in);
			}

		}
		zip.close();
	}

	private void printFileContent(InputStream in, Charset gbkCharset) throws Exception {
		List<String> contentList = IOUtils.readLines(in, gbkCharset);
		for (String str : contentList) {
			System.out.println(str);
		}
	}
}
