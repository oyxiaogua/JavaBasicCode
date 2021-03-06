package com.xiaogua.better.bean;

public class Dept_2_Bean implements Cloneable {
	private int id2;
	private String name2;
	private Dept_1_Bean dept1Bean;
	private Dept_1_Bean[] dept1BeanArr;

	public int getId2() {
		return id2;
	}

	public void setId2(int id2) {
		this.id2 = id2;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public Dept_1_Bean getDept1Bean() {
		return dept1Bean;
	}

	public void setDept1Bean(Dept_1_Bean dept1Bean) {
		this.dept1Bean = dept1Bean;
	}

	public Dept_2_Bean() {
		super();
	}

	public Dept_1_Bean[] getDept1BeanArr() {
		return dept1BeanArr;
	}

	public void setDept1BeanArr(Dept_1_Bean[] dept1BeanArr) {
		this.dept1BeanArr = dept1BeanArr;
	}

	public Dept_2_Bean(int id2, String name2, Dept_1_Bean dept1Bean, Dept_1_Bean[] dept1BeanArr) {
		super();
		this.id2 = id2;
		this.name2 = name2;
		this.dept1Bean = dept1Bean;
		this.dept1BeanArr = dept1BeanArr;
	}

	public Object clone() throws CloneNotSupportedException {
		Dept_2_Bean bean = (Dept_2_Bean) super.clone();
		Dept_1_Bean obj = bean.getDept1Bean();
		if (obj != null) {
			bean.setDept1Bean((Dept_1_Bean) obj.clone());
		}
		Dept_1_Bean[] srcArr = bean.getDept1BeanArr();
		if (srcArr != null) {
			Dept_1_Bean[] destArr = new Dept_1_Bean[srcArr.length];
			for (int i = 0, len = srcArr.length; i < len; i++) {
				if (srcArr[i] != null) {
					destArr[i] = (Dept_1_Bean) srcArr[i].clone();
				}
			}
			bean.setDept1BeanArr(destArr);
		}
		return bean;
	}

	public String printDept2Info() {
		StringBuilder infoBuilder = new StringBuilder();
		infoBuilder.append(" [id2=").append(id2).append(", name2=").append(name2).append(", dept1Bean=")
				.append(dept1Bean).append(",dept1BeanArr=[");
		if (dept1BeanArr == null || dept1BeanArr.length == 0) {
			infoBuilder.append(dept1BeanArr);
		} else {
			for (int i = 0, len = dept1BeanArr.length; i < len; i++) {
				infoBuilder.append("index=").append(i).append(",address=").append(dept1BeanArr[i]).append(",");
			}
			infoBuilder.deleteCharAt(infoBuilder.length() - 1);
		}
		infoBuilder.append("]]");
		return infoBuilder.toString();
	}
}
