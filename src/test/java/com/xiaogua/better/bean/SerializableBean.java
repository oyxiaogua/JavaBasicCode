package com.xiaogua.better.bean;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.log4j.Logger;

public class SerializableBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SerializableBean.class);
	private int id;
	private String name;
	private String address;
	private short age;
	private transient double salary;// not serializable

	public SerializableBean(int id, String name, String address, short age, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.age = age;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public short getAge() {
		return age;
	}

	public void setAge(short age) {
		this.age = age;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		logger.error("------start writeObject");
		out.writeUTF("------------start serializable----------");
		out.defaultWriteObject();
		// do something
		out.writeUTF(Double.toHexString(salary));
		out.writeUTF("------------end serializable----------");
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		logger.error("------start readObject");
		String str = in.readUTF();
		System.out.println(str);
		in.defaultReadObject();
		String tmpStr = in.readUTF();
		salary = Double.valueOf(tmpStr);
		str = in.readUTF();
		System.out.println(str);
	}

	public String toString() {
		return "SerializableBean [id=" + id + ", name=" + name + ", address=" + address + ", age=" + age + ",salary="
				+ salary + "]";
	}

}
