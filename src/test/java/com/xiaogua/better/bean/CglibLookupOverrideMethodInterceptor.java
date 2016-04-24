package com.xiaogua.better.bean;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibLookupOverrideMethodInterceptor implements MethodInterceptor {

	public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		if ("createExecute".equals(method.getName())) {
			return new ExecuteImp();
		}
		return methodProxy.invokeSuper(obj, args);
	}
}