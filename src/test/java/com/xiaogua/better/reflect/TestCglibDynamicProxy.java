package com.xiaogua.better.reflect;

import org.junit.Test;

import com.xiaogua.better.bean.HelloImplWithOutInterface;
import com.xiaogua.better.bean.Interface_SayHello;
import com.xiaogua.better.bean.Simple_HelloImpl;

import net.sf.cglib.core.DebuggingClassWriter;

public class TestCglibDynamicProxy {
	@Test
	public void testCglibDynamicProxyHello() throws Exception {
		//保存生成的代理class文件
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "e:/test_tmp/cglib_proxy");
		CglibDynamicProxy proxy = new CglibDynamicProxy();
		// 注意: 原始类的instance不需要存在，只需要Class类型
		Interface_SayHello helloAProxy = (Interface_SayHello) proxy.generateProxy(Simple_HelloImpl.class);
		helloAProxy.hello();

		System.out.println("-------------------------------------------------");
		// 代理没有实现任何interface的类
		HelloImplWithOutInterface helloCProxy = (HelloImplWithOutInterface) proxy
				.generateProxy(HelloImplWithOutInterface.class);
		helloCProxy.hello();
	}
}
