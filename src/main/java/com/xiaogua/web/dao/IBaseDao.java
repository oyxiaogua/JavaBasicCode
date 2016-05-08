package com.xiaogua.web.dao;

import java.util.List;
import java.util.Map;

public interface IBaseDao {
	public void insert(String namespace, String statement, Map<String, Object> paramMap);

	public <T> void insert(String namespace, String statement, List<T> beanList);

	public Map<String, Object> queryMap(String namespace, String statement, Map<String, Object> paramMap);

	public List<Map<String, Object>> queryListMap(String namespace, String statement, Map<String, Object> paramMap);

	public <T> List<Map<String, Object>> queryListMap(String namespace, String statement, T t);

	public <T> void update(String namespace, String statement, List<T> beanList);

	public int getTotalNum(String namespace, String statement, Map<String, Object> paramMap);

}