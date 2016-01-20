package com.xiaogua.better.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.StreamSupport;

import org.apache.commons.io.FileUtils;

public class GetFolderSize {

	/**
	 * 使用commons-io获取文件夹大小
	 * 
	 * @param folderStr
	 * @return
	 * @throws Exception
	 */
	public long getFolderSizeUsingApacheCommonsIO(String folderStr) {
		final File folder = new File(folderStr);
		final long size = FileUtils.sizeOfDirectory(folder);
		return size;
	}

	/**
	 * 使用guava java8获取文件夹大小
	 * 
	 * @param folderStr
	 * @return
	 * @throws Exception
	 */
	public long getFolderSizeUsingGuava(String folderStr) {
		final File folder = new File(folderStr);
		final Iterable<File> files = com.google.common.io.Files.fileTreeTraverser().breadthFirstTraversal(folder);
		final long size = StreamSupport.stream(files.spliterator(), false).filter(f -> f.isFile())
				.mapToLong(File::length).sum();
		return size;
	}

	/**
	 * 使用Java8获取文件夹大小
	 * 
	 * @param folderStr
	 * @return
	 * @throws Exception
	 */
	public long getFolderSizeUsingJava8(String folderStr) throws Exception {
		final Path folder = Paths.get(folderStr);
		final long size = Files.walk(folder).filter(p -> p.toFile().isFile()).mapToLong(p -> p.toFile().length()).sum();
		return size;
	}

	/**
	 * 使用Java7获取文件夹大小
	 * 
	 * @param folderStr
	 * @return
	 * @throws Exception
	 */
	public long getFolderSizeUsingJava7(String folderStr) throws Exception {
		final AtomicLong size = new AtomicLong(0);
		final Path folder = Paths.get(folderStr);
		Files.walkFileTree(folder, new SimpleFileVisitor<Path>() {
			public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
				size.addAndGet(attrs.size());
				return FileVisitResult.CONTINUE;
			}
		});
		return size.longValue();
	}

	/**
	 * 获取文件夹大小(递归)
	 * 
	 * @param folderStr
	 */
	public long getFolderSizeWithRecursive(String folderStr) {
		final File folder = new File(folderStr);
		final long size = getFolderSize(folder);
		return size;
	}

	private long getFolderSize(final File folder) {
		long length = 0;
		final File[] files = folder.listFiles();
		final int count = files.length;
		for (int i = 0; i < count; i++) {
			if (files[i].isFile()) {
				length += files[i].length();
			} else {
				length += getFolderSize(files[i]);
			}
		}
		return length;
	}
}
