package com.xiaogua.web.test;

import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestQuartz {
	public static ApplicationContext context;

	@BeforeClass
	public static void initContext() {
		context = new ClassPathXmlApplicationContext("spring/spring-quartz.xml");
	}

	@Test
	public void testPrintTimeTask() throws Exception {
		context.getBean("springJobSchedulerFactoryBean");
		TimeUnit.MINUTES.sleep(3);
	}

}
