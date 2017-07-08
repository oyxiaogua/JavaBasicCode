package com.xiaogua.better.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

public class TestFileMd5 {
	@Test
	public void testGetFileMd5() throws Exception{
		String filePath="E:/迅雷下载/终极斗士3：赎罪BD中英双字.rmvb";
		long startTime=System.currentTimeMillis();
		String md5Str=new String(Hex.encodeHex(getMD5Digits(new File(filePath))));
		System.out.println(md5Str);
		System.out.println(System.currentTimeMillis()-startTime);
	}
	
	public static byte[] getMD5Digits(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        FileChannel channel = inputStream.getChannel();
        try {
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            long position = 0;
            long remaining = file.length();
            MappedByteBuffer byteBuffer = null;
            while (remaining > 0) {
                long size = Integer.MAX_VALUE / 2;
                if (size > remaining) {
                    size = remaining;
                }
                byteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, position, size);
                messagedigest.update(byteBuffer);
                position += size;
                remaining -= size;
            }
            unMapBuffer(byteBuffer, channel.getClass());
            return messagedigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } finally {
            channel.close();
            inputStream.close();
        }
    }
	public static void unMapBuffer(MappedByteBuffer buffer, Class<?> channelClass) throws IOException{
	    if (buffer == null) {
	        return;
	    }
	    Throwable throwable = null;
	    try {
	        Method unmap = channelClass.getDeclaredMethod("unmap", MappedByteBuffer.class);
	        unmap.setAccessible(true);
	        unmap.invoke(channelClass, buffer);
	    } catch (NoSuchMethodException e) {
	        throwable = e;
	    } catch (IllegalAccessException e) {
	        throwable = e;
	    } catch (InvocationTargetException e) {
	        throwable = e;
	    }

	    if (throwable != null) {
	        throw new IOException("MappedByte buffer unmap error", throwable);
	    }
	}
}
