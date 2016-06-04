package com.xiaogua.better.bean;

import java.util.List;

public class ListBeanWapper {
	private List<Parent_Normal_Bean> beanList;

	public ListBeanWapper() {
		super();
	}

	public ListBeanWapper(List<Parent_Normal_Bean> beanList) {
		super();
		this.beanList = beanList;
	}

	public List<Parent_Normal_Bean> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<Parent_Normal_Bean> beanList) {
		this.beanList = beanList;
	}

	public String toString() {
		return " [beanList=" + beanList + "]";
	}

}
