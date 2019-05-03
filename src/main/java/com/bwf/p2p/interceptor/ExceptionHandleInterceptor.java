package com.bwf.p2p.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bwf.p2p.constant.ResponseCode;
import com.bwf.p2p.output.BaseBean;
import com.google.common.base.Strings;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@ControllerAdvice
public class ExceptionHandleInterceptor {
	
	private BaseBean outbean = new BaseBean();
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public BaseBean errorHandler(Exception ex) {
		outbean.success(Boolean.FALSE);
		String msg = ex.getMessage();
		if (!Strings.isNullOrEmpty(msg) && (msg.contains("is not present") || msg.contains("not supported"))) {
			log.error("request error", ex);
			outbean.code(ResponseCode.PARAM_EXCEPTION).msg(msg);
		} else {
			log.error("system error" , ex);
			outbean.code(ResponseCode.SYSTEM_ERROR).msg("There's an error hanppened!");
		}
		
		return outbean;
	}

}
