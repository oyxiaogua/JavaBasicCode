package com.xiaogua.better.basic;

import java.util.Date;

import org.junit.Test;

import com.xiaogua.better.bean.Person_Bean;

public class TestHessianCode {
	@Test
	public void testHessianEncode() {
		Person_Bean bean = new Person_Bean(1, "test", null, new Date(), "address测试");
		byte[] rtnArr = HessianCode.encodeObj(bean);
		Person_Bean bean2 = (Person_Bean) HessianCode.decodeArr(rtnArr);
		System.out.println(bean2);
	}
}
