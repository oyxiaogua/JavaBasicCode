package com.xiaogua.better.file;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
	
}
