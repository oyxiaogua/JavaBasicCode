package com.xiaogua.web.spring;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.xiaogua.web.service.TestSpringValue;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/test/resources/spring/spring-all.xml")
public class TestSpringValueAnnotation {

	@Autowired
	private TestSpringValue testSpringValue;

	@Test
	public void testSpringValue() {
		String str = testSpringValue.getJdbcUrl();
		System.out.println("url=" + str);

		str = testSpringValue.getJdbcUrl2();
		System.out.println("url2=" + str);

		str = testSpringValue.getJdbcUrl3();
		System.out.println("url3=" + str);

		str = testSpringValue.getCnValue();
		System.out.println("cn value=" + str);

		str = testSpringValue.getProp3Key();
		System.out.println("prop3 value=" + str);

		System.out.println("-----------------null value-----------");
		str = testSpringValue.getNullValue();
		Assert.assertNull(str);

		str = testSpringValue.getNullValue2();
		Assert.assertNull(str);

		str = testSpringValue.getNullValue3();
		Assert.assertNull(str);
	}
}
