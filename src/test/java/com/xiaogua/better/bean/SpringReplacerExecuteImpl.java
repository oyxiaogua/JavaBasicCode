package com.xiaogua.better.bean;

import java.lang.reflect.Method;

import org.springframework.beans.factory.support.MethodReplacer;

public class SpringReplacerExecuteImpl implements MethodReplacer {

	public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
		if(args.length==0){
			return "spring replace method";
		}
		return "spring replace method with param=" + args[0];
	}

}