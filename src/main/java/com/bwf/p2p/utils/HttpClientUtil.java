package com.bwf.p2p.utils;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpClientUtil {
	
	public static String doGet(String url, Map<String, Object> params) {
		return doGet(url + getLinkParameter(params));
	}
	
	public static String doGet(String url) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String result = "{}";
		try {
			HttpGet httpget = new HttpGet(url);
			log.info("Get Request <{}>", url);
			response = httpclient.execute(httpget);
			int status = response.getStatusLine().getStatusCode();
			if (status == HttpStatus.SC_OK) {
				HttpEntity respEntity = response.getEntity();
				result = EntityUtils.toString(respEntity);
				log.info("Get Request <{}> return: {}", url, result);
			} else {
				log.error("Get Request <{}> Failed, status: {}", url, status);
			}
			
		} catch (Exception e) {
			log.error("Get Request <{}> Exception: {}", url, e);
		}
		
		dispose(response, httpclient);
		return result;
	}

	private static String getLinkParameter(Map<String, Object> params) {
		if (params == null || params.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (Entry<String, Object> entry : params.entrySet()) {
			sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
		}
		String linkedStr = sb.toString();
		if (null != linkedStr && linkedStr.length() != 0) {
			linkedStr = linkedStr.replace("&", "?");
		}
		return linkedStr;
	}

	public static String doPost(String url, Map<String, Object> params) {
		String body = JSON.toJSONString(params);
		return doPost(url, body);
	}

	public static String doPost(String url, String body) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String result = "{}";
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");
			StringEntity reqEntity = new StringEntity(body, Charset.forName("UTF-8"));
			httpPost.setEntity(reqEntity);
			log.info("Post Request <{}> with body: {}", url, body);
			response = httpclient.execute(httpPost);
			int status = response.getStatusLine().getStatusCode();
			if (status == HttpStatus.SC_OK) {
				HttpEntity respEntity = response.getEntity();
				result = EntityUtils.toString(respEntity);
				log.info("Post Request <{}> return: {}", url, result);
			} else {
				log.error("Post Request <{}> Failed, status: {}", url, status);
			}
			
		} catch (Exception e) {
			log.error("Post Request <{}> Exception: {}", url, e);
		}
		
		dispose(response, httpclient);
		return result;
	}

	private static void dispose(CloseableHttpResponse response, CloseableHttpClient httpclient) {
		try {
			if (response != null) {
				response.close();
			}
			if (httpclient != null) {
				httpclient.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
//		String result = doGet("http://s1.bdstatic.com/r/www/cache/static/jquery/jquery-1.10.2.min_65682a2.js");
//		doGet("http://localhost:8080/p2p/demotest");
//		doPost("http://localhost:8080/p2p/demotest", "");
	}

}
