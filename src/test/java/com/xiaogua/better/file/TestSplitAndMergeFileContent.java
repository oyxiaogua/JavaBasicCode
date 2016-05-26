package com.xiaogua.better.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

public class TestSplitAndMergeFileContent {
	@Test
	public void testGenerateFile() throws Exception {
		char[] randomCharArr = "测试中文编码拆分文件完成后再合并".toCharArray();
		BufferedWriter bw = FileCommonUtil.getBufferedWriter("e:/test_tmp/sys_09887765467.txt", "gbk");
		for (int i = 1; i < 1000; i++) {
			bw.write(RandomStringUtils.random(4, randomCharArr));
			bw.write("|");
			bw.write(Integer.toString(i));
			bw.newLine();
		}
		bw.close();
	}

	@Test
	public void testSplitFile() throws Exception {
		String filePath = "e:/test_tmp/sys_09887765467.txt";
		SplitAndMergeFileContent.splitFileContent(filePath, "e:/test_tmp/525", "utf-8", "gbk", 200, 200);
	}

	@Test
	public void testMergeFileContent() throws Exception {
		String filePath = "e:/test_tmp/525";
		SplitAndMergeFileContent.mergeFileContent(filePath, "e:/test_tmp/526/result.txt", "gbk", "utf-8");
	}

	@Test
	public void testReadFileContent() throws Exception {
		String filePath = "e:/test_tmp/525/sys_09887765467_1.txt";
		String content = IOUtils.toString(new FileInputStream(new File(filePath)), "gbk");
		System.out.println(content);
	}
}
