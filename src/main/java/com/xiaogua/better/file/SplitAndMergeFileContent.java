package com.xiaogua.better.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

public class SplitAndMergeFileContent {

	/**
	 * 按行数分割文件
	 */
	public static void splitFileContent(String filePath, String destFolder, String fileEncoding,
			String destFileEncoding, int fileLine, int startLine) throws Exception {
		if (filePath == null || fileLine < 1) {
			return;
		}
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			return;
		}
		destFolder = destFolder.replace("\\", "/");
		if (!destFolder.endsWith("/")) {
			destFolder = destFolder + "/";
		}
		file = new File(destFolder);
		if (!file.exists()) {
			file.mkdirs();
		}
		String fileName = FilenameUtils.getBaseName(filePath);
		String fileExt = "";
		if (filePath.indexOf(".") > 0) {
			fileExt = "." + FilenameUtils.getExtension(filePath);
		}
		BufferedReader br = null;
		BufferedWriter bw = null;
		int fileNum = 1;
		String destFilePath = null;
		String str = null;
		int currentLine = 0;
		int ignoreLine = 0;
		boolean isWrite = false;
		try {
			br = FileCommonUtil.getBufferedReader(filePath, fileEncoding);
			destFilePath = destFolder + fileName + "_" + fileNum + fileExt;
			bw = FileCommonUtil.getBufferedWriter(destFilePath, destFileEncoding, true);
			while ((str = br.readLine()) != null) {
				ignoreLine++;
				if (ignoreLine <= startLine) {
					continue;
				}
				if (ignoreLine == 1) {
					str = str.replace("\uFEFF", "");
				}
				currentLine++;
				bw.write(str);
				if (currentLine % fileLine == 0) {
					bw.close();
					fileNum++;
					destFilePath = destFolder + fileName + "_" + fileNum + fileExt;
					bw = FileCommonUtil.getBufferedWriter(destFilePath, destFileEncoding, true);
				} else {
					bw.newLine();
				}
				isWrite = true;
			}
		} finally {
			IOUtils.closeQuietly(bw);
			IOUtils.closeQuietly(br);
			if (!isWrite) {
				File destFile = new File(destFilePath);
				destFile.delete();
			}
		}
	}

	/**
	 * 合并文件
	 */
	public static void mergeFileContent(String filePath, String destFilePath, String fileEncoding,
			String destFileEncoding) throws Exception {
		if (filePath == null) {
			return;
		}
		File file = new File(filePath);
		if (!file.exists() || !file.isDirectory()) {
			return;
		}
		File destFile = new File(destFilePath);
		if (destFile.exists() && !destFile.isFile()) {
			return;
		}
		if (!destFile.getParentFile().exists()) {
			destFile.getParentFile().mkdirs();
		}

		File[] fileArr = file.listFiles();
		BufferedReader br = null;
		BufferedWriter bw = null;
		String str = null;
		try {
			bw = FileCommonUtil.getBufferedWriter(destFilePath, destFileEncoding, true);
			for (File file2 : fileArr) {
				br = FileCommonUtil.getBufferedReader(file2, fileEncoding);
				while ((str = br.readLine()) != null) {
					bw.write(str);
					bw.newLine();
				}
				br.close();
			}
		} finally {
			IOUtils.closeQuietly(bw);
			IOUtils.closeQuietly(br);
		}
	}

}
