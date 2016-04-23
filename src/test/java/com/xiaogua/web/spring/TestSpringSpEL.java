package com.xiaogua.web.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class TestSpringSpEL {

	private ExpressionParser parser = new SpelExpressionParser();

	@Test
	public void testSpringSpELHelloWorld() {
		Expression expression = parser.parseExpression("'Testing Spring Expression Framework'");
		String message = (String) expression.getValue();
		System.out.println("Message is " + message);
	}

	@Test
	public void testSpringSpELConstantValue() {
		// SpEL支持的字面量包括字符串,数字类型,bool类型,null类型,字符串必须要单引号括起来
		String str = (String) parser.parseExpression("'Hello World'").getValue();
		double doubleValue = (Double) parser.parseExpression("6.0221415E+23").getValue();
		int maxValue = (Integer) parser.parseExpression("0x7FFFFFFF").getValue();
		boolean trueValue = (Boolean) parser.parseExpression("true").getValue();
		Object nullValue = parser.parseExpression("null").getValue();

		Assert.assertEquals("Hello World", str);
		System.out.println(doubleValue);
		System.out.println(maxValue);
		System.out.println(trueValue);
		Assert.assertNull(nullValue);
	}

	@Test
	public void testSpringSpELArithmeticExpression() {
		int intRt = parser.parseExpression("1+2-3*4/2").getValue(Integer.class);
		int intRt2 = parser.parseExpression("4%3").getValue(Integer.class);
		int intRt3 = parser.parseExpression("2^3").getValue(Integer.class);
		Assert.assertEquals(-3, intRt);
		Assert.assertEquals(1, intRt2);
		Assert.assertEquals(8, intRt3);
	}

	@Test
	public void testSpringSpELRelationalExpression() {
		// 等于(== eq)、不等于(!=ne)、大于(>gt)、大于等于(>=ge)、小于(<lt)、小于等于(<=le)，区间(between)
		boolean booleanRt = parser.parseExpression("1>2").getValue(boolean.class);
		boolean booleanRt2 = parser.parseExpression("1 between {1, 2}").getValue(boolean.class);
		boolean booleanRt3 = parser.parseExpression("1.139 between {1.1, 1.14}").getValue(boolean.class);
		boolean booleanRt4 = parser.parseExpression("1 lt 1.001").getValue(boolean.class);

		Assert.assertFalse(booleanRt);
		Assert.assertTrue(booleanRt2);
		Assert.assertTrue(booleanRt3);
		Assert.assertTrue(booleanRt4);
	}

	@Test
	public void testSpringSpELLogicalExpression() {
		String expStr = "2>1 and (!true or !false)";
		boolean booleanRt = parser.parseExpression(expStr).getValue(boolean.class);
		String expStr2 = "2>1 and (not true or not false)";
		boolean booleanRt2 = parser.parseExpression(expStr2).getValue(boolean.class);
		Assert.assertTrue(booleanRt);
		Assert.assertTrue(booleanRt2);
	}

	@Test
	public void testSpringSpELStringOperate() {
		String expStr = "'Hello' + '''Spring''' + 'SpEL.'";
		String strRt = parser.parseExpression(expStr).getValue(String.class);
		Assert.assertEquals("Hello'Spring'SpEL.", strRt);

		expStr = "'Hello Spring SpEL'[1]";
		expStr = parser.parseExpression(expStr).getValue(String.class);
		Assert.assertEquals("e", expStr);
	}

	@Test
	public void testSpringSpELTernaryOperator() {
		int intRt = parser.parseExpression("2>3?(3>4?5:6):(7<=8?9:10)").getValue(int.class);
		Assert.assertEquals(9, intRt);
	}

	@Test
	public void testSpringSpELRegularExpression() {
		boolean booleanRtn = parser.parseExpression("'123' matches '\\d{3}'").getValue(boolean.class);
		Assert.assertTrue(booleanRtn);
	}

	@Test
	public void testSpringSpELBracketPriorityExpression() {
		int intRt = parser.parseExpression("1+2-3*4/2").getValue(Integer.class);
		int intRt2 = parser.parseExpression("1+(2-3)*4/2").getValue(Integer.class);
		Assert.assertEquals(-3, intRt);
		Assert.assertEquals(-1, intRt2);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testSpringSpELClassTypeExpression() {
		// java.lang包类访问
		Class<String> rtClz = parser.parseExpression("T(String)").getValue(Class.class);
		Assert.assertEquals("java.lang.String", rtClz.getName());
		// 其他包类访问
		String expStr = "T(com.xiaogua.web.spring.TestSpringSpEL)";
		Class<String> rtClz2 = parser.parseExpression(expStr).getValue(Class.class);
		Assert.assertEquals("com.xiaogua.web.spring.TestSpringSpEL", rtClz2.getName());
		// 类静态字段访问
		int intRt = parser.parseExpression("T(Integer).MAX_VALUE").getValue(int.class);
		Assert.assertEquals(Integer.MAX_VALUE, intRt);
		// 类静态方法调用
		int intRt2 = parser.parseExpression("T(Integer).parseInt('1')").getValue(int.class);
		Assert.assertEquals(1, intRt2);
	}

	@Test
	public void testSpringSpELClassInstance() {
		String strRt = parser.parseExpression("new String('Spring SpEL')").getValue(String.class);
		Assert.assertEquals("Spring SpEL", strRt);

		Date result2 = parser.parseExpression("new java.util.Date()").getValue(Date.class);
		System.out.println("result2=" + result2);
	}

	@Test
	public void testSpringSpELInstanceofExpression() {
		boolean booleanRt = parser.parseExpression("'Spring SpEL' instanceof T(String)").getValue(Boolean.class);
		Assert.assertTrue(booleanRt);
	}

	@Test
	public void testSpringSpELVariableDefinition() {
		EvaluationContext context = new StandardEvaluationContext();
		context.setVariable("variable", "Spring 'SpEL'");
		String strRtn = parser.parseExpression("#variable").getValue(context, String.class);
		Assert.assertEquals("Spring 'SpEL'", strRtn);

		context = new StandardEvaluationContext("Spring 'SpEL'");
		strRtn = parser.parseExpression("#root").getValue(context, String.class);
		Assert.assertEquals("Spring 'SpEL'", strRtn);
		strRtn = parser.parseExpression("#this").getValue(context, String.class);
		Assert.assertEquals("Spring 'SpEL'", strRtn);
	}

	@Test
	public void testSpringSpELAssignmentExpression() {
		EvaluationContext context = new StandardEvaluationContext();
		// 1.给自定义变量赋值
		context.setVariable("variable", "Spring 'SpEL'");
		String strRtn = parser.parseExpression("#variable").getValue(context, String.class);
		Assert.assertEquals("Spring 'SpEL'", strRtn);

		// 修改
		strRtn = parser.parseExpression("#variable='Spring ''ABC'''").getValue(context, String.class);
		Assert.assertEquals("Spring 'ABC'", strRtn);
	}

	@Test
	public void testSpringSpELAccessObjProp() {
		ExpressionParser parser = new SpelExpressionParser();
		Date date = new Date();
		StandardEvaluationContext context = new StandardEvaluationContext(date);
		int intRtn = parser.parseExpression("Month").getValue(context, int.class);
		System.out.println(intRtn);
		int intRtn2 = parser.parseExpression("month").getValue(context, int.class);
		System.out.println(intRtn2);

		// 安全访问
		context.setRootObject(null);
		Object objRtn = parser.parseExpression("#root?.year").getValue(context, Object.class);
		Assert.assertNull(objRtn);

		context.setVariable("currentDate", new Date());
		objRtn = parser.parseExpression("#currentDate?.year").getValue(context, String.class);
		System.out.println(objRtn);
	}

	@Test
	public void testSpringSpELInvokeObjMethod() {
		String strRtn = parser.parseExpression("'Spring SpEL'.substring(2,4)").getValue(String.class);
		Assert.assertEquals("ri", strRtn);

		Date date = new Date();
		StandardEvaluationContext context = new StandardEvaluationContext(date);
		int intRtn = parser.parseExpression("getMonth()").getValue(context, int.class);
		System.out.println(intRtn);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testSpringSpELInLineList() {
		// 将返回不可修改的空List
		List<Integer> intList = parser.parseExpression("{}").getValue(List.class);
		System.out.println(intList); // []

		// 对于字面量列表也将返回不可修改的List
		List<Integer> intList2 = parser.parseExpression("{1,2,3}").getValue(List.class);
		System.out.println(intList2); // [1, 2, 3]
		Assert.assertEquals(new Integer(1), intList2.get(0));
		try {
			intList2.set(0, 2);
			// 不可能执行到这，对于字面量列表不可修改
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		// 对于列表中只要有一个不是字面量表达式，将只返回原始List， 会进行不可修改处理
		String expStr = "{{1+2,2+4},{3,4+4,'a'}}";
		List<List<Integer>> listRtn3 = parser.parseExpression(expStr).getValue(List.class);
		System.out.println(listRtn3);
	}

	@Test
	public void testSpringSpELInLineArray() {
		// 定义一维数组并初始化
		int[] intArr = parser.parseExpression("new int[1]{8}").getValue(int[].class);
		System.out.println(Arrays.toString(intArr));

		// 定义多维数组但不初始化, 多维数组不能初始化
		String expStr = "new int[1][2]";
		// expStr = "new int[1][2]{{1}{2}}"; //error,多维数组不能初始化
		int[][] intArr2 = parser.parseExpression(expStr).getValue(int[][].class);
		System.out.println(Arrays.deepToString(intArr2));

		// 解析到多维数据
		String expStr2 = "{{3,4},{5,6}}";
		int[][] intArr3 = parser.parseExpression(expStr2).getValue(int[][].class);
		System.out.println(Arrays.deepToString(intArr3));
	}

	@Test
	public void testSpringSpELAccessCollection() {
		int intRtn = parser.parseExpression("{1,2,3}[0]").getValue(int.class);
		Assert.assertEquals(1, intRtn);

		EvaluationContext context = new StandardEvaluationContext();
		// SpEL目前支持所有集合类型的访问
		Collection<Integer> collection = new HashSet<Integer>();
		collection.add(1);
		collection.add(2);
		context.setVariable("collection", collection);
		int intRtn2 = parser.parseExpression("#collection[1]").getValue(context, int.class);
		Assert.assertEquals(2, intRtn2);

		// SpEL对Map字典元素访问的支持(不存在报空指针)
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("A", 3);
		context.setVariable("map", map);
		int intRtn3 = parser.parseExpression("#map['A']").getValue(context, int.class);
		Assert.assertEquals(3, intRtn3);
	}

	@Test
	public void testSpringSpELModifyCollection() {
		EvaluationContext context = new StandardEvaluationContext();
		// 1.修改数组元素值
		int[] array = new int[] { 1, 2 };
		context.setVariable("array", array);
		int intRtn = parser.parseExpression("#array[1] = 3").getValue(context, int.class);
		Assert.assertEquals(3, intRtn);

		// 2.修改集合值
		Collection<Integer> collection = new ArrayList<Integer>();
		collection.add(1);
		collection.add(2);
		context.setVariable("collection", collection);
		int intRtn2 = parser.parseExpression("#collection[1] = 4").getValue(context, int.class);
		Assert.assertEquals(4, intRtn2);
		parser.parseExpression("#collection[1]").setValue(context, 5);
		int intRtn3 = parser.parseExpression("#collection[1]").getValue(context, int.class);
		Assert.assertEquals(5, intRtn3);

		// 3.修改map元素值
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("A", 1);
		context.setVariable("map", map);
		int intRt4 = parser.parseExpression("#map['A'] = 6").getValue(context, int.class);
		Assert.assertEquals(6, intRt4);

		int intRt5 = parser.parseExpression("#map['B'] = 7").getValue(context, int.class);
		Assert.assertEquals(7, intRt5);

		int intRt6 = parser.parseExpression("#map[null] = 8").getValue(context, int.class);
		Assert.assertEquals(8, intRt6);

		int intRt7 = parser.parseExpression("#map[null]").getValue(context, int.class);
		Assert.assertEquals(8, intRt7);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testSpringSpELCollectionProjection() {
		EvaluationContext context = new StandardEvaluationContext();

		Collection<Integer> collection = new ArrayList<Integer>();
		collection.add(4);
		collection.add(5);

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("A", 1);
		map.put("B", 2);
		map.put(null, 3);

		// 2.collection中每个值加1
		context.setVariable("collection", collection);
		List<Integer> listRtn = parser.parseExpression("#collection.![#this+1]").getValue(context, List.class);
		System.out.println(listRtn);

		// 3.测试map
		context.setVariable("map", map);
		List<Integer> listRtn2 = parser.parseExpression("#map.![value+1]").getValue(context, List.class);
		System.out.println(listRtn2);

		List<String> listRtn3 = parser
				.parseExpression("'key=['+#map.![key==null?key:key+1]+'],value=['+#map.![value+1]+']'")
				.getValue(context, List.class);
		System.out.println(listRtn3);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testSpringSpELCollectionSelect() {
		EvaluationContext context = new StandardEvaluationContext();
		Collection<Integer> collection = new ArrayList<Integer>();
		collection.add(4);
		collection.add(5);

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("A", 1);
		map.put("B", 2);
		map.put(null, 3);

		// 2.集合或数组测试:出集合元素值大于4的所有元素
		context.setVariable("collection", collection);
		List<Integer> listRtn = parser.parseExpression("#collection.?[#this>4]").getValue(context, List.class);
		System.out.println(listRtn);

		// 3.字典测试:选择键值不等于"A"的,注意map选择表达式中"#this"是Map.Entry类型
		context.setVariable("map", map);
		Map<String, Integer> mapRtn = parser.parseExpression("#map.?[#this.key != 'A']").getValue(context, Map.class);
		System.out.println(mapRtn);

		List<Integer> listRtn3 = parser.parseExpression("#map.?[key != 'A'].![value+1]").getValue(context, List.class);
		System.out.println(listRtn3);
	}

}
