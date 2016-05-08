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

	public void insert(String namespace, String statement, Map<String, Object> paramMap) {
		long start = System.currentTimeMillis();
		sqlSessionTemplate.insert(getStatementWithNameSpace(namespace, statement), paramMap);
		logger.info("insert",
				"[" + namespace + "." + statement + "] execute, cost:" + (System.currentTimeMillis() - start) + "ms");
	}

	public <T> void insert(String namespace, String statement, List<T> beanList) {
		long start = System.currentTimeMillis();
		sqlSessionTemplate.insert(getStatementWithNameSpace(namespace, statement), beanList);
		logger.info("batchInsert",
				"[" + namespace + "." + statement + "] execute, cost:" + (System.currentTimeMillis() - start) + "ms");
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

	public <T> void update(String namespace, String statement, List<T> beanList) {
		long start = System.currentTimeMillis();
		sqlSessionTemplate.update(getStatementWithNameSpace(namespace, statement), beanList);
		logger.info("batchUpdate",
				"[" + namespace + "." + statement + "] execute, cost:" + (System.currentTimeMillis() - start) + "ms");
	}

	public int getTotalNum(String namespace, String statement, Map<String, Object> paramMap) {
		long start = System.currentTimeMillis();
		int total = sqlSessionTemplate.selectOne(getStatementWithNameSpace(namespace, statement), paramMap);
		logger.info("getTotalNum",
				"[" + namespace + "." + statement + "] execute, cost:" + (System.currentTimeMillis() - start) + "ms");
		return total;
	}

	private String getStatementWithNameSpace(String namespace, String statement) {
		return namespace + "." + statement;
	}

}
