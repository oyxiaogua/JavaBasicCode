package com.xiaogua.better.bean;

import java.util.List;

public class With_InnerClass_Prop_Class {
	private String name;
	private Inner_Hello_Class innerClz;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Inner_Hello_Class getInnerClz() {
		return innerClz;
	}

	public void setInnerClz(String hello, List<String> user) {
		Inner_Hello_Class h = new Inner_Hello_Class();
		h.setHello(hello);
		h.setUser(user);
		this.innerClz = h;
	}

	public String toString() {
		return "With_InnerClass_Prop_Class{" + "name='" + name + '\'' + ", hello=" + innerClz + '}';
	}

	public class Inner_Hello_Class {
		private String hello;
		private List<String> user;

		@SuppressWarnings("unused")
		public String getHello() {
			return hello;
		}

		public void setHello(String hello) {
			this.hello = hello;
		}

		@SuppressWarnings("unused")
		public List<String> getUser() {
			return user;
		}

		public void setUser(List<String> user) {
			this.user = user;
		}

		public String toString() {
			return "Inner_Hello_Class{" + "hello='" + hello + '\'' + ", user=" + user + '}';
		}
	}

}