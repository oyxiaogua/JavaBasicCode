package com.xiaogua.better.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestChangeBooleanValue {
	@Before
	public void changeBooleanValue() {
		try {
			Field f = Boolean.class.getDeclaredField("TRUE");
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(f, f.getModifiers() & ~Modifier.FINAL);

			f.setAccessible(true);
			f.set(Boolean.class, Boolean.FALSE);
		} catch (Exception ex) {
		}
	}

	@Test
	public void testChangeBooleanValue() {
		boolean trueBoolean = Boolean.valueOf("true");
		Assert.assertFalse(trueBoolean);
	}
}
