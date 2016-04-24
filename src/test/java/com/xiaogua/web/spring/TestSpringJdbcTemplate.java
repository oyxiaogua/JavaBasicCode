package com.xiaogua.web.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.xiaogua.web.BaseTest;

public class TestSpringJdbcTemplate extends BaseTest {
	private static NamedParameterJdbcTemplate nameJdbcTemplate;

	@BeforeClass
	public static void getNamedParameterJdbcTemplate() {
		nameJdbcTemplate = (NamedParameterJdbcTemplate) context.getBean("namedParameterJdbcTemplate");
	}

	@Test
	public void testSpringJdbcTemplateQueryCount() {
		String sql = "select count(*) from t_user_3 where instr(name,:name)>0";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("name", "test");
		int countNum = nameJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
		System.out.println(countNum);
	}

	@Test
	public void testSpringJdbcTemplateQueryList() {
		String sql = "select name from t_user_3 where id in(:idList)";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<Integer> idList = new ArrayList<>();
		idList.add(2);
		idList.add(3);
		idList.add(4);
		idList.add(15);
		idList.add(5);
		paramMap.put("idList", idList);
		List<String> rtnList = nameJdbcTemplate.queryForList(sql, paramMap, String.class);
		System.out.println(rtnList);
	}

	@Test
	public void testSpringJdbcTemplateUpdate() {
		MapSqlParameterSource[] batch = new MapSqlParameterSource[3];
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "update_1");
		map.put("id", "1");
		batch[0] = new MapSqlParameterSource();
		batch[0].addValues(map);

		map = new HashMap<String, Object>();
		map.put("name", "update_2");
		map.put("id", "2");
		batch[1] = new MapSqlParameterSource();
		batch[1].addValues(map);

		map = new HashMap<String, Object>();
		map.put("name", "update_3");
		map.put("id", "3");
		batch[2] = new MapSqlParameterSource();
		batch[2].addValues(map);

		int[] updateArr = nameJdbcTemplate.batchUpdate("update t_user_3 set name=:name where id = :id", batch);
		System.out.println(Arrays.toString(updateArr));
	}

}
