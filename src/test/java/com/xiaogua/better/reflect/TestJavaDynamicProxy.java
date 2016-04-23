package com.xiaogua.better.reflect;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.xiaogua.better.bean.Interface_SayHello;
import com.xiaogua.better.bean.Simple_HelloImpl;

public class TestJavaDynamicProxy {

	@Test
	public void testJavaDynamicProxyHello() throws Exception {
		// 被动态代理的IHello实例对象A
		Interface_SayHello helloA = new Simple_HelloImpl("testName");
		// 生成对象A的动态代理
		Interface_SayHello helloAProxy = (Interface_SayHello) JavaDynamicProxy.generateProxy(helloA);
		helloAProxy.hello();

		@SuppressWarnings("restriction")
		//生成代理类,保存代理类的字节码
		byte[] fileClass = sun.misc.ProxyGenerator.generateProxyClass("$Proxy0",
				Simple_HelloImpl.class.getInterfaces());
		OutputStream out = new FileOutputStream(new File("e:/test_tmp/sys_" + System.currentTimeMillis() + ".class"));
		IOUtils.write(fileClass, out);
		IOUtils.closeQuietly(out);
	}
}
