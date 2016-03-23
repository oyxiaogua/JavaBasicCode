package com.xiaogua.better.file;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

public class FileCommonUtil extends org.apache.commons.io.FileUtils {
	/**
	 * 获取当前系统换行符
	 */
	public static String getSystemLineSeparator() {
		return System.getProperty("line.separator", "\n");
	}

	/**
	 * 获取BufferedReader
	 */
	public static BufferedReader getBufferedReader(String filePath, String encoding) throws Exception {
		return new BufferedReader(new InputStreamReader(new FileInputStream(filePath), encoding));
	}

	/**
	 * 获取BufferedWriter 
	 */
	public BufferedWriter getBufferedWriter(String filePath, String encoding) throws Exception {
		return getBufferedWriter(filePath, encoding, false);
	}
	
	/**
	 * 获取BufferedWriter 支持追加写
	 */
	public BufferedWriter getBufferedWriter(String filePath, String encoding,boolean append) throws Exception {
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath,append), encoding);
		BufferedWriter bw = new BufferedWriter(osw);
		return bw;
	}

	/**
	 * 清空文件内容
	 */
	public static void cleanFileContent(String filePath) {
		cleanFileContent(new File(filePath));
	}

	/**
	 * 清空文件内容
	 */
	public static void cleanFileContent(File file) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			out.write(new byte[0]);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	/**
	 * 添加内容到文件
	 */
	public static void appendContentToFile(String filePath, String content) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(filePath, true);
			writer.write(content);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(writer);
		}
	}

	/**
	 * 添加内容到文件
	 */
	public static void appendContentToFile(String fileName, String content, String encoding) {
		RandomAccessFile randomFile = null;
		try {
			if (encoding == null) {
				encoding = "utf-8";
			}
			randomFile = new RandomAccessFile(fileName, "rw");
			long fileLength = randomFile.length();
			randomFile.seek(fileLength);
			randomFile.write(content.getBytes(encoding));
			randomFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(randomFile);
		}
	}

	/**
	 * 获取文件后缀 如txt
	 */
	public static String getFileExtension(String filePath) {
		return FilenameUtils.getExtension(filePath);
	}

	/**
	 * 获取去除目录和后缀后的文件名 e:/test_tmp/sys.xml返回sys
	 */
	public static String getFileBaseName(String filePath) {
		return FilenameUtils.getBaseName(filePath);
	}

	/**
	 * 获取去除目录后的文件名称，包含后缀 如返回sys.xml
	 */
	public static String getFileName(String filePath) {
		return FilenameUtils.getName(filePath);
	}

	/**
	 * 获取文件路径,不包含文件名 如e:/test_tmp/sys.xml返回e:/test_tmp/
	 */
	public static String getFullPathNoFile(String filePath) {
		return FilenameUtils.getFullPath(filePath);
	}

	/**
	 * 获取文件路径,不包含文件名,不包含文件最后的分隔符 如e:/test_tmp/sys.xml返回e:/test_tmp
	 */
	public static String getFullPathNoEndSeparator(String filePath) {
		return FilenameUtils.getFullPathNoEndSeparator(filePath);
	}

	/**
	 * 判断文件后缀结尾
	 */
	public static boolean isEndOfExtension(String filePath, String suffix) {
		if (filePath == null || suffix == null) {
			return false;
		}
		return FilenameUtils.isExtension(filePath, suffix);
	}

	/**
	 * 判断文件后缀是否以..结尾
	 */
	public static boolean isEndOfExtension(String filePath, String[] suffixArr) {
		return isEndOfExtension(filePath, suffixArr, false);
	}

	/**
	 * 判断文件后缀是否以..结尾
	 */
	public static boolean isEndOfExtension(String filePath, String[] suffixArr, boolean ignoreCase) {
		if (filePath == null) {
			return false;
		}
		if (suffixArr == null || suffixArr.length == 0) {
			return false;
		}
		if (ignoreCase) {
			filePath = filePath.toLowerCase();
			for (int i = 0, len = suffixArr.length; i < len; i++) {
				suffixArr[i] = (suffixArr[i] == null ? null : suffixArr[i].toLowerCase());
			}
		}
		return FilenameUtils.isExtension(filePath, suffixArr);
	}

	/**
	 * 删除文件后缀 e:/test_tmp/sys.xml返回 e:/test_tmp/sys
	 */
	public static String removeExtension(String filePath) {
		return FilenameUtils.removeExtension(filePath);
	}

	/**
	 * 文件显示
	 * 
	 * @param size
	 * @return
	 */
	public static String getReadableSize(long size) {
		final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
		final int unitIndex = (int) (Math.log10(size) / 3);
		final double unitValue = 1 << (unitIndex * 10);
		final String readableSize = new DecimalFormat("#,##0.#").format(size / unitValue) + " " + units[unitIndex];
		return readableSize;
	}

	/**
	 * 获取文件行数
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static int getFileTotalLine(String filename) throws Exception {
		InputStream is = new BufferedInputStream(new FileInputStream(filename));
		try {
			byte[] c = new byte[1024];
			int count = 0;
			int readChars = 0;
			boolean empty = true;
			while ((readChars = is.read(c)) != -1) {
				empty = false;
				for (int i = 0; i < readChars; ++i) {
					if (c[i] == '\n') {
						++count;
					}
				}
			}
			return (count == 0 && !empty) ? 1 : count;
		} finally {
			is.close();
		}
	}

	/**
	 * 获取文件最后一行
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFileLastLine(String filePath) {
		RandomAccessFile raf;
		String lastLine = "";
		try {
			raf = new RandomAccessFile(filePath, "r");
			long len = raf.length();
			if (len != 0L) {
				long pos = len - 1;
				while (pos > 0) {
					pos--;
					raf.seek(pos);
					if (raf.readByte() == '\n') {
						lastLine = raf.readLine();
						break;
					}
				}
			}
			raf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lastLine;
	}

	/**
	 * 快速创建大文件
	 * 
	 * @param filePath
	 * @param fileSize
	 * @throws Exception
	 */
	public static void fastCreateBigFile(String filePath, int fileSize) throws Exception {
		RandomAccessFile r = new RandomAccessFile(filePath, "rw");
		r.setLength(fileSize);
		r.close();
	}

	/**
	 * 文件深度非递归遍历
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static List<File> getFileListByDFS(File file) throws IOException {
		Stack<File> stack = new Stack<File>();
		stack.push(file);
		File fileInStack = null;
		List<File> fileList = new ArrayList<File>();
		while (!stack.isEmpty()) {
			fileInStack = stack.pop();
			File[] files = fileInStack.listFiles();
			for (File eachFile : files) {
				if (eachFile.isFile()) {
					fileList.add(eachFile);
				} else {
					stack.push(eachFile);
				}
			}
		}
		return fileList;
	}

	/**
	 * 文件广度非递归遍历
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static List<File> getFileListByBFS(File file) throws IOException {
		Queue<File> queue = new LinkedList<File>();
		queue.offer(file);
		File fileInQueue = null;
		List<File> fileList = new ArrayList<File>();
		while (queue.size() > 0) {
			fileInQueue = queue.poll();
			File[] files = fileInQueue.listFiles();
			for (File eachFile : files) {
				if (eachFile.isFile()) {
					fileList.add(eachFile);
				} else {
					queue.offer(eachFile);
				}
			}
		}
		return fileList;
	}

}
