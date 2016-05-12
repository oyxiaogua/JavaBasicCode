package com.xiaogua.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaogua.web.dao.IBaseDao;

public class BaseDaoImpl implements IBaseDao {
	private static final Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public int insert(String namespace, String statement, Map<String, Object> paramMap) {
		long start = System.currentTimeMillis();
		int rtnInt = sqlSessionTemplate.insert(getStatementWithNameSpace(namespace, statement), paramMap);
		logger.info("insert",
				"[" + namespace + "." + statement + "] execute, cost:" + (System.currentTimeMillis() - start) + "ms");
		return rtnInt;
	}

	public <T> int insert(String namespace, String statement, List<T> beanList) {
		long start = System.currentTimeMillis();
		int rtnInt = sqlSessionTemplate.insert(getStatementWithNameSpace(namespace, statement), beanList);
		logger.info("batchInsert",
				"[" + namespace + "." + statement + "] execute, cost:" + (System.currentTimeMillis() - start) + "ms");
		return rtnInt;
	}

	public <T> int insert(String namespace, String statement, T bean) {
		long start = System.currentTimeMillis();
		int rtnInt = sqlSessionTemplate.insert(getStatementWithNameSpace(namespace, statement), bean);
		logger.info("insert",
				"[" + namespace + "." + statement + "] execute, cost:" + (System.currentTimeMillis() - start) + "ms");
		return rtnInt;
	}

	public Map<String, Object> queryMap(String namespace, String statement, Map<String, Object> paramMap) {
		long start = System.currentTimeMillis();
		Map<String, Object> rtnMap = sqlSessionTemplate
				.<Map<String, Object>> selectOne(getStatementWithNameSpace(namespace, statement), paramMap);
		logger.info("queryMap",
				"[" + namespace + "." + statement + "] execute, cost:" + (System.currentTimeMillis() - start) + "ms");
		return rtnMap;
	}

	public List<Map<String, Object>> queryListMap(String namespace, String statement, Map<String, Object> paramMap) {
		long start = System.currentTimeMillis();
		List<Map<String, Object>> rtnList = sqlSessionTemplate
				.<Map<String, Object>> selectList(getStatementWithNameSpace(namespace, statement), paramMap);
		logger.info("queryListMap",
				"[" + namespace + "." + statement + "] execute, cost:" + (System.currentTimeMillis() - start) + "ms");
		return rtnList;
	}

	public <T> List<Map<String, Object>> queryListMap(String namespace, String statement, T t) {
		long start = System.currentTimeMillis();
		List<Map<String, Object>> rtnList = sqlSessionTemplate
				.<Map<String, Object>> selectList(getStatementWithNameSpace(namespace, statement), t);
		logger.info("queryListMap GeneType",
				"[" + namespace + "." + statement + "] execute, cost:" + (System.currentTimeMillis() - start) + "ms");
		return rtnList;
	}

	public <T> int update(String namespace, String statement, List<T> beanList) {
		long start = System.currentTimeMillis();
		int rtnInt = sqlSessionTemplate.update(getStatementWithNameSpace(namespace, statement), beanList);
		logger.info("batchUpdate",
				"[" + namespace + "." + statement + "] execute, cost:" + (System.currentTimeMillis() - start) + "ms");
		return rtnInt;
	}

	public <T> int update(String namespace, String statement, Map<String, Object> paramMap) {
		long start = System.currentTimeMillis();
		int rtnInt = sqlSessionTemplate.update(getStatementWithNameSpace(namespace, statement), paramMap);
		logger.info("batchUpdate",
				"[" + namespace + "." + statement + "] execute, cost:" + (System.currentTimeMillis() - start) + "ms");
		return rtnInt;
	}

	public int getTotalNum(String namespace, String statement, Map<String, Object> paramMap) {
		long start = System.currentTimeMillis();
		Integer total = sqlSessionTemplate.selectOne(getStatementWithNameSpace(namespace, statement), paramMap);
		if (total == null) {
			// 此处自动拆箱可能报空指针
			return 0;
		}
		logger.info("getTotalNum",
				"[" + namespace + "." + statement + "] execute, cost:" + (System.currentTimeMillis() - start) + "ms");
		return total;
	}

	private String getStatementWithNameSpace(String namespace, String statement) {
		return namespace + "." + statement;
	}

}
