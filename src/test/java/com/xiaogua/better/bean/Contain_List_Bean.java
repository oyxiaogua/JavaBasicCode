package com.xiaogua.better.bean;

import java.util.List;

public class Contain_List_Bean {

	private Integer id;
	private String name;
	private List<Friend_Bean> friendList;

	public Contain_List_Bean() {
		super();
	}

	public Contain_List_Bean(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Friend_Bean> getFriendList() {
		return friendList;
	}

	public void setFriendList(List<Friend_Bean> friendList) {
		this.friendList = friendList;
	}

}
