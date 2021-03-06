package com.bwf.p2p.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bwf.p2p.constant.ResponseCode;
import com.bwf.p2p.output.BaseBean;
import com.bwf.p2p.rocketmq.MyProducer;
import com.bwf.p2p.service.DemoService;
import com.bwf.p2p.utils.ExcelUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DemoController {
	
	@Autowired
	private DemoService demoService;
	
	@RequestMapping(value = "/demotest", method = RequestMethod.GET)
	@ResponseBody
	public BaseBean demoMethod(HttpServletRequest request, HttpServletResponse response) {
		log.debug("into method");
		log.info("hello controller!");
		demoService.demoMethod();
//		try {
//			response.getWriter().write("hello world!");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		log.warn("returned");
		
//		try {
			int i = 1; int j = 0;
			int a = i / j;
//		} catch (Exception e) {
//			log.error("it's time to maka a error log now", e);
//		}
		return new BaseBean();
	}

	@RequestMapping(value = "/jsptest", method = RequestMethod.POST)
	public ModelAndView jsptest(HttpServletRequest request, HttpServletResponse response) {
		log.info("hello jsp");
		return new ModelAndView("hello").addObject("key", "haha");
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response) {
		log.info("index jsp");
		return "index";
	}
	
	@RequestMapping(value = "/uploadjsp", method = RequestMethod.GET)
	public String uploadjsp(HttpServletRequest request, HttpServletResponse response) {
		log.info("upload jsp");
		return "upload";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public BaseBean upload(@RequestParam MultipartFile file, HttpServletRequest request) {
		BaseBean outbean = new BaseBean();
		if (file == null || file.isEmpty()) {
			outbean.success(Boolean.FALSE).code(ResponseCode.PARAM_EXCEPTION).msg("请选择文件再上传！");
			log.info("upload file return: {}", outbean);
			return outbean;
		}
		log.info("upload file: originalName={}, size={}bytes", file.getOriginalFilename(), file.getSize());
		
		String tempPath = System.getProperty("catalina.base") + File.separator + "temp";
		
		try {
			demoService.saveFile(file, tempPath);
		} catch (Exception e) {
			outbean.success(Boolean.FALSE).code(ResponseCode.FAILED).msg(e.getMessage());
			log.info("upload file return: {}", outbean);
			return outbean;
		}
		
		log.info("upload file return: {}", outbean);
		return outbean;
	}
	
	@GetMapping("/mqtest")
	public void mqtest(HttpServletRequest request, HttpServletResponse response) {
		log.info("进入mqtest");
//		demoService.mqtest(request.getParameter("topic"));
		response.setHeader("Content-disposition", "attachment;filename=test.xlsx");
		try {
			ExcelUtil.createExcel(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("退出mqtest");
	}
	
}
