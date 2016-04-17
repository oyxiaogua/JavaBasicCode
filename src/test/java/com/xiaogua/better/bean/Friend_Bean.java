package com.xiaogua.better.bean;

public class Friend_Bean {
	private Integer id;
	private String friendName;

	public Friend_Bean() {
		super();
	}

	public Friend_Bean(Integer id, String friendName) {
		super();
		this.id = id;
		this.friendName = friendName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public String toString() {
		return "Friend_Bean [id=" + id + ", friendName=" + friendName + "]";
	}

}
