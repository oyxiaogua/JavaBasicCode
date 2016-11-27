package com.xiaogua.better.test;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;


public class TestSystemRule {
	@Rule
	public final SystemOutRule log = new SystemOutRule().enableLog();

	@Test
	public void testConsoleOutput() {
		log.clearLog();
		System.out.print("Hello");
		Assert.assertThat(log.getLog(), Is.is("Hello"));
	}
}
