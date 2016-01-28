package com.xiaogua.better.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class IOCode {
	public InputStream convertOutputStreamToInputStream(OutputStream outStream) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos = (ByteArrayOutputStream) outStream;
		return new ByteArrayInputStream(baos.toByteArray());
	}
}
