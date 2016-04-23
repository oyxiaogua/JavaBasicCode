package com.xiaogua.better.bean;

public abstract class Static_Inner_Class {

	@SuppressWarnings("unused")
	private static class Inner_Static_Class {
		private Inner_Static_Class(String name) {
			super();
			this.name = name;
		}

		private String name;

		private void setName(String name) {
			this.name = name;
		}

		private String getNameInfo() {
			return "Inner_Static_Class,name=" + name;
		}

	}
}
