package com.bwf.p2p.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/grwx/friend")
@Controller
public class TestController {
	
	private static List<Object> list;
	
	static {
		list = new ArrayList<Object>();
		for (int i = 0; i < 50; i++) {
			JSONObject obj = new JSONObject();
			obj.put("remarkName", "a"+i);
			obj.put("nickName", "a"+i);
			obj.put("userName", "@a"+i);
			list.add(obj);
		}
	}

	@RequestMapping(value = "/find_all.do", method = RequestMethod.GET)
	public ModelAndView findAll(String umid, String remarkName, String nickName,
			@RequestParam(value = "current_page", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "page_size", defaultValue = "20") Integer pageSize) {
		log.info("[findAll]umid={},remarkName={},nickName={},currentPage={},pageSize={}", umid, remarkName, nickName,
				currentPage, pageSize);
		ModelAndView view = new ModelAndView("friendlist");
		if (null == umid || "".equals(umid)) {
			return view;
		}
		
		// 查询数据
		int totalCount = list.size();
		int totalPage = totalCount % pageSize == 0 ? (totalCount / pageSize) : (totalCount / pageSize + 1);
		if (currentPage >= totalPage) {
			currentPage = totalPage;
		}
		int pageStart = (currentPage - 1) * pageSize + 1;
		int pageEnd = currentPage * pageSize;
		
		List<Object> data = list.subList(pageStart, pageEnd); // 忽略remarkName和nickName匹配
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("umid", umid);
		model.put("remarkName", remarkName);
		model.put("nickName", nickName);
		model.put("friendList", data);
		model.put("total_count", totalCount);
		model.put("total_page", totalPage);
		model.put("current_page", currentPage);
		model.put("next_page", currentPage == totalPage ? currentPage : (currentPage + 1));
		model.put("prev_page", currentPage == 1 ? currentPage : (currentPage - 1));
		view.addObject("model", model);
		
		return view;
	}

}
