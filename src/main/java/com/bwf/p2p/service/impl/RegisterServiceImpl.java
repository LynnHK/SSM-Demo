package com.bwf.p2p.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bwf.p2p.dao.RegisterDao;
import com.bwf.p2p.dto.UserDto;
import com.bwf.p2p.output.DaoException;
import com.bwf.p2p.service.RegisterService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RegisterServiceImpl implements RegisterService {
	
	@Autowired
	private RegisterDao dao;

	public boolean checkIsExistMobile(String mobile) throws Exception {
		UserDto user = null;
		try {
			user = dao.getUserByMobile(mobile);
		} catch (DaoException e) {
			log.error("【验证手机号】数据库查询异常", e);
			throw new Exception("用户数据库查询异常");
		}
		return user == null ? false : true;
	}

}
