package com.xiaogua.better.basic;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonJsonCode {
	private final static ObjectMapper mapper = new ObjectMapper();

	public static String convertObjToStr(Object obj) throws Exception {
		return mapper.writeValueAsString(obj);
	}

	public static <T> T convertStrToObj(String jsonStr, Class<T> clz) throws Exception {
		return mapper.readValue(jsonStr, clz);
	}
	
	public static <T> T convertStrToObj(String jsonStr, TypeReference<T> clz) throws Exception {
		return mapper.readValue(jsonStr, clz);
	}

}
