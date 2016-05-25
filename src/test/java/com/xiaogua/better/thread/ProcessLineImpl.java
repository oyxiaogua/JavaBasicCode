package com.xiaogua.better.thread;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

public class ProcessLineImpl implements Interface_ProcessLine {

	public boolean isProcessLine(String content) {
		if (content != null) {
			return content.endsWith("|1");
		}
		return false;
	}

	public String getProcessContent(String content) {
		String[] contentArr = content.split("\\|");
		StringBuilder sb = new StringBuilder(128);
		sb.append(contentArr[0]).append("|").append(DigestUtils.md5Hex(contentArr[0])).append("|");
		sb.append(contentArr[1]).append("|").append(DigestUtils.sha256Hex(contentArr[1]));
		return sb.toString();
	}

	public String getFilePart(String content) {
		return Integer.toString(Math.abs(content.split("\\|")[0].hashCode()) % 30);
	}

	public String getDynamicFileName(String filePath) {
		return DateFormatUtils.format(new Date(), "yyyyMMddHHmmss") + "_" + RandomStringUtils.randomAlphabetic(4);
	}

	public String getFileExtension() {
		return ".txt";
	}

}
