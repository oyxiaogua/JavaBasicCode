package com.xiaogua.better.bean;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name", scope = TreeNodeBean_2.class)
public class TreeNodeBean_2 {
	private String name;
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name", scope = TreeNodeBean_2.class)
	private TreeNodeBean_2 parent;
	private List<TreeNodeBean_2> children;

	public TreeNodeBean_2() {
	}

	public TreeNodeBean_2(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TreeNodeBean_2 getParent() {
		return parent;
	}

	public void setParent(TreeNodeBean_2 parent) {
		this.parent = parent;
	}

	public List<TreeNodeBean_2> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNodeBean_2> children) {
		this.children = children;
	}

	public void addChild(TreeNodeBean_2 child) {
		if (children == null)
			children = new ArrayList<TreeNodeBean_2>();
		children.add(child);
	}

}
