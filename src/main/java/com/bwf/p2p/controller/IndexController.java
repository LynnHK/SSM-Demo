package com.bwf.p2p.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bwf.p2p.constant.ResponseCode;
import com.bwf.p2p.dto.CommonNoticeDto;
import com.bwf.p2p.output.BaseBean;
import com.bwf.p2p.service.IndexService;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {
	
	@Autowired
	private IndexService service;
	
	@RequestMapping(value = "/activityArea", method = RequestMethod.GET)
	@ResponseBody
	public BaseBean activity(HttpServletRequest request, HttpServletResponse response) {
		log.info("【获取活动信息】start");
		
		BaseBean outbean = null;
		List<CommonNoticeDto> list = service.getActivity(4);
		if (list != null) {
			outbean = BaseBean.builder().data(list).build();
		} else {
			outbean =  BaseBean.builder().success(Boolean.FALSE).code(ResponseCode.FAILED).msg("数据查询失败").build();
		}
		
		log.info("【获取活动信息】result: {}", JSON.toJSONString(outbean));
		return outbean;
	}
	
	@RequestMapping(value = "/news", method = RequestMethod.GET)
	@ResponseBody
	public BaseBean news(HttpServletRequest request, HttpServletResponse response) {
		log.info("【获取新闻信息】start");
		
		BaseBean outbean = null;
		List<CommonNoticeDto> list = service.getNews(2);
		if (list != null) {
			outbean = BaseBean.builder().data(list).build();
		} else {
			outbean =  BaseBean.builder().success(Boolean.FALSE).code(ResponseCode.FAILED).msg("数据查询失败").build();
		}
		
		log.info("【获取新闻信息】result: {}", JSON.toJSONString(outbean));
		return outbean;
	}
	
	@RequestMapping(value = "/notice", method = RequestMethod.GET)
	@ResponseBody
	public BaseBean notice(HttpServletRequest request, HttpServletResponse response) {
		log.info("【获取轮播公告】start");
		
		BaseBean outbean = null;
		List<CommonNoticeDto> list = service.getNotice(6);
		if (list != null) {
			outbean = BaseBean.builder().data(list).build();
		} else {
			outbean =  BaseBean.builder().success(Boolean.FALSE).code(ResponseCode.FAILED).msg("数据查询失败").build();
		}
		
		log.info("【获取轮播公告】result: {}", JSON.toJSONString(outbean));
		return outbean;
	}
	
	@RequestMapping(value = "/getByCid", method = RequestMethod.GET)
	@ResponseBody
	public BaseBean getByCid(@RequestParam String type, @RequestParam int pageSize) {
		log.info("【获取公告信息】start: type={}, pageSize={}", type, pageSize);
		BaseBean outbean = null;
		if (Strings.isNullOrEmpty(type)) {
			outbean = BaseBean.builder().success(Boolean.FALSE).code(ResponseCode.PARAM_EXCEPTION).msg("type为空").build();
		} else if (pageSize == 0) {
			outbean = BaseBean.builder().success(Boolean.FALSE).code(ResponseCode.PARAM_EXCEPTION).msg("pageSize为空").build();
		} else {
		
			List<CommonNoticeDto> list = null;
			try {
				list = service.getByCid(type, pageSize);
				if (list != null) {
					Map<Object,Object> data = Maps.newHashMap();
					data.put("news", list);
					outbean = BaseBean.builder().data(data).build();
				} else {
					outbean =  BaseBean.builder().success(Boolean.FALSE).code(ResponseCode.FAILED).msg("数据查询失败").build();
				}
			} catch (IllegalArgumentException e) {
				outbean = BaseBean.builder().success(Boolean.FALSE).code(ResponseCode.PARAM_EXCEPTION).msg(e.getMessage()).build();
			}
		}
		
		log.info("【获取公告信息】result: {}", JSON.toJSONString(outbean));
		return outbean;
	}
	
}
