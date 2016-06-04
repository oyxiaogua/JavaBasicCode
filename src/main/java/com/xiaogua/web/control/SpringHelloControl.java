package com.xiaogua.web.control;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xiaogua.better.basic.JacksonJsonCode;
import com.xiaogua.better.bean.Parent_Normal_Bean;

@Controller
@RequestMapping("/hello")
public class SpringHelloControl {
	private Logger log = Logger.getLogger(SpringHelloControl.class);

	@RequestMapping(value = "/addPerson", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Parent_Normal_Bean addPersonList(Parent_Normal_Bean person) {
		log.info(person);
		return person;
	}

	@RequestMapping(value = "/addPersonList", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public List<Parent_Normal_Bean> addPersonList(@RequestBody List<Parent_Normal_Bean> beanList) {
		log.info(beanList);
		return beanList;
	}

	@RequestMapping(value = "/submitJsonStr", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public List<Integer> submitJsonStr(@RequestParam(value = "jsonStr", required = false) String jsonStr)
			throws Exception {
		TypeReference<List<Integer>> typeRef = new TypeReference<List<Integer>>() {
		};
		List<Integer> list = JacksonJsonCode.convertStrToObj(jsonStr, typeRef);
		return list;
	}

	@RequestMapping(value = "/submitArr", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public List<Integer> submitArr(@RequestParam("idList[]") List<Integer> idList) throws Exception {
		return idList;
	}

}
