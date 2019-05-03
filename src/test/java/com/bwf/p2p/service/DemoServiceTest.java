package com.bwf.p2p.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bwf.p2p.base.BaseTest;

public class DemoServiceTest extends BaseTest {
	
	@Autowired
	private DemoService service;
	
	@Test
	public void test() {
		service.demoMethod();
	}

}
