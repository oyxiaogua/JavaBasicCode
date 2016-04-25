package com.xiaogua.web.dao;

import java.util.List;
import java.util.Map;

public interface IBaseDao {
	public <T> void insert(String namespace, String statement, List<T> beanList);

	public Map<String, Object> queryMap(String namespace, String statement, Map<String, Object> paramMap);

	public List<Map<String, Object>> queryListMap(String namespace, String statement, Map<String, Object> paramMap);

	public <T> void update(String namespace, String statement, List<T> beanList);
}