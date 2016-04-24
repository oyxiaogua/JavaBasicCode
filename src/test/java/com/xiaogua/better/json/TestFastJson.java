package com.xiaogua.better.json;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xiaogua.better.basic.JacksonJsonCode;
import com.xiaogua.better.bean.Normal_Hello_Bean;
import com.xiaogua.better.bean.Super_Template_Class;
import com.xiaogua.better.bean.With_InnerClass_Prop_Class;

public class TestFastJson {
	private static Logger logger = Logger.getLogger(TestFastJson.class);

	@Test
	public void testFastJsonConvertTemplateClass() throws Exception {
		Super_Template_Class<Normal_Hello_Bean> templateClass = new Super_Template_Class<>();
		templateClass.setName("hello");

		Normal_Hello_Bean hello = new Normal_Hello_Bean();
		hello.setHello("hello1");
		hello.setUser(Arrays.asList("user1", "user2"));

		Normal_Hello_Bean hello2 = new Normal_Hello_Bean();
		hello2.setHello("hello2");
		hello2.setUser(Arrays.asList("world1", "world2"));

		templateClass.setList(Arrays.asList(hello, hello2));

		String jsonStr = JSON.toJSONString(templateClass);
		logger.info(jsonStr);

		Object obj = JSON.parseObject(jsonStr, Super_Template_Class.class);
		logger.info(obj);

		Object obj2 = JSON.parseObject(jsonStr, new TypeReference<Super_Template_Class<Normal_Hello_Bean>>() {
		});
		logger.info(obj2);

		logger.error("-----------user jacksonjson----");
		jsonStr = JacksonJsonCode.convertObjToStr(templateClass);
		logger.info(jsonStr);

		@SuppressWarnings("unchecked")
		Super_Template_Class<Normal_Hello_Bean> bean2 = JacksonJsonCode.convertStrToObj(jsonStr,
				Super_Template_Class.class);
		logger.info(bean2);

		bean2 = JacksonJsonCode.convertStrToObj(jsonStr,
				new com.fasterxml.jackson.core.type.TypeReference<Super_Template_Class<Normal_Hello_Bean>>() {
				});
		logger.info(bean2);
	}

	@Test
	public void testFastJsonConvertInnerClass() throws Exception {
		With_InnerClass_Prop_Class withClz = new With_InnerClass_Prop_Class();
		withClz.setName("hello");
		withClz.setInnerClz("innerHello", Arrays.asList("user1", "user2"));

		String str = JSON.toJSONString(withClz);
		logger.info(str);

		// 不要反序列化匿名类,内部类
		Object obj = JSON.parseObject(str, With_InnerClass_Prop_Class.class);
		logger.info(obj);

		logger.error("-----------user jacksonjson,note that Inner_Hello_Class must be public----");
		String jsonStr = JacksonJsonCode.convertObjToStr(withClz);
		logger.info(jsonStr);

		obj = JacksonJsonCode.convertStrToObj(jsonStr, With_InnerClass_Prop_Class.class);
		logger.info(obj);
	}

	@Test
	public void testFastJsonConvertMap() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", 1);
		map.put("b", "");
		map.put("c", null);
		map.put("d", "2");
		map.put(null, "3");
		String jsonStr = JSONObject.toJSONString(map);
		logger.info(jsonStr);
		// 输出空值
		jsonStr = JSONObject.toJSONString(map, SerializerFeature.WriteMapNullValue);
		logger.info(jsonStr);
	}

}
