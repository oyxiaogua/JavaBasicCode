package com.xiaogua.better.basic;

import java.nio.charset.Charset;
import java.util.SortedMap;

import org.junit.Test;

public class TestCharsetCode {

	@Test
	public void testPrintDefaultCharsetDetails() {
		CharsetCode.printCharsetDetails(Charset.defaultCharset());
	}

	@Test
	public void testPrintAllDefaultCharsetDetails() {
		System.out.println("all supported charsets\n");
		final SortedMap<String, Charset> availableCharsets = Charset.availableCharsets();
		for (final Charset charset : availableCharsets.values()) {
			CharsetCode.printCharsetDetails(charset);
		}
	}

}
