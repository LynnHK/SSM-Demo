package com.bwf.p2p.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bwf.p2p.dao.IndexDao;
import com.bwf.p2p.dto.CommonNoticeDto;
import com.bwf.p2p.output.DaoException;
import com.bwf.p2p.service.IndexService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IndexServiceImpl implements IndexService {
	
	@Autowired
	private IndexDao dao;

	public List<CommonNoticeDto> getActivity(int count) {
		List<CommonNoticeDto> list = null;
		try {
			list = dao.getActivity(count);
		} catch (DaoException e) {
			log.error("【获取活动信息】数据查询异常", e);
		}
		return list;
	}

	public List<CommonNoticeDto> getNotice(int count) {
		List<CommonNoticeDto> list = null;
		try {
			list = dao.getNotice("notice", count);
		} catch (DaoException e) {
			log.error("【获取轮播公告】数据查询异常", e);
		}
		
		formatTime(list);
		return list;
	}

	public List<CommonNoticeDto> getNews(int count) {
		List<CommonNoticeDto> list = null;
		try {
			list = dao.getNews(count);
		} catch (DaoException e) {
			log.error("【获取新闻信息】数据查询异常", e);
		}
		return list;
	}

	public List<CommonNoticeDto> getByCid(String type, int count) throws IllegalArgumentException {
		if (!"notice".equals(type) && !"notice_loan".equals(type)) {
			throw new IllegalArgumentException("不支持的数据类型");
		}
		
		List<CommonNoticeDto> list = null;
		try {
			list = dao.getNotice(type, count);
		} catch (DaoException e) {
			log.error("【获取公告信息】数据查询异常", e);
		}
		
		formatTime(list);
		return list;
	}

	private void formatTime(List<CommonNoticeDto> list) {
		if (list != null) {
			for (CommonNoticeDto dto : list) {
				long time = dto.getCreated().getTime();
				dto.setCreated(new Date(time / 1000));
			}
		}
	}

}