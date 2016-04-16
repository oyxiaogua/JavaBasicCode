package com.xiaogua.better.basic;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Assert;
import org.junit.Test;

import com.xiaogua.better.bean.Normal_Pkg_Bean;
import com.xiaogua.better.bean.Sub_All_Bean;
import com.xiaogua.better.bean.Sub_Normal_Bean;
import com.xiaogua.better.datetime.DateTimeCode;

public class TestOperateJavaBean {

	@Test
	public void testIntrospectorGetBeanInfo() throws Exception {
		// 获取JavaBean的信息，排除父类Object的信息
		BeanInfo beanInfo = Introspector.getBeanInfo(Sub_Normal_Bean.class, Object.class);
		// JavaBean的属性信息
		for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
			System.out.println("property name :" + pd.getName());
			System.out.println("property type :" + pd.getPropertyType().toGenericString());
			System.out.println("property get method :" + pd.getReadMethod().toGenericString());
			System.out.println("property set method :" + pd.getWriteMethod().toGenericString());
		}
		// JavaBean的方法信息
		for (MethodDescriptor md : beanInfo.getMethodDescriptors()) {
			System.out.println(md.getDisplayName());
		}
	}

	@Test
	public void testIntrospectorFlushFromCaches() throws Exception {
		Introspector.flushFromCaches(Sub_Normal_Bean.class);
	}

	@Test
	public void testOperateBeanPropertyWithPropertyDescriptor() throws Exception {
		Sub_Normal_Bean subBean = new Sub_Normal_Bean();
		OperateJavaBean.setBeanPropertyWithPropertyDescriptor(subBean, "subName", "subName");
		OperateJavaBean.setBeanPropertyWithPropertyDescriptor(subBean, "parentName", "parentName");
		Object objValue = OperateJavaBean.getBeanPropertyWithPropertyDescriptor(subBean, "subName");
		System.out.println(objValue);
		objValue = OperateJavaBean.getBeanPropertyWithPropertyDescriptor(subBean, "parentName");
		System.out.println(objValue);
	}

	@Test(expected = IntrospectionException.class)
	public void testSetBeanNoExistPropertyWithPropertyDescriptor() throws Exception {
		Sub_Normal_Bean subBean = new Sub_Normal_Bean();
		OperateJavaBean.setBeanPropertyWithPropertyDescriptor(subBean, "noExistField", "noExistValue");
	}

	@Test(expected = IntrospectionException.class)
	public void testGetBeanNoExistPropertyWithPropertyDescriptor() throws Exception {
		Sub_Normal_Bean subBean = new Sub_Normal_Bean();
		OperateJavaBean.getBeanPropertyWithPropertyDescriptor(subBean, "noExistField");
	}

	@Test
	public void testOperateBeanPropertyWithIntrospector() throws Exception {
		Sub_Normal_Bean subBean = new Sub_Normal_Bean();
		OperateJavaBean.setBeanPropertyWithIntrospector(subBean, "subName", "subName");
		OperateJavaBean.setBeanPropertyWithIntrospector(subBean, "parentName", "parentName");
		Object objValue = OperateJavaBean.getBeanPropertyWithIntrospector(subBean, "subName");
		System.out.println(objValue);
		objValue = OperateJavaBean.getBeanPropertyWithIntrospector(subBean, "parentName");
		System.out.println(objValue);
	}

	@Test
	public void testSetBeanNoExistPropertyWithIntrospector() throws Exception {
		Sub_Normal_Bean subBean = new Sub_Normal_Bean();
		OperateJavaBean.setBeanPropertyWithIntrospector(subBean, "noExistField", "noExistValue");
	}

	@Test
	public void testGetBeanNoExistPropertyWithIntrospector() throws Exception {
		Sub_Normal_Bean subBean = new Sub_Normal_Bean();
		Object objValue = OperateJavaBean.getBeanPropertyWithIntrospector(subBean, "noExistField");
		Assert.assertEquals(objValue, null);
	}

	@Test
	public void testOperateBeanPropertyWithApacheBeanUtils() throws Exception {
		Sub_Normal_Bean subBean = new Sub_Normal_Bean();
		OperateJavaBean.setBeanPropertyWithApacheBeanUtils(subBean, "subName", "subName");
		OperateJavaBean.setBeanPropertyWithApacheBeanUtils(subBean, "parentName", "parentName");
		Object objValue = OperateJavaBean.getBeanPropertyWithApacheBeanUtils(subBean, "subName");
		System.out.println(objValue);
		objValue = OperateJavaBean.getBeanPropertyWithApacheBeanUtils(subBean, "parentName");
		System.out.println(objValue);
	}

	@Test
	public void testApacheBeanUtilsTypeConvert() throws Exception {
		Sub_Normal_Bean subBean = new Sub_Normal_Bean();
		OperateJavaBean.setBeanPropertyWithApacheBeanUtils(subBean, "subAge", "22");
		Object objValue = OperateJavaBean.getBeanPropertyWithApacheBeanUtils(subBean, "subAge");
		System.out.println(objValue);
	}

	@Test
	public void testApacheBeanUtilsOperateProperty() throws Exception {
		// date not null
		Sub_Normal_Bean subBean = new Sub_Normal_Bean("subName", 1, new Date());
		OperateJavaBean.setBeanPropertyWithApacheBeanUtils(subBean, "subDate.time",
				Long.toString(DateTimeCode.getDateFromStr("2015-1-2 3:4:5", DateTimeCode.FULL_DATETIME).getTime()));
		Object objValue = OperateJavaBean.getBeanPropertyWithApacheBeanUtils(subBean, "subDate.time");
		System.out.println(objValue);
		Date date = new Date(Long.parseLong(objValue.toString()));
		String dateStr = DateTimeCode.getStrFromDate(date, DateTimeCode.FULL_DATETIME);
		Assert.assertEquals("2015-01-02 03:04:05", dateStr);
	}

	@Test(expected = NoSuchMethodException.class)
	public void testGetBeanNoExistPropertyWithApacheBeanUtils() throws Exception {
		Sub_Normal_Bean subBean = new Sub_Normal_Bean();
		OperateJavaBean.getBeanPropertyWithApacheBeanUtils(subBean, "noExistField");
	}

	@Test
	public void testSetBeanNoExistPropertyWithApacheBeanUtils() throws Exception {
		Sub_Normal_Bean subBean = new Sub_Normal_Bean();
		OperateJavaBean.setBeanPropertyWithApacheBeanUtils(subBean, "noExistField", "noExistValue");
	}

	@Test
	public void testOperateBeanPropertyWithApachePropertyUtils() throws Exception {
		Sub_Normal_Bean subBean = new Sub_Normal_Bean();
		OperateJavaBean.setBeanPropertyWithApachePropertyUtils(subBean, "subName", "subName");
		OperateJavaBean.setBeanPropertyWithApachePropertyUtils(subBean, "parentName", "parentName");
		Object objValue = OperateJavaBean.getBeanPropertyWithApachePropertyUtils(subBean, "subName");
		System.out.println(objValue);
		objValue = OperateJavaBean.getBeanPropertyWithApachePropertyUtils(subBean, "parentName");
		System.out.println(objValue);
	}

	@Test(expected = NoSuchMethodException.class)
	public void testGetBeanNoExistPropertyWithApachePropertyUtils() throws Exception {
		Sub_Normal_Bean subBean = new Sub_Normal_Bean();
		OperateJavaBean.getBeanPropertyWithApachePropertyUtils(subBean, "noExistField");
	}

	@Test(expected = NoSuchMethodException.class)
	public void testSetBeanNoExistPropertyWithApachePropertyUtils() throws Exception {
		Sub_Normal_Bean subBean = new Sub_Normal_Bean();
		OperateJavaBean.setBeanPropertyWithApachePropertyUtils(subBean, "noExistField", "noExistValue");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testApachePropertyUtilsTypeConvert() throws Exception {
		Sub_Normal_Bean subBean = new Sub_Normal_Bean();
		OperateJavaBean.setBeanPropertyWithApachePropertyUtils(subBean, "subAge", "22");
	}

	@Test
	public void testApachePropertyUtilsOperateProperty() throws Exception {
		// date not null
		Sub_Normal_Bean subBean = new Sub_Normal_Bean("subName", 1, new Date());
		PropertyUtils.setProperty(subBean, "subDate.time",
				DateTimeCode.getDateFromStr("2015-1-2 3:4:5", DateTimeCode.FULL_DATETIME).getTime());
		Object objValue = OperateJavaBean.getBeanPropertyWithApachePropertyUtils(subBean, "subDate.time");
		System.out.println(objValue);
		Date date = new Date(Long.parseLong(objValue.toString()));
		String dateStr = DateTimeCode.getStrFromDate(date, DateTimeCode.FULL_DATETIME);
		Assert.assertEquals("2015-01-02 03:04:05", dateStr);
	}

	@Test
	public void testGetBeanNullPropertyNames() throws Exception {
		Sub_Normal_Bean subBean = new Sub_Normal_Bean("subName", 2,
				DateTimeCode.getDateFromStr("2015-1-2 4:5:6", DateTimeCode.FULL_DATETIME));
		String[] rtnArr = OperateJavaBean.getBeanNullPropertyNames(subBean);
		System.out.println(Arrays.toString(rtnArr));
	}

	@Test
	public void testCopyBeanWithApacheBeanUtils() throws Exception {
		Sub_Normal_Bean subBean = new Sub_Normal_Bean("parentName", 1,
				DateTimeCode.getDateFromStr("2015-1-2 3:4:5", DateTimeCode.FULL_DATETIME), "subName", 2,
				DateTimeCode.getDateFromStr("2015-1-2 4:5:6", DateTimeCode.FULL_DATETIME), 3, true);
		Sub_All_Bean destBean = new Sub_All_Bean();
		org.apache.commons.beanutils.BeanUtils.copyProperties(destBean, subBean);
		System.out.println(destBean);

		Normal_Pkg_Bean pkgBean = new Normal_Pkg_Bean();
		pkgBean.setShortVal((short) 0);
		Normal_Pkg_Bean destPkgBean = new Normal_Pkg_Bean();
		org.apache.commons.beanutils.BeanUtils.copyProperties(destPkgBean, pkgBean);
		System.out.println(destPkgBean);
	}

	@Test
	public void testCopyBeanWithSpringBeanUtils() throws Exception {
		Sub_Normal_Bean subBean = new Sub_Normal_Bean("parentName", 1,
				DateTimeCode.getDateFromStr("2015-1-2 3:4:5", DateTimeCode.FULL_DATETIME), "subName", 2,
				DateTimeCode.getDateFromStr("2015-1-2 4:5:6", DateTimeCode.FULL_DATETIME), 3, true);
		Sub_All_Bean destBean = new Sub_All_Bean();
		org.springframework.beans.BeanUtils.copyProperties(subBean, destBean);
		System.out.println(destBean);

		subBean = new Sub_Normal_Bean("subName", 2,
				DateTimeCode.getDateFromStr("2015-1-2 4:5:6", DateTimeCode.FULL_DATETIME));
		destBean = new Sub_All_Bean();
		org.springframework.beans.BeanUtils.copyProperties(subBean, destBean);
		System.out.println(destBean);

		Normal_Pkg_Bean pkgBean = new Normal_Pkg_Bean();
		pkgBean.setShortVal((short) 0);
		Normal_Pkg_Bean destPkgBean = new Normal_Pkg_Bean();
		org.springframework.beans.BeanUtils.copyProperties(pkgBean, destPkgBean);
		System.out.println(destPkgBean);

		String[] ignorePropArr = new String[] { "subDate", "parentDate" };
		subBean = new Sub_Normal_Bean("parentName", 1,
				DateTimeCode.getDateFromStr("2015-1-2 3:4:5", DateTimeCode.FULL_DATETIME), "subName", 2,
				DateTimeCode.getDateFromStr("2015-1-2 4:5:6", DateTimeCode.FULL_DATETIME), 3, true);
		destBean = new Sub_All_Bean();
		destBean.setParentDate(DateTimeCode.getDateFromStr("2016-1-2 4:5:6", DateTimeCode.FULL_DATETIME));
		destBean.setSubDate(DateTimeCode.getDateFromStr("2016-2-2 4:5:6", DateTimeCode.FULL_DATETIME));
		org.springframework.beans.BeanUtils.copyProperties(subBean, destBean, ignorePropArr);
		System.out.println(destBean);
	}

}
