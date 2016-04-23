package com.xiaogua.better.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JavaDynamicProxy implements InvocationHandler {
	/**
	 * 被代理者
	 */
	private Object inner;

	/**
	 * 生成代理类
	 */
	public static Object generateProxy(Object inner) {
		return Proxy.newProxyInstance(inner.getClass().getClassLoader(), inner.getClass().getInterfaces(),
				new JavaDynamicProxy(inner));
	}

	private JavaDynamicProxy(Object inner) {
		this.inner = inner;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		doSomethingBefore();
		Object result = method.invoke(inner, args);
		doSomethingAfter();
		return result;
	}

	private void doSomethingBefore() {
		System.out.println("JavaDynamicProxy: Before...");
	}

	private void doSomethingAfter() {
		System.out.println("JavaDynamicProxy: After...");
	}
}