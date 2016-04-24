package com.xiaogua.better.bean;

public abstract class Abs_ExecuteManager {

	public Object process() {
		Interface_Execute execute = createExecute();
		System.out.println("print self address=" + execute);
		return execute.execute();
	}

	public String process(String command) {
		Interface_Execute execute = createExecute();
		System.out.println("print self address=" + execute);
		return execute.execute(command);
	}

	public abstract Interface_Execute createExecute();

	public abstract Interface_Execute createExecute(String param);

}