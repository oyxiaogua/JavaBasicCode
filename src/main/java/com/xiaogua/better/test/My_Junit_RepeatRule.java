package com.xiaogua.better.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class My_Junit_RepeatRule implements TestRule {
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ java.lang.annotation.ElementType.METHOD })
	public @interface My_Junit_Repeat {
		public abstract int times();
	}

	private static class MyJunitRepeatStatement extends Statement {
		private final int times;
		private final Statement statement;

		private MyJunitRepeatStatement(int times, Statement statement) {
			this.times = times;
			this.statement = statement;
		}

		public void evaluate() throws Throwable {
			for (int i = 0; i < times; i++) {
				statement.evaluate();
			}
		}
	}

	public Statement apply(Statement statement, Description description) {
		Statement result = statement;
		My_Junit_Repeat repeat = description.getAnnotation(My_Junit_Repeat.class);
		if (repeat != null) {
			int times = repeat.times();
			result = new MyJunitRepeatStatement(times, statement);
		}
		return result;
	}
}
