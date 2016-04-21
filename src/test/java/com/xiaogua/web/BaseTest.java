package com.xiaogua.web;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseTest {
	public static ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-all.xml");
}
