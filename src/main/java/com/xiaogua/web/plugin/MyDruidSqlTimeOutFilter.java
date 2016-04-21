package com.xiaogua.web.plugin;

import org.apache.log4j.Logger;

import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.StatementProxy;

public class MyDruidSqlTimeOutFilter extends FilterEventAdapter {
	private static final Logger log = Logger.getLogger(MyDruidSqlTimeOutFilter.class);

	/**
	 * 默认超时时间,单位秒
	 */
	private static final int QUERY_TIMEOUT_THRESHOLD_SECOND = 100;

	/**
	 * 超时时间
	 */
	private int timeOutThreshold = QUERY_TIMEOUT_THRESHOLD_SECOND;

	protected void statementExecuteBefore(StatementProxy statement, String sql) {
		setQueryTimeout(statement);
		super.statementExecuteBefore(statement, sql);
	}

	protected void statementExecuteBatchBefore(StatementProxy statement) {
		setQueryTimeout(statement);
		super.statementExecuteBatchBefore(statement);
	}

	protected void statementExecuteUpdateBefore(StatementProxy statement, String sql) {
		setQueryTimeout(statement);
		super.statementExecuteUpdateBefore(statement, sql);
	}

	protected void statementExecuteQueryBefore(StatementProxy statement, String sql) {
		setQueryTimeout(statement);
		super.statementExecuteQueryBefore(statement, sql);
	}

	private void setQueryTimeout(StatementProxy statement) {
		try {
			log.error("setQueryTimeout timeOutValue=" + timeOutThreshold);
			statement.setQueryTimeout(timeOutThreshold);
		} catch (Exception e) {
			log.error("setQueryTimeout error,timeOutValue=" + timeOutThreshold, e);
		}
	}

	public int getTimeOutThreshold() {
		return timeOutThreshold;
	}

	public void setTimeOutThreshold(int timeOutThreshold) {
		this.timeOutThreshold = timeOutThreshold;
	}

}