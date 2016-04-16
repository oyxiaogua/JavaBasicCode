package com.xiaogua.better.bean;

public class Super_Animal implements Comparable<Super_Animal> {
	protected int age;

	public Super_Animal(int age)

	{
		this.age = age;
	}

	public int compareTo(Super_Animal other) {
		return this.age - other.age;
	}

	public String toString() {
		return "Super_Animal [age=" + age + "]";
	}

}