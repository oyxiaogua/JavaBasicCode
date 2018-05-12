package com.xiaogua.better.bean;

public enum Enum_Order {
	ONE(1), TWO(2), THREE(3);
	private final int numberOrder;
	Enum_Order(int size) {
		this.numberOrder = size;
	}
	public int getNumberOrder() {
		return numberOrder;
	}
}
