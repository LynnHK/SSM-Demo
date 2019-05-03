package com.bwf.p2p.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonWebInterceptor extends HandlerInterceptorAdapter {

	/**
	 * 进入controller之前调用
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		log.info("PreHandle...requestMethod:{}", getControllerMethodName(handler.toString()));
		return super.preHandle(request, response, handler);
	}

	/**
	 * controller执行完成后调用
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
//		log.info("PoseHandle...fineshMethod:{}", getControllerMethodName(handler.toString()));
		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * 在响应之前调用
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) throws Exception {
		log.info("AfterCompletion ready to answer...");
		super.afterCompletion(request, response, handler, ex);
	}
	
	/*private String getControllerMethodName(String method) {
		method = method.substring(0, method.indexOf("("));
		return method.substring(method.lastIndexOf(" "));
	}*/

}
