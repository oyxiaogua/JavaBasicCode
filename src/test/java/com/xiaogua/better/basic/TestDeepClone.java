package com.xiaogua.better.basic;

import org.junit.Assert;
import org.junit.Test;

import com.xiaogua.better.bean.Dept_1_Bean;
import com.xiaogua.better.bean.Dept_1_Normal_Bean;
import com.xiaogua.better.bean.Dept_1_Normal_Bean2;
import com.xiaogua.better.bean.Dept_2_Bean;
import com.xiaogua.better.bean.Dept_2_Normal_Bean;
import com.xiaogua.better.bean.Dept_2_Normal_Bean2;

public class TestDeepClone {

	@Test
	public void testDeepCloneWithCloneable() throws Exception {
		Dept_1_Bean bean = new Dept_1_Bean(1, "test");
		Dept_1_Bean arrBean = new Dept_1_Bean(2, "test_2");
		Dept_1_Bean arrBean2 = new Dept_1_Bean(3, "test_3");
		Dept_1_Bean[] beanArr = new Dept_1_Bean[2];
		beanArr[0] = arrBean;
		beanArr[1] = arrBean2;
		Dept_2_Bean bean2 = new Dept_2_Bean(4, "name2", bean, beanArr);

		Dept_2_Bean bean3 = (Dept_2_Bean) bean2.clone();
		System.out.println(bean2 + "," + bean2.printDept2Info());
		System.out.println(bean3 + "," + bean3.printDept2Info());

		String updateNameStr = "updateName";
		bean3.getDept1BeanArr()[0].setName(updateNameStr);
		Assert.assertFalse(updateNameStr.equals(bean2.getDept1BeanArr()[0].getName()));
	}

	@Test
	public void testDeepCloneWithSerialize() throws Exception {
		Dept_1_Normal_Bean bean = new Dept_1_Normal_Bean(1, "test");
		Dept_1_Normal_Bean arrBean = new Dept_1_Normal_Bean(2, "test_2");
		Dept_1_Normal_Bean arrBean2 = new Dept_1_Normal_Bean(3, "test_3");
		Dept_1_Normal_Bean[] beanArr = new Dept_1_Normal_Bean[2];
		beanArr[0] = arrBean;
		beanArr[1] = arrBean2;
		Dept_2_Normal_Bean bean2 = new Dept_2_Normal_Bean(4, "name2", bean, beanArr);
		byte[] byteArr = SerializeCode.convertObjToByteArr(bean2);
		Dept_2_Normal_Bean bean3 = (Dept_2_Normal_Bean) SerializeCode.convertByteArrToObj(byteArr);
		System.out.println(bean2 + "," + bean2.printDept2Info());
		System.out.println(bean3 + "," + bean3.printDept2Info());

		String updateNameStr = "updateName";
		bean3.getDept1BeanArr()[0].setName(updateNameStr);
		Assert.assertFalse(updateNameStr.equals(bean2.getDept1BeanArr()[0].getName()));
	}

	@Test
	public void testDeepCloneWithJson() throws Exception {
		Dept_1_Normal_Bean2 bean = new Dept_1_Normal_Bean2(1, "test");
		Dept_1_Normal_Bean2 arrBean = new Dept_1_Normal_Bean2(2, "test_2");
		Dept_1_Normal_Bean2 arrBean2 = new Dept_1_Normal_Bean2(3, "test_3");
		Dept_1_Normal_Bean2[] beanArr = new Dept_1_Normal_Bean2[2];
		beanArr[0] = arrBean;
		beanArr[1] = arrBean2;
		Dept_2_Normal_Bean2 bean2 = new Dept_2_Normal_Bean2(4, "name2", bean, beanArr);
		String jsonStr = JacksonJsonCode.convertObjToStr(bean2);
		Dept_2_Normal_Bean2 bean3 = JacksonJsonCode.convertStrToObj(jsonStr, Dept_2_Normal_Bean2.class);
		System.out.println(bean2 + "," + bean2.printDept2Info());
		System.out.println(bean3 + "," + bean3.printDept2Info());

		String updateNameStr = "updateName";
		bean3.getDept1BeanArr()[0].setName(updateNameStr);
		Assert.assertFalse(updateNameStr.equals(bean2.getDept1BeanArr()[0].getName()));
	}
}
