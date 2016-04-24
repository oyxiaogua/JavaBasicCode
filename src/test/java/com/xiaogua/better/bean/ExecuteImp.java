package com.xiaogua.better.bean;

public class ExecuteImp implements Interface_Execute {

	public Object execute() {
		return "execute say hello";
	}

	public String execute(String command) {
		return "execute " + command;
	}

}
