package com.xiaogua.better.file;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ReadContentWithLineNum {
	public static String readFileByLineNumWithJava8(String filePath, String encoding, int lineNum) throws Exception {
		String lineContent = null;
		try (Stream<String> lines = Files.lines(Paths.get(filePath), Charset.forName(encoding))) {
			lineContent = lines.skip(lineNum).findFirst().orElse(null);
		}
		return lineContent;
	}

	public static String readFileByLineWithBufferedReader(String filePath, String encoding, int lineNum)
			throws Exception {
		String lineContent = null;
		try (BufferedReader br = FileCommonUtil.getBufferedReader(filePath, encoding);) {
			for (int i = 0; i < lineNum; ++i) {
				br.readLine();
			}
			lineContent = br.readLine();
		}
		return lineContent;
	}

}
