package com.xiaogua.better.thread;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.xiaogua.better.file.FileCommonUtil;

public class TestProcessFile {
	private final Logger log = Logger.getLogger(TestProcessFile.class);

	@Test
	public void testGetFolderSize() throws Exception {
		File file = new File("e:/test_tmp/0524/");
		String suffixStr = "1_";
		File[] fileArr = findFileWithPrefixStr(file, suffixStr);
		long totalNum = 0;
		long singleSize = 0;
		for (File file2 : fileArr) {
			singleSize = FileCommonUtil.getFileDataSize(file2, "UTF-8");
			log.error(
					String.format("fileName=%s,fileSize=%s,fileLine=%s", file2.getName(), file2.length(), singleSize));
			totalNum += singleSize;
		}
		log.error("total line=" + totalNum);
	}

	@Test
	public void testProcessFile() throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		Interface_ProcessLine processClz = new ProcessLineImpl();
		for (int i = 1; i < 20; i++) {
			executor.submit(new ProcessFileTask("e:/test_tmp/test_data/", "test_data_" + i + ".txt", "e:/test_tmp/0524",
					"UTF-8", "UTF-8", processClz, 100000));
		}
		TimeUnit.SECONDS.sleep(25);
		executor.shutdown();
	}

	@Test
	public void testGenerateData() throws Exception {
		BufferedWriter writer = null;
		StringBuilder sb = new StringBuilder(128);
		int fileNum = 0;
		int totalNum = 0;
		for (int i = 1; i < 20; i++) {
			fileNum = 0;
			writer = FileCommonUtil.getBufferedWriter("e:/test_tmp/test_data/test_data_" + i + ".txt", "UTF-8", true);
			for (int j = 0; j < 8000; j++) {
				sb.setLength(0);
				sb.append(RandomStringUtils.randomNumeric(11)).append("|")
						.append(RandomStringUtils.randomAlphanumeric(18)).append("|")
						.append(RandomStringUtils.randomAlphabetic(30)).append("|");
				if (j % 100 == 0) {
					sb.append("0");
				} else {
					sb.append("1");
					fileNum++;
					totalNum++;
				}
				writer.append(sb.toString());
				writer.newLine();
			}
			writer.close();
			log.error(
					String.format("fileName=%s,fileLine=%s", "e:/test_tmp/test_data/test_data_" + i + ".txt", fileNum));
		}
		log.error("total line=" + totalNum);
	}

	public File[] findFileWithPrefixStr(File file, final String prefix) {
		File[] rtnArr = file.listFiles(new FileFilter() {
			public boolean accept(File subFile) {
				String fileNameStr = subFile.getName();
				return fileNameStr.startsWith(prefix);
			}
		});
		return rtnArr;
	}

	public File[] findFileWithSuffixStr(File file, final String suffixStr) {
		File[] rtnArr = file.listFiles(new FileFilter() {
			public boolean accept(File subFile) {
				String fileNameStr = subFile.getName();
				if (suffixStr.indexOf(".") > 0 && fileNameStr.endsWith(suffixStr)) {
					return true;
				}
				if (fileNameStr.lastIndexOf(".") > 0) {
					fileNameStr = fileNameStr.substring(0, fileNameStr.lastIndexOf("."));
				}
				if (fileNameStr.endsWith(suffixStr)) {
					return true;
				}
				return false;
			}
		});
		return rtnArr;
	}

}
