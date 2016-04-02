package com.xiaogua.better.basic;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.SortedSet;
import java.util.TreeSet;

public class CharsetCode {
	/**
	 * @See 
	 * https://github.com/sualeh/What-a-Character/blob/master/src/main/java/us/fatehi/whatacharacter/Charsets.java
	 */
	public static void printCharsetDetails(final Charset charset) {
		String displayName = charset.name();
		final SortedSet<String> aliases = new TreeSet<String>(charset.aliases());
		aliases.add(charset.name());
		final float maxBytesPerChar;
		if (charset.canEncode()) {
			final CharsetEncoder encoder = charset.newEncoder();
			maxBytesPerChar = encoder.maxBytesPerChar();
		} else {
			maxBytesPerChar = 0;
		}
		System.out.println(
				String.format("charset: %s%naliases: %s%nmax %d bytes%n", displayName, aliases, (int) maxBytesPerChar));
	}
}
