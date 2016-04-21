package com.xiaogua.better.bean;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.log4j.Logger;

public class SerializableAndExtBean implements Serializable, Externalizable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SerializableBean.class);
	private int id;
	private String name;
	private String address;
	private short age;
	private double salary;

	public SerializableAndExtBean() {
		super();
	}

	public SerializableAndExtBean(int id, String name, String address, short age, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.age = age;
		this.salary = salary;
	}

	public String toString() {
		return "SerializableAndExtBean [id=" + id + ", name=" + name + ", address=" + address + ", age=" + age
				+ ", salary=" + salary + "]";
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

}
