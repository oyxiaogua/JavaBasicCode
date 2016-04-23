package com.xiaogua.better.reflect;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibDynamicProxy implements MethodInterceptor {
	/**
	 * 动态生成代理类
	 */
	public Object generateProxy(Class<?> cls) {
		Enhancer enhancer = new Enhancer();
		enhancer.setCallback(this);
		// enhancer.setCallbackFilter(); //filter
		enhancer.setSuperclass(cls);
		return enhancer.create(); // Enhancer也包含带参数的create方法
	}

	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		doSomethingBefore();
		Object result = proxy.invokeSuper(obj, args);
		doSomethingAfter();
		return result;
	}

	private void doSomethingBefore() {
		System.out.println("CglibDynamicProxy: Before...");
	}

	private void doSomethingAfter() {
		System.out.println("CglibDynamicProxy: After...");
	}
}
