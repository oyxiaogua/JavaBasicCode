package com.xiaogua.better.thread;

import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;

import com.xiaogua.better.file.FileCommonUtil;
import com.xiaogua.better.str.StringCommonUtils;

public class ProcessFileTask  implements Runnable  {
	private final Logger log = Logger.getLogger(ProcessFileTask.class);
	private final String symbol = "_";
	private final int querySize = 100;
	private final int retryTime = 100;// 重试次数
	private String randomPrefix;// 文件前缀, 避免重复文件名
	private String srcFolder;// 源文件夹
	private String filePrefix;// 文件前缀
	private String destFolder;// 目标文件夹
	private String srcFileEncoding = "utf-8";
	private String destFileEncoding = "utf-8";
	private Interface_ProcessLine processClz;
	private long fileMaxSize;

	public ProcessFileTask(String srcFolder, String filePrefix, String destFolder, String srcFileEncoding,
			String destFileEncoding, Interface_ProcessLine processClz, long fileMaxSize) {
		super();
		this.srcFolder = srcFolder;
		this.filePrefix = filePrefix;
		this.destFolder = destFolder;
		randomPrefix = Thread.currentThread().getId() + symbol + RandomStringUtils.randomAlphanumeric(4) + symbol;
		if (StringCommonUtils.isNotBlank(srcFileEncoding)) {
			this.srcFileEncoding = srcFileEncoding;
		}
		if (StringCommonUtils.isNotBlank(destFileEncoding)) {
			this.destFileEncoding = destFileEncoding;
		}
		this.processClz = processClz;
		this.fileMaxSize = fileMaxSize;
	}

	public void run() {
		File srcFile = new File(srcFolder);
		if (!srcFile.exists() || !srcFile.isDirectory()) {
			log.error(srcFolder + " not folder");
			return;
		}
		if (!this.srcFolder.endsWith("/")) {
			this.srcFolder = this.srcFolder + "/";
		}
		File destFile = new File(destFolder);
		if (destFile.exists() && !destFile.isDirectory()) {
			log.error(destFolder + " not folder");
			return;
		}
		if (!this.destFolder.endsWith("/")) {
			this.destFolder = this.destFolder + "/";
		}
		List<String> filePathList = ProcessFileCommonCode.getFolderFileList(srcFile, filePrefix);
		if (filePathList.size() == 0) {
			log.error(destFolder + " not file found");
			return;
		}
		if (!destFile.exists()) {
			destFile.mkdirs();
		}
		for (String filePath : filePathList) {
			processFile(filePath);
		}
		ProcessFileCommonCode.closeBufferWriter(bufferMap);
		ProcessFileCommonCode.clearMap(bufferMap, pathMap, dataMap);
	}

	private Map<String, BufferedWriter> bufferMap = new HashMap<String, BufferedWriter>(64);
	private Map<String, String> pathMap = new HashMap<String, String>(64);
	private Map<String, List<String>> dataMap = new HashMap<String, List<String>>(64);// 身份证维度数据

	public void processFile(String filePath) {
		File file = new File(this.srcFolder + filePath);
		int startIndex = 1;
		int endIndex = (startIndex > 1 ? startIndex - 1 : 0) + querySize;
		int totalSize = 0;
		List<String> contentList = null;
		try {
			while (true) {
				contentList = FileCommonUtil.getRangeDataList(file, startIndex, endIndex, false, srcFileEncoding);
				if (contentList == null || contentList.size() == 0) {
					break;
				}
				processFileContent(filePath, contentList, startIndex, endIndex);
				totalSize += contentList.size();
				startIndex = endIndex + 1;
				endIndex = endIndex + querySize;
				contentList.clear();
			}
		} catch (Exception e) {
			log.error("processFile", e);
		} finally {
			log.error(String.format("filePath=%s,totalSize=%s", filePath, totalSize));
			ProcessFileCommonCode.flushData(bufferMap);
		}
	}

	private void processFileContent(String filePath, List<String> contentList, int startIndex, int endIndex) {
		int ignoreNum = 0;
		int processLine = startIndex;
		String filePartStr = null;
		String fileContetStr = null;
		List<String> cacheList = null;
		try {
			for (String str : contentList) {
				if (!processClz.isProcessLine(str)) {
					ignoreNum++;
					continue;
				}
				filePartStr = processClz.getFilePart(str);
				fileContetStr = processClz.getProcessContent(str);
				if (dataMap.containsKey(filePartStr)) {
					dataMap.get(filePartStr).add(fileContetStr);
				} else {
					cacheList = new ArrayList<String>(128);
					cacheList.add(fileContetStr);
					dataMap.put(filePartStr, cacheList);
				}
				processLine++;
			}
			ProcessFileCommonCode.writeDataToFile(destFolder, filePath, randomPrefix, dataMap, bufferMap, pathMap,
					retryTime, symbol, processClz, destFileEncoding);
		} finally {
			log.error(String.format("filePath=%s,processLine=%s,ignore num=%s", filePath, processLine, ignoreNum));
			ProcessFileCommonCode.flushData(bufferMap);
			ProcessFileCommonCode.cleanMapData(dataMap);
			ProcessFileCommonCode.checkFileSize(destFolder, filePath, fileMaxSize, pathMap, bufferMap, randomPrefix,
					retryTime, symbol, processClz, destFileEncoding);
		}
	}

}
