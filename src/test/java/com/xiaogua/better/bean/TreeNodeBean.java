package com.xiaogua.better.bean;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class TreeNodeBean {
	private String name;
	@JsonBackReference
	private TreeNodeBean parent;
	@JsonManagedReference
	private List<TreeNodeBean> children;

	public TreeNodeBean() {
	}

	public TreeNodeBean(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TreeNodeBean getParent() {
		return parent;
	}

	public void setParent(TreeNodeBean parent) {
		this.parent = parent;
	}

	public List<TreeNodeBean> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNodeBean> children) {
		this.children = children;
	}

	public void addChild(TreeNodeBean child) {
		if (children == null)
			children = new ArrayList<TreeNodeBean>();
		children.add(child);
	}

}
