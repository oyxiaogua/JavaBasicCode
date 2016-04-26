package com.xiaogua.web.control;

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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/resources/spring/spring-all.xml")
public class TestMyJsonControl {
	private MockMvc mockMvc;
	@InjectMocks
	MyJsonControl myJsonControl;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(myJsonControl).build();
	}

	@Test
	public void testResponseJsonWithProduces() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/json/rtnJson")).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testResponseJsonWithResponseBody() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/json/rtnJson2").param("key_1", "测试返回").param("key_2", "222")
				.param("key_3", (String) null)).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testResponseJavaBean() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/json/rtnJson3").accept(MediaType.APPLICATION_JSON_UTF8))
				.andDo(MockMvcResultHandlers.print());
	}
}
