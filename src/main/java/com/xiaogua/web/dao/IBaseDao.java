package com.xiaogua.web.dao;

import java.util.List;
import java.util.Map;

public interface IBaseDao {
	public int insert(String namespace, String statement, Map<String, Object> paramMap);

	public <T> int insert(String namespace, String statement, List<T> beanList);

	public <T> int insert(String namespace, String statement, T bean);

	public Map<String, Object> queryMap(String namespace, String statement, Map<String, Object> paramMap);

	public List<Map<String, Object>> queryListMap(String namespace, String statement, Map<String, Object> paramMap);

	public <T> List<Map<String, Object>> queryListMap(String namespace, String statement, T t);

	public <T, U> List<U> queryList(String namespace, String statement, T t);

	public <T> int update(String namespace, String statement, List<T> beanList);

	public <T> int update(String namespace, String statement, Map<String, Object> paramMap);

	public int getTotalNum(String namespace, String statement, Map<String, Object> paramMap);

}