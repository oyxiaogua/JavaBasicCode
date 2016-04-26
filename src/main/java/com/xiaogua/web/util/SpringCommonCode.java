package com.xiaogua.web.util;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;

public class SpringCommonCode {

	/**
	 * 获取真实的被代理类
	 */
	public static Object unwrapProxy(Object bean) throws Exception {
		if (AopUtils.isAopProxy(bean) && bean instanceof Advised) {
			Advised advised = (Advised) bean;
			bean = advised.getTargetSource().getTarget();
		}
		return bean;
	}
}
