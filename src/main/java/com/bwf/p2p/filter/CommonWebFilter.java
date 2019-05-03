package com.bwf.p2p.filter;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonWebFilter implements Filter {

	public void destroy() {
		log.info("<<<<<<Filter destroied.");
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		long start = System.currentTimeMillis();
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		String uri = getUriWithParam(request);
		log.info(">>>>>>Begin Request [{}].", uri);
		
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Headers", "Authentication");
	    
		chain.doFilter(req, resp);
		log.info("<<<<<<Finish Request [{}] {}ms.", uri, System.currentTimeMillis() - start);
	}

	public void init(FilterConfig conf) throws ServletException {
		log.info(">>>>>>Filter initted.");
	}

	private String getUriWithParam(HttpServletRequest request) {
		String uri = request.getRequestURI();
		StringBuilder sb = new StringBuilder();
		Map<String, String[]> params = request.getParameterMap();
		if (params != null && !params.isEmpty()) {
			for (Entry<String, String[]> entry : params.entrySet()) {
				sb.append("&").append(entry.getKey()).append("=").append(entry.getValue()[0]);
			}
		}
		String paramStr = sb.toString();
		if (null != paramStr && !"".equals(paramStr)) {
			uri += "?" + paramStr.substring(1);
		}
		
		return uri;
	}

}
