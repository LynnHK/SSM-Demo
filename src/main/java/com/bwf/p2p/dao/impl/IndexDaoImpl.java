package com.bwf.p2p.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bwf.p2p.dao.IndexDao;
import com.bwf.p2p.dto.CommonNoticeDto;
import com.bwf.p2p.output.DaoException;
import com.google.common.collect.Maps;

@Repository
public class IndexDaoImpl extends BaseDaoSupport implements IndexDao {

	public List<CommonNoticeDto> getActivity(int count) throws DaoException {
		List<CommonNoticeDto> list = null;
		try {
			list = this.sqlSessionTemplate.selectList("getActivity", count);
		} catch (Exception e) {
			throw new DaoException();
		}
		return list;
	}

	public List<CommonNoticeDto> getNotice(String type, int count) throws DaoException {
		List<CommonNoticeDto> list = null;
		Map<String, Object> param = Maps.newHashMap();
		param.put("type", type);
		param.put("count", count);
		try {
			list = this.sqlSessionTemplate.selectList("getNotice", param);
		} catch (Exception e) {
			throw new DaoException();
		}
		return list;
	}

	public List<CommonNoticeDto> getNews(int count) throws DaoException {
		List<CommonNoticeDto> list = null;
		try {
			list = this.sqlSessionTemplate.selectList("getNews", count);
		} catch (Exception e) {
			throw new DaoException();
		}
		return list;
	}

}
