package com.xiaogua.better.bean;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.apache.log4j.Logger;

public class ExternalizableBean implements Externalizable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SerializableBean.class);
	private int id;
	private String name;
	private String address;
	private short age;
	private double salary;

	public ExternalizableBean() {
		super();
	}

	public ExternalizableBean(int id, String name, String address, short age, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.age = age;
		this.salary = salary;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		logger.error("------start writeExternal");
		out.writeUTF("------------start externalizable----------");
		out.writeInt(id);
		out.writeUTF(name);
		out.writeUTF(address);
		out.writeShort(age);
		// do something
		out.writeUTF(Double.toHexString(salary));
		out.writeUTF("------------end externalizable----------");
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		logger.error("------start readExternal");
		String str = in.readUTF();
		System.out.println(str);
		id = in.readInt();
		name = in.readUTF();
		address = in.readUTF();
		age = in.readShort();
		str = in.readUTF();
		salary = Double.valueOf(str);
		str = in.readUTF();
		System.out.println(str);
	}

	public String toString() {
		return "ExternalizableBean [id=" + id + ", name=" + name + ", address=" + address + ", age=" + age + ",salary="
				+ salary + "]";
	}

}
