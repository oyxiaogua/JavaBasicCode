package com.xiaogua.better.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

public class HessianCode {
	private static final Logger log = LoggerFactory.getLogger(HessianCode.class);

	/**
	 * 对象转换为byte数组
	 * 
	 * @param obj
	 * @return
	 */
	public static byte[] encodeObj(Object obj) {
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
		Hessian2Output output = new Hessian2Output(byteArray);
		try {
			output.writeObject(obj);
			output.flush();
			output.close();
			return byteArray.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				output.close();
			} catch (Exception e) {
				log.error("close output error", e);
			}
		}
	}

	/**
	 * 将byte数组转回对象
	 * 
	 * @param data
	 * @return
	 */
	public static Object decodeArr(byte[] data) {
		final Hessian2Input input = new Hessian2Input(new ByteArrayInputStream(data));
		try {
			return input.readObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				input.close();
			} catch (Exception e) {
				log.error("close input error", e);
			}
		}
	}
}
