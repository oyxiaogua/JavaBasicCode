package com.xiaogua.better.basic;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class OperateJavaBean {
	public static void setBeanPropertyWithPropertyDescriptor(Object bean, String fieldName, String fieldValue)
			throws Exception {
		PropertyDescriptor propDesc = new PropertyDescriptor(fieldName, bean.getClass());
		Method setMethod = propDesc.getWriteMethod();
		setMethod.invoke(bean, fieldValue);
	}

	public static Object getBeanPropertyWithPropertyDescriptor(Object bean, String fieldName) throws Exception {
		PropertyDescriptor proDescriptor = new PropertyDescriptor(fieldName, bean.getClass());
		Method getMethod = proDescriptor.getReadMethod();
		Object fieldValue = getMethod.invoke(bean);
		return fieldValue;
	}

	public static void setBeanPropertyWithIntrospector(Object bean, String fieldName, String fieldValue)
			throws Exception {
		BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
		PropertyDescriptor[] proDescrtptors = beanInfo.getPropertyDescriptors();
		if (proDescrtptors != null && proDescrtptors.length > 0) {
			for (PropertyDescriptor propDesc : proDescrtptors) {
				if (propDesc.getName().equals(fieldName)) {
					Method methodSetUserName = propDesc.getWriteMethod();
					methodSetUserName.invoke(bean, fieldValue);
					break;
				}
			}
		}
	}

	public static Object getBeanPropertyWithIntrospector(Object bean, String fieldName) throws Exception {
		BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
		PropertyDescriptor[] proDescrtptors = beanInfo.getPropertyDescriptors();
		if (proDescrtptors != null && proDescrtptors.length > 0) {
			for (PropertyDescriptor propDesc : proDescrtptors) {
				if (propDesc.getName().equals(fieldName)) {
					Method methodGetUserName = propDesc.getReadMethod();
					return methodGetUserName.invoke(bean);
				}
			}
		}
		return null;
	}

	public static void setBeanPropertyWithApacheBeanUtils(Object bean, String fieldName, String fieldValue)
			throws Exception {
		BeanUtils.setProperty(bean, fieldName, fieldValue);
	}

	public static Object getBeanPropertyWithApacheBeanUtils(Object bean, String fieldName) throws Exception {
		return BeanUtils.getProperty(bean, fieldName);
	}

	public static void setBeanPropertyWithApachePropertyUtils(Object bean, String fieldName, String fieldValue)
			throws Exception {
		PropertyUtils.setProperty(bean, fieldName, fieldValue);
	}

	public static Object getBeanPropertyWithApachePropertyUtils(Object bean, String fieldName) throws Exception {
		return PropertyUtils.getProperty(bean, fieldName);
	}

	public static String[] getBeanNullPropertyNames(Object bean) {
		final BeanWrapper src = new BeanWrapperImpl(bean);
		PropertyDescriptor[] pds = src.getPropertyDescriptors();
		Set<String> emptyNameSet = new HashSet<String>();
		for (PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNameSet.add(pd.getName());
		}
		return emptyNameSet.toArray(new String[0]);
	}

}
