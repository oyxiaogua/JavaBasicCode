package com.xiaogua.web.spring;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xiaogua.better.bean.Abs_ExecuteManager;
import com.xiaogua.better.bean.Interface_Execute;
import com.xiaogua.web.util.SpringCommonCode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-inject.xml")
public class TestSpringInject {

	private static final Logger log = Logger.getLogger(TestSpringInject.class);
	@Autowired
	private Abs_ExecuteManager executeManager;
	@Resource(name = "executeImpl2")
	private Interface_Execute execute;

	@Test
	public void testSpringLookUpMethodInjection() {
		Object rtnObj = null;
		for (int i = 0; i < 3; i++) {
			rtnObj = executeManager.process();
			log.error(rtnObj);
		}
		log.error("-----------split line--------------");
		for (int i = 0; i < 3; i++) {
			rtnObj = executeManager.process("sit down");
			log.error(rtnObj);
		}
	}

	@Test
	public void testSpringReplacerInjection() throws Exception {
		Object rtnObj = null;
		for (int i = 0; i < 3; i++) {
			rtnObj = execute.execute();
			log.error(i + ",rtnValue=" + rtnObj);
		}
		log.error("-----------split line--------------");
		for (int i = 0; i < 3; i++) {
			rtnObj = execute.execute("sit down");
			log.error(i + ",rtnValue=" + rtnObj);
		}
	}
}
