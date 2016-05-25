package com.xiaogua.better.thread;

import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.xiaogua.better.file.FileCommonUtil;
import com.xiaogua.better.str.StringCommonUtils;

public class ProcessFileCommonCode {
	private final static Logger log = Logger.getLogger(ProcessFileCommonCode.class);

	/**
	 * 清空Map
	 */
	public static void clearMap(Map<String, BufferedWriter> bufferMap, Map<String, String> pathMap,
			Map<String, List<String>> dataMap) {
		if (bufferMap != null) {
			bufferMap.clear();
		}
		if (pathMap != null) {
			pathMap.clear();
		}
		if (dataMap != null) {
			dataMap.clear();
		}
	}

	/**
	 * 关闭BufferWriter
	 */
	public static void closeBufferWriter(Map<String, BufferedWriter> bufferMap) {
		Iterator<Entry<String, BufferedWriter>> it = bufferMap.entrySet().iterator();
		Entry<String, BufferedWriter> entry = null;
		while (it.hasNext()) {
			entry = it.next();
			BufferedWriter writer = entry.getValue();
			if (writer != null) {
				try {
					writer.flush();
					writer.close();
				} catch (Exception e) {
					log.error(String.format("method=%s,part=%s", "closeBufferWriter", entry.getKey()));
				}
			}
		}
	}

	/**
	 * 数据刷到硬盘
	 */
	public static void flushData(Map<String, BufferedWriter> bufferMap) {
		Iterator<Entry<String, BufferedWriter>> it = bufferMap.entrySet().iterator();
		Entry<String, BufferedWriter> entry = null;
		while (it.hasNext()) {
			entry = it.next();
			BufferedWriter writer = entry.getValue();
			if (writer != null) {
				try {
					writer.flush();
				} catch (Exception e) {
					log.error(String.format("method=%s,part=%s", "flushData", entry.getKey()));
				}
			}
		}
	}

	/**
	 * 检查文件大小
	 */
	public static void checkFileSize(String folderPath, String fileName, long fileMaxSize, Map<String, String> pathMap,
			Map<String, BufferedWriter> bufferMap, String randomPrefix, int retryTime, String symbol,
			Interface_ProcessLine processClz, String destFileEncoding) {
		String filePartStr = null;
		Iterator<Entry<String, String>> it = pathMap.entrySet().iterator();
		Entry<String, String> entry = null;
		String filePath = null;
		while (it.hasNext()) {
			entry = it.next();
			filePartStr = entry.getKey();
			filePath = entry.getValue();
			try {
				checkFileSize(folderPath, fileName, filePartStr, filePath, fileMaxSize, bufferMap, pathMap,
						randomPrefix, retryTime, symbol, processClz, destFileEncoding);
			} catch (Exception e) {
				log.error(String.format("method=%s,fileName=%s,filePath=%s,filePart=%s", "checkFileSize", fileName,
						filePath, filePartStr), e);

			}
		}
	}

	public static void checkFileSize(String folderPath, String fileName, String filePartStr, String filePath,
			long fileMaxSize, Map<String, BufferedWriter> bufferMap, Map<String, String> pathMap, String randomPrefix,
			int retryTime, String symbol, Interface_ProcessLine processClz, String destFileEncoding) throws Exception {
		File file = new File(filePath);
		if (!file.exists()) {
			return;
		}
		long fileSize = file.length();
		if (fileSize >= fileMaxSize) {
			BufferedWriter writer = bufferMap.get(filePartStr);
			writer.flush();
			writer.close();
			createBufferWrite(folderPath, fileName, filePartStr, randomPrefix, bufferMap, pathMap, retryTime, symbol,
					processClz, destFileEncoding);
		}
	}

	/**
	 * 清空Map数据
	 */
	public static void cleanMapData(Map<String, List<String>> dataMap) {
		if (dataMap != null) {
			dataMap.clear();
		}
	}

	/**
	 * 数据写入文件
	 */
	public static void writeDataToFile(String folderPath, String filePath, String randomPrefix,
			Map<String, List<String>> dataMap, Map<String, BufferedWriter> bufferMap, Map<String, String> pathMap,
			int retryTime, String symbol, Interface_ProcessLine processClz, String destFileEncoding) {
		Iterator<Entry<String, List<String>>> it = dataMap.entrySet().iterator();
		Entry<String, List<String>> entry = null;
		String filePartStr = null;
		List<String> dataList = null;
		while (it.hasNext()) {
			entry = it.next();
			filePartStr = entry.getKey();
			dataList = entry.getValue();
			writeDataToFile(folderPath, filePath, filePartStr, dataList, randomPrefix, bufferMap, pathMap, retryTime,
					symbol, processClz, destFileEncoding);
		}
	}

	/**
	 * 数据写入文件
	 */
	public static void writeDataToFile(String folderPath, String filePath, String filePartStr, List<String> dataList,
			String randomPrefix, Map<String, BufferedWriter> bufferMap, Map<String, String> pathMap, int retryTime,
			String symbol, Interface_ProcessLine processClz, String destFileEncoding) {
		if (dataList == null || dataList.size() == 0) {
			return;
		}
		StringBuilder sb = new StringBuilder(32);
		BufferedWriter writer = null;
		try {
			writer = getBufferWrite(folderPath, filePath, filePartStr, randomPrefix, bufferMap, pathMap, retryTime,
					symbol, processClz, destFileEncoding);
			for (String str : dataList) {
				writer.write(str);
				writer.newLine();
			}
		} catch (Exception e) {
			log.error(String.format("method=%s,filePath=%s,filePart=%s", "writeDataToFile", filePath, filePartStr), e);
		}
		sb.setLength(0);
	}

	/**
	 * 获取getBufferWrite
	 */
	public static BufferedWriter getBufferWrite(String folderPath, String filePath, String filePartStr,
			String randomPrefix, Map<String, BufferedWriter> bufferMap, Map<String, String> pathMap, int retryTime,
			String symbol, Interface_ProcessLine processClz, String destFileEncoding) throws Exception {
		BufferedWriter writer;
		writer = bufferMap.get(filePartStr);
		if (writer == null) {
			writer = createBufferWrite(folderPath, filePath, filePartStr, randomPrefix, bufferMap, pathMap, retryTime,
					symbol, processClz, destFileEncoding);
		}
		return writer;
	}

	/**
	 * 获取文件列表
	 */
	public static List<String> getFolderFileList(File folder, String prefix) {
		List<String> rtnList = new ArrayList<String>();
		try {
			String[] prefixArr = null;
			if (!StringCommonUtils.isNull(prefix)) {
				prefixArr = prefix.split(",");
			}
			String fileNameStr = null;
			File[] fileArr = folder.listFiles();
			for (File file : fileArr) {
				fileNameStr = file.getName();
				if (!isStartWithPrefix(fileNameStr, prefixArr)) {
					continue;
				}
				rtnList.add(fileNameStr);
			}
		} catch (Exception e) {
			log.error("getFolderFileList error", e);
		}
		return rtnList;
	}

	/**
	 * 是否以指定前缀开头
	 */
	public static boolean isStartWithPrefix(String fileName, String[] prefixArr) {
		if (prefixArr == null || prefixArr.length == 0) {
			// 默认不过滤
			return true;
		}
		for (String str : prefixArr) {
			if (str != null && fileName.startsWith(str)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 新建BufferWrite
	 */
	private static BufferedWriter createBufferWrite(String folderPath, String filePath, String filePartStr,
			String randomPrefix, Map<String, BufferedWriter> bufferMap, Map<String, String> pathMap, int retryTime,
			String symbol, Interface_ProcessLine processClz, String destFileEncoding) throws Exception {
		File file;
		String destFilePath = null;
		BufferedWriter writer = null;
		StringBuilder sb = new StringBuilder(32);
		sb.append(randomPrefix).append(processClz.getDynamicFileName(filePath)).append(symbol).append(filePartStr)
				.append(processClz.getFileExtension());
		destFilePath = sb.toString();
		file = new File(folderPath + destFilePath);
		if (file.exists()) {
			for (int k = 1; k < retryTime; k++) {
				sb.setLength(0);
				sb.append(randomPrefix).append(processClz.getDynamicFileName(filePath)).append(symbol)
						.append(filePartStr);
				destFilePath = sb.toString();
				file = new File(folderPath + destFilePath);
				if (!file.exists()) {
					bufferMap.put(filePartStr,
							FileCommonUtil.getBufferedWriter(folderPath + destFilePath, destFileEncoding, true));
					pathMap.put(filePartStr, folderPath + destFilePath);
					writer = bufferMap.get(filePath);
					break;
				}
			}
		} else {
			bufferMap.put(filePartStr,
					FileCommonUtil.getBufferedWriter(folderPath + destFilePath, destFileEncoding, true));
			pathMap.put(filePartStr, folderPath + destFilePath);
			writer = bufferMap.get(filePartStr);
		}
		return writer;
	}
}
