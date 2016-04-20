package com.xiaogua.better.class_init;

public class ReferenceVarFinalClass {
	final int[] referVar_final;
	static ReferenceVarFinalClass instance;

	public ReferenceVarFinalClass() {
		// 对referVar_final引用进行初始化操作;
		referVar_final = new int[1];
		referVar_final[0] = 1;
	}

	// 对instance对象进行实例化
	public static void writerInstance() {
		instance = new ReferenceVarFinalClass();
	}

	// 改变referVar_final对象状态
	public static void writerReferVar() {
		instance.referVar_final[0] = 2;
	}

	// 读取referVar_final状态
	public static void reader() {
		if (instance != null)
			System.out.println(
					Thread.currentThread().getName() + ", referVar_final values: " + instance.referVar_final[0]);
	}
}
