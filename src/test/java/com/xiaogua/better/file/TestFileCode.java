package com.xiaogua.better.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;
import org.junit.Test;
import org.springframework.util.StopWatch;

public class TestFileCode {
	@Test
	public void testGetCanonicalPath() throws Exception {
		System.out.println(new File("/").getCanonicalPath());
		System.out.println(new File(".").getCanonicalPath());
	}

	@Test
	public void testGetFileListBreadthFirstWithGuava() {
		String filePath = "e:/test_tmp/";
		final File folder = new File(filePath);
		StopWatch stopWatch = new StopWatch();
		final Iterable<File> filesIterable = com.google.common.io.Files.fileTreeTraverser()
				.breadthFirstTraversal(folder);
		Stream<File> fileStream = StreamSupport.stream(filesIterable.spliterator(), false).filter(p -> p.isFile());
		fileStream.forEach(p -> System.out.println(p.getAbsoluteFile()));
		System.out.println(
				String.format("method=%s,time=%s", "testGetFileListUsingGuava", stopWatch.getTotalTimeMillis()));
	}

	@Test
	public void testReadFileContentWithJava8() throws Exception {
		Files.lines(Paths.get("e:/test_tmp/test_world_1234.txt"), StandardCharsets.UTF_8).forEach(System.out::println);
	}
	
	@Test
	public void testReadUtfBomFile() throws Exception {
		String defaultEncoding = "UTF-8";
		String fileNameStr = "e:/test_tmp/test_file_with_bom.txt";
		InputStream in = new FileInputStream(new File(fileNameStr));
		BufferedReader br = null;
		BOMInputStream bomIn = null;
		InputStreamReader reader = null;
		try {
			bomIn = new BOMInputStream(in);
			ByteOrderMark bom = bomIn.getBOM();
			String charsetName = (bom == null ? defaultEncoding : bom.getCharsetName());
			reader = new InputStreamReader(bomIn, charsetName);
			br = new BufferedReader(reader);
			String str = null;
			while ((str = br.readLine()) != null) {
				System.out.println(str);
			}
		} finally {
			IOUtils.closeQuietly(br);
			IOUtils.closeQuietly(reader);
			IOUtils.closeQuietly(bomIn);
			IOUtils.closeQuietly(in);
		}
	}
	
}
