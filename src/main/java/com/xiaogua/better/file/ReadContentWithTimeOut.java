package com.xiaogua.better.file;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

public class ReadContentWithTimeOut {
	public static String getStreamAsStringWithTimeOut(InputStream is, String charset, int timeoutMillis)
			throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset));
		StringWriter writer = new StringWriter();
		final int maxSize = 256;
		char[] chars = new char[maxSize];
		int countRead = 0;
		long maxTimeMillis = System.currentTimeMillis() + timeoutMillis;
		int availableTotal = 0;
		while (System.currentTimeMillis() < maxTimeMillis) {
			// inputstream.available()方法返回的值是该inputstream在不被阻塞的情况下一次可以读取到的数据长度
			availableTotal = is.available();
			if (availableTotal == 0) {
				if (is.read() == -1) {
					break;
				}
				continue;
			}
			for (int pageNum = 1, pageTotal = (availableTotal - 1) / maxSize + 1; pageNum <= pageTotal; pageNum++) {
				int readLength = java.lang.Math.min(availableTotal - (pageNum - 1) * maxSize, maxSize);
				countRead = reader.read(chars, 0, readLength);
				if (countRead == -1) {
					break;
				}
				writer.write(chars, 0, countRead);
			}
		}
		return writer.toString();
	}

	/**
	 * @see http://stackoverflow.com/questions/804951/is-it-possible-to-read-
	 *      from-a-inputstream-with-a-timeout
	 */
	public static int readInputStreamWithTimeout(InputStream is, byte[] b, int timeoutMillis) throws Exception {
		int bufferOffset = 0;
		long maxTimeMillis = System.currentTimeMillis() + timeoutMillis;
		int readLength = -1, readResult = -1;
		while (System.currentTimeMillis() < maxTimeMillis && bufferOffset < b.length) {
			readLength = java.lang.Math.min(is.available(), b.length - bufferOffset);
			readResult = is.read(b, bufferOffset, readLength);
			if (readResult == -1) {
				break;
			}
			bufferOffset += readResult;
		}
		return bufferOffset;
	}
}
