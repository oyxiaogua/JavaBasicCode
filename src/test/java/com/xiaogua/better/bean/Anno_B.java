package com.xiaogua.better.bean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Anno_A(valueA="AnnoB_ValueA")
public @interface Anno_B {
	String valueB() default "Anno_B";
}
