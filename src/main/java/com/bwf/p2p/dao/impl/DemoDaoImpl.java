package com.bwf.p2p.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bwf.p2p.dao.DemoDao;
import com.bwf.p2p.dto.DemoDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DemoDaoImpl extends BaseDaoSupport implements DemoDao {
	
	public void demoMethod() {
		Object one = this.sqlSessionTemplate.selectOne("demomapping");
		log.info("hello dao! {}", one);
	}

	public void save(List<DemoDto> list) {
		this.sqlSessionTemplate.insert("saveDemoDto", list);
	}

}
