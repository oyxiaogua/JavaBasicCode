package com.xiaogua.better.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeCode {

	/**
	 * object转byte数组,需实现Serializable接口
	 */
	public static byte[] convertObjToByteArr(Object obj) throws Exception {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(bos);) {
			oos.writeObject(obj);
			oos.flush();
			return bos.toByteArray();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * byte数组转object,需实现Serializable接口
	 */
	public static Object convertByteArrToObj(byte[] byteArr) throws Exception {
		try (ByteArrayInputStream bin = new ByteArrayInputStream(byteArr);
				ObjectInputStream ois = new ObjectInputStream(bin);) {
			return ois.readObject();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
}
