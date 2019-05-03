package com.bwf.p2p.dao;

import java.util.List;

import com.bwf.p2p.dto.CommonNoticeDto;
import com.bwf.p2p.output.DaoException;

public interface IndexDao {

	public List<CommonNoticeDto> getActivity(int count) throws DaoException;

	public List<CommonNoticeDto> getNotice(String type, int count) throws DaoException;

	public List<CommonNoticeDto> getNews(int count) throws DaoException;

}
