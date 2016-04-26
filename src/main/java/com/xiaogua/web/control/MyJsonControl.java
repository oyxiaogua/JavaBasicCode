package com.xiaogua.web.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaogua.better.bean.WithDatePropBean;

@Controller
@RequestMapping("/json")
public class MyJsonControl {
	@RequestMapping(value = "/rtnJson", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public List<Map<String, Object>> responseJsonWithProduces() {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<>();
		map.put("key_1", "测试中文");
		map.put("key_2", 123455);
		map.put("key_3", null);
		dataList.add(map);
		return dataList;
	}

	@RequestMapping(value = "/rtnJson2")
	@ResponseBody
	public List<Map<String, Object>> responseJsonWithResponseBody(@RequestParam Map<String, Object> paramMap) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		dataList.add(paramMap);
		return dataList;
	}

	@RequestMapping(value = "/rtnJson3")
	@ResponseBody
	public WithDatePropBean responseJavaBean() {
		WithDatePropBean bean = new WithDatePropBean(1, "测试", new Date(), new Date());
		return bean;
	}

}
