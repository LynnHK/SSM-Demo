package com.bwf.p2p.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.bwf.p2p.base.BaseTest;

public class DemoControllerTest extends BaseTest {
	
	@Test
	public void test() throws Exception {
		// 请求设置
    	MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/demotest") // 请求方式及url
    																  .param("uid", "5") // 请求入参
    																  .contentType(MediaType.APPLICATION_JSON_UTF8); // 请求ContentType
    	// 调用接口
    	ResultActions actions = this.mockMvc.perform(request);
    	
    	// 返回值预判
    	String result = actions.andExpect(MockMvcResultMatchers.status().isOk()) // 返回状态200
//	    	  .andDo(MockMvcResultHandlers.print()) // 打印接口请求和返回信息
//    		  .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true)) // 对返回的具体数据进行判断
    		  .andReturn().getResponse().getContentAsString(); // 获取接口返回值
    	
    	System.out.println(">>>>>>>返回result:" + result);
	}
	
}
