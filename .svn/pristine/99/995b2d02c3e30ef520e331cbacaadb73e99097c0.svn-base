package com.bwf.p2p.dao.impl;

import org.springframework.stereotype.Repository;

import com.bwf.p2p.dao.RegisterDao;
import com.bwf.p2p.dto.UserDto;
import com.bwf.p2p.output.DaoException;

@Repository
public class RegisterDaoImpl extends BaseDaoSupport implements RegisterDao {

	public UserDto getUserByMobile(String mobile) throws DaoException {
		UserDto user = null;
		try {
			user = this.sqlSessionTemplate.selectOne("getUserByMobile", mobile);
		} catch (Exception e) {
			throw new DaoException();
		}
		return user;
	}

}
