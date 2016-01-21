package com.xiaogua.better.basic;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

import org.junit.Test;

public class TestByteOrder {
	@Test
	public void testByteOrderNativeOrder() {
		if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
			System.out.println("BIG_ENDIAN");
		} else {
			System.out.println("LITTLE_ENDIAN");
		}
	}

	@Test
	public void testByteBufferByteOrder() {
		// 大端1 2 3 4
		// 小端4 3 2 1
		int x = 0x01020304;
		ByteBuffer bb = ByteBuffer.wrap(new byte[4]);
		bb.asIntBuffer().put(x);
		String ss_before = Arrays.toString(bb.array());

		System.out.println("默认字节序 " + bb.order().toString() + "," + " 内存数据 " + ss_before);

		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.asIntBuffer().put(x);
		String ss_after = Arrays.toString(bb.array());

		System.out.println("修改字节序 " + bb.order().toString() + "," + " 内存数据 " + ss_after);
	}
}
