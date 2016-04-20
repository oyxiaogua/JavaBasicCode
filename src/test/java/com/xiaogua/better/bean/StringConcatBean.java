package com.xiaogua.better.bean;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class StringConcatBean {
	private int age;
	private String name;
	private String address;
	private List<String> phone;
	private BigDecimal salary;
	private Map<String, Object> attrs;

	public StringConcatBean(int age, String name, String address, List<String> phone, BigDecimal salary,
			Map<String, Object> attrs) {
		super();
		this.age = age;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.salary = salary;
		this.attrs = attrs;
	}

	/**
	 * 使用字符串+
	 */
	public String useConcatToStr() {
		return "StringConcatBean{" + "age=" + age + ", name='" + name + '\'' + ", address='" + address + '\''
				+ ", phone=" + phone + ", salary=" + salary + ", attrs=" + attrs + '}';
	}

	/**
	 * 使用StringBuilder
	 */
	public String useStringBuilderToStr() {
		StringBuilder builder = new StringBuilder("StringConcatBean{");
		builder.append("age=").append(age).append(", name='").append(name).append("'").append(", address='")
				.append(address).append("'").append(", phone=").append(phone).append(", salary=").append(salary)
				.append(", attrs=").append(attrs).append("}");
		return builder.toString();
	}

	/**
	 * 使用StringBuffer
	 */
	public String useStringBufferToStr() {
		StringBuffer buffer = new StringBuffer("StringConcatBean{");
		buffer.append("age=").append(age).append(", name='").append(name).append("'").append(", address='")
				.append(address).append("'").append(", phone=").append(phone).append(", salary=").append(salary)
				.append(", attrs=").append(attrs).append("}");
		return buffer.toString();
	}

	/**
	 * Guava的ToStringHelper
	 */
	public String userGuavaToStringHelperToStr() {
		return com.google.common.base.MoreObjects.toStringHelper(this).add("age", age).add("name", name)
				.add("address", address).add("phone", phone).add("salary", salary).add("attrs", attrs).toString();
	}

	/**
	 * apache.commons.lang3的ToStringBuilder
	 */
	public String useCommonLang3ToStringBuilderToString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("age", age).append("name", name)
				.append("address", address).append("phone", phone).append("salary", salary).append("attrs", attrs)
				.toString();
	}

	/**
	 * apache.commons.lang3的ReflectionToStringBuilder
	 */
	public String useCommonLang3ReflectionToStringBuilderToString() {
		return ReflectionToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
