package com.xiaogua.better.bean;

public class BuildClass {
	private final int id;
	private final String name;
	private final int age;
	private final String sex;
	private final String address;

	public BuildClass(Builder builder) {
		id = builder.id;
		name = builder.name;
		age = builder.age;
		sex = builder.sex;
		address = builder.address;
	}

	public static class Builder {
		private int id;
		private String name;
		private int age;
		private String sex;
		private String address;

		public Builder setId(int id) {
			this.id = id;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setAge(int age) {
			this.age = age;
			return this;
		}

		public Builder setSex(String sex) {
			this.sex = sex;
			return this;
		}

		public Builder setAddress(String address) {
			this.address = address;
			return this;
		}

		public BuildClass build() {
			return new BuildClass(this);
		}
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getSex() {
		return sex;
	}

	public String getAddress() {
		return address;
	}

	public String toString() {
		return "BuildClass [id=" + id + ", name=" + name + ", age=" + age + ", sex=" + sex + ", address=" + address
				+ "]";
	}

}
