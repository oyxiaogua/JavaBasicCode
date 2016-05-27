package com.xiaogua.other;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.compiler.ExecutableAccessor;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;
import org.mvel2.templates.CompiledTemplate;
import org.mvel2.templates.TemplateCompiler;
import org.mvel2.templates.TemplateRuntime;

import com.xiaogua.better.bean.Address_Normal_Bean;
import com.xiaogua.better.bean.Employee_Normal_Bean;

public class TestMvel {
	private Logger log = Logger.getLogger(TestMvel.class);

	@Test
	public void testMvelExecuteExpression() {
		Map<String, Object> varMap = new HashMap<String, Object>();
		varMap.put("x", 5);
		varMap.put("y", 2);

		int intRtn = (int) MVEL.eval("x * y", varMap);
		log.info("intRtn=" + intRtn);

		String expStr = "x>99";
		Boolean boolRtn = (Boolean) MVEL.eval(expStr, varMap);
		log.info("boolRtn=" + boolRtn);

		ExecutableAccessor compiled = (ExecutableAccessor) MVEL.compileExpression("x * y");
		intRtn = (int) MVEL.executeExpression(compiled, varMap);
		log.info("intResult=" + intRtn);

		varMap.put("x", new BigDecimal("0.3"));
		varMap.put("y", 123456);
		BigDecimal doubleRtn = (BigDecimal) MVEL.eval("y * x", varMap);
		log.info("doubleRtn=" + doubleRtn);

		varMap.put("emp", new Employee_Normal_Bean(1, null, new Address_Normal_Bean(null)));
		String streetName = MVEL.evalToString("emp.?address.?streetName", varMap);
		log.info("streetName=" + streetName);

		String strRtn = MVEL.evalToString("emp==null?null:emp.name", varMap);
		log.info("strRtn=" + strRtn);

		strRtn = MVEL.evalToString("emp==null?null:emp.getName()", varMap);
		log.info("strRtn=" + strRtn);
	}

	@Test
	public void testMvelEvelFile() throws Exception {
		File scriptFile = new File("src/test/resources/test_file/addTwo.mvel");
		VariableResolverFactory resolverFactory = new MapVariableResolverFactory();
		MVEL.evalFile(scriptFile, ParserContext.create(), resolverFactory);
		resolverFactory.createVariable("x", 10);
		resolverFactory.createVariable("y", 20);
		Object objRtn = MVEL.eval("addTwo(x,y);", resolverFactory);
		log.info(objRtn);
	}

	@Test
	public void testMvelStrongTyping() {
		ParserContext ctx = new ParserContext();
		ctx.setStrongTyping(true);
		ctx.addInput("str", String.class);
		String expStr = "str.toUpperCase()";
		Serializable serializable = MVEL.compileExpression(expStr, ctx);
		Map<String, String> varMap = new HashMap<String, String>();
		varMap.put("str", "test");
		String rtnStr = MVEL.executeExpression(serializable, varMap, String.class);
		log.info("str=" + rtnStr);

		String str = "test";
		Object rtnObj = MVEL.eval("toUpperCase()", str);
		log.info("rtnObj=" + rtnObj);

	}

	@Test
	public void testMvelTemplateRuntime() {
		String template = "name= @{name==null?'':name.toUpperCase()}";
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("name", "a");
		String str = (String) TemplateRuntime.eval(template, vars);
		log.info(str);
	}

	@Test
	public void testMvelCompiledTemplate() {
		String template = "123456*0.3 = @{123456*0.3}";
		CompiledTemplate compiled = TemplateCompiler.compileTemplate(template);
		String str = (String) TemplateRuntime.execute(compiled);
		log.info("str=" + str);

		Map<String, Object> varMap = new HashMap<String, Object>();
		varMap.put("userName", "test");
		template = "@if{userName!=null && userName!=''}@{userName} @else{}@end{}";
		compiled = TemplateCompiler.compileTemplate(template);
		str = (String) TemplateRuntime.execute(compiled, varMap);
		log.info("str=" + str);

		varMap.put("userName", "    ");
		template = "@if{com.xiaogua.better.str.StringCommonUtils.isNotNull(userName)}@{userName}@else{}@end{}";
		compiled = TemplateCompiler.compileTemplate(template);
		str = (String) TemplateRuntime.execute(compiled, varMap);
		log.info("str=" + str);
	}

	@Test
	public void testMvelListArrMap() {
		String expression = "['one','two',null,'three']";
		List<String> list = (List<String>) MVEL.eval(expression);
		System.out.println(list);

		expression = "{'one','two',null,'three'}";
		Object obj = MVEL.eval(expression);
		if (obj.getClass().isArray()) {
			Object[] strArr = (Object[]) obj;
			System.out.println(Arrays.toString(strArr));
		}

		expression = "['one' : 'oneValue', 'two' : null,null:null]";
		Map map = (Map) MVEL.eval(expression);
		System.out.println(map);

	}
}
