package com.xiaogua.better.basic;

import org.junit.Test;

import com.xiaogua.better.bean.ExternalizableBean;
import com.xiaogua.better.bean.SerializableAndExtBean;
import com.xiaogua.better.bean.SerializableBean;

public class TestSerializable {

	@Test
	public void testSerializableBean() throws Exception {
		SerializableBean bean = new SerializableBean(1, "testName", "测试", (short) 22, 12345.1234);
		byte[] byteArr = SerializeCode.convertObjToByteArr(bean);
		SerializableBean bean2 = (SerializableBean) SerializeCode.convertByteArrToObj(byteArr);
		System.out.println(bean2);
	}

	@Test
	public void testExternalizableBean() throws Exception {
		// 需要无参public构造函数
		ExternalizableBean bean = new ExternalizableBean(2, "testName2", "测试2", (short) 23, 123456.1234);
		byte[] byteArr = SerializeCode.convertObjToByteArr(bean);
		ExternalizableBean bean2 = (ExternalizableBean) SerializeCode.convertByteArrToObj(byteArr);
		System.out.println(bean2);
	}

	@Test
	public void testExternalizableOverideSerializable() throws Exception {
		// Externalizable覆盖Serializable
		SerializableAndExtBean bean = new SerializableAndExtBean(3, "testName3", "测试3", (short) 24, 12345678.1234);
		byte[] byteArr = SerializeCode.convertObjToByteArr(bean);
		SerializableAndExtBean bean2 = (SerializableAndExtBean) SerializeCode.convertByteArrToObj(byteArr);
		System.out.println(bean2);
	}
}
