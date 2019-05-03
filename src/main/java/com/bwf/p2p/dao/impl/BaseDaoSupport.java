package com.bwf.p2p.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDaoSupport {

	@Autowired
	public SqlSessionTemplate sqlSessionTemplate;
	
}
