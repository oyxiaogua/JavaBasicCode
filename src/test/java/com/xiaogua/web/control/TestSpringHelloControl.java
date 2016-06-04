package com.xiaogua.web.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.xiaogua.better.basic.JacksonJsonCode;
import com.xiaogua.better.bean.Parent_Normal_Bean;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/resources/spring/spring-all.xml")
public class TestSpringHelloControl {
	private MockMvc mockMvc;
	@InjectMocks
	SpringHelloControl springHelloControl;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(springHelloControl).build();
	}

	@Test
	public void testAddPerson() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/hello/addPerson").param("parentName", "test测试")
				.param("parentAge", "22").param("parentDate", "2016-06-06 11:12:13"))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testAddPersonList() throws Exception {
		List<Parent_Normal_Bean> beanList = new ArrayList<Parent_Normal_Bean>();
		beanList.add(new Parent_Normal_Bean());
		beanList.add(new Parent_Normal_Bean(null, 2, null));
		for (int i = 1; i < 400; i++) {
			beanList.add(new Parent_Normal_Bean("test_" + i, i, new Date()));
		}
		mockMvc.perform(MockMvcRequestBuilders.post("/hello/addPersonList")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(JacksonJsonCode.convertObjToStr(beanList)))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testAddPersonArr() throws Exception {
		Parent_Normal_Bean[] beanArr = new Parent_Normal_Bean[4];
		beanArr[0] = new Parent_Normal_Bean();
		beanArr[1] = new Parent_Normal_Bean(null, 2, null);
		for (int i = 1; i < 3; i++) {
			beanArr[i + 1] = new Parent_Normal_Bean("test_" + i, i, new Date());
		}
		mockMvc.perform(MockMvcRequestBuilders.post("/hello/addPersonArr")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(JacksonJsonCode.convertObjToStr(beanArr)))
				.andDo(MockMvcResultHandlers.print());
	}


	@Test
	public void testSubmitJsonStr() throws Exception {
		List<Integer> list = new ArrayList<Integer>();
		list.add(null);
		for (int i = 1; i < 400; i++) {
			list.add(i);
		}
		mockMvc.perform(MockMvcRequestBuilders.post("/hello/submitJsonStr").param("jsonStr",
				JacksonJsonCode.convertObjToStr(list))).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testSubmitArr() throws Exception {
		StringBuffer sb = new StringBuffer(128);
		sb.append(0);
		for (int i = 1; i < 400; i++) {
			sb.append(",").append(i);
		}
		mockMvc.perform(MockMvcRequestBuilders.post("/hello/submitArr").param("idList[]", sb.toString()))
				.andDo(MockMvcResultHandlers.print());
	}
}
