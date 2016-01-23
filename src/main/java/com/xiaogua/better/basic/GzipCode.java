package com.xiaogua.better.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipCode {
	public static boolean isGzip(byte[] buffer) {
		return buffer.length > 2 && buffer[0] == (byte) 0x1F && buffer[1] == (byte) 0x8B;
	}

	/**
	 * 压缩byte
	 * 
	 * @param dateArr
	 * @return
	 * @throws Exception
	 */
	public static byte[] compressByte(byte[] dateArr) throws Exception {
		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				GZIPOutputStream gzip = new GZIPOutputStream(out);) {
			gzip.write(dateArr);
			// 一定要关闭
			gzip.close();
			return out.toByteArray();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public static byte[] unzipByte(byte[] compressDateArr) throws Exception {
		try (GZIPInputStream gzip = new GZIPInputStream(new ByteArrayInputStream(compressDateArr));
				ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = gzip.read(buffer)) != -1) {
				out.write(buffer, 0, offset);
			}
			return out.toByteArray();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
}
