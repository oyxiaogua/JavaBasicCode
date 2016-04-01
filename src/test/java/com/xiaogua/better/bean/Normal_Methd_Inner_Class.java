package com.xiaogua.better.bean;

public class Normal_Methd_Inner_Class {
	public InterfaceGetName getMthInnerClass() {
		class MthInnerClassGetName implements InterfaceGetName {
			public String getName() {
				return "method_inner_class";
			}
		}
		return new MthInnerClassGetName();
	}
}
