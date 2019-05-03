package com.bwf.p2p.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.Cookie;
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
import com.bwf.p2p.output.BaseBean;
import com.bwf.p2p.service.RegisterService;
import com.bwf.p2p.utils.ImageUtil;
import com.bwf.p2p.utils.RedisUtil;
import com.google.common.base.Strings;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RegisterController {
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private RegisterService service;
	 
	/**
	 * 获取验证码
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getValicode", method = RequestMethod.GET)
	public void getValicode(HttpServletRequest request, HttpServletResponse response) {
		String valicode = ImageUtil.getRandomStr();
		log.info("【验证码】{}", valicode);
		
		// 存到redis 有效期5分钟
		String uuid = getUUID();
		redisUtil.set(uuid, valicode, 60 * 5);
		// 存到cookie
		Cookie c = new Cookie("UUID", uuid);
		c.setPath("/p2p");
		response.addCookie(c);
		
		// 发送到前端
		try {
			ImageUtil.getImage(valicode, response.getOutputStream());
		} catch (IOException e) {
			log.error("响应验证码失败", e);
		}
	}
	
	@RequestMapping(value = "/checkIsExistMobile", method = RequestMethod.GET)
	@ResponseBody
	public BaseBean checkIsExistMobile(@RequestParam String mobile) {
		log.info("【验证手机号】mobile={}", mobile);
		BaseBean outbean = null;
		if (Strings.isNullOrEmpty(mobile)) {
			outbean = BaseBean.builder().success(Boolean.FALSE).code(ResponseCode.FAILED).msg("mobile为空").build();
		} else {
			try {
				boolean exist = service.checkIsExistMobile(mobile);
				if (exist) {
					outbean = BaseBean.builder().success(Boolean.TRUE).code(1).msg("手机号" + mobile + "已存在").build();
				} else {
					outbean = new BaseBean();
				}
			} catch (Exception e) {
				outbean = BaseBean.builder().success(Boolean.FALSE).code(ResponseCode.FAILED).msg("查询手机号异常").build();
			}
		}
		
		log.info("【验证手机号】result: {}", JSON.toJSONString(outbean));
		return outbean;
	}
	
	private String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.replace("-", "").toUpperCase();
	}
	
}
