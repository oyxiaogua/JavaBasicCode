package com.xiaogua.better.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class TestReadFileFromEnd {
	@Test
	public void testReadFileFromEnd() {
		String filePath = "e:/test_tmp/test_utff8_txt.txt";
		readFileFromEnd(filePath, "utf-8");
	}

	public static void readFileFromEnd(String filename, String charset) {
		RandomAccessFile rf = null;
		try {
			rf = new RandomAccessFile(filename, "r");
			long len = rf.length();
			long start = rf.getFilePointer();
			long nextend = start + len - 1;
			String line;
			rf.seek(nextend);
			int c = -1;
			while (nextend > start) {
				c = rf.read();
				if (c == '\n' || c == '\r') {
					line = rf.readLine();
					if (line != null) {
						System.out.println(new String(line.getBytes("ISO-8859-1"), charset));
					} else {
						System.out.println(line);
					}
					nextend--;
				}
				nextend--;
				rf.seek(nextend);
				if (nextend == 0) {// 当文件指针退至文件开始处，输出第一行
					System.out.println(new String(rf.readLine().getBytes("ISO-8859-1"), charset));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(rf);
		}
	}

}
