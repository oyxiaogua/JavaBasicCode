package com.xiaogua.better.bean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Anno_B(valueB = "AnnoC_ValueB")
public @interface Anno_C {
	String valueC() default "Anno_C";
}
