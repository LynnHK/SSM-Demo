package com.bwf.p2p.utils;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("redisUtil")
public class RedisUtil {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	/**
	 * 往Redis设置值
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String key, String value) {
		try {
			redisTemplate.opsForValue().set(key, value);
		} catch (Exception e) {
			log.error("redis handle error, key:{}", key, e);
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}  
	
	/**
	 * 往Redis设置值
	 * @param key
	 * @param value
	 * @param expire 
	 * @return
	 */
	public boolean set(String key, String value, long expire) {
		try {
			redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
		} catch (Exception e) {
			log.error("redis handle error, key:{} ", key, e);
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	/**
	 * 从Redis获取值
	 * @param key
	 * @return
	 */
	public String get(String key){
		String obj = null;
		try {
			obj = redisTemplate.opsForValue().get(key);
		} catch (Exception e) {
			log.error("redis handle error, key:{} ", key, e);
		}
		return obj;
	}
	
	/**
	 * redis对应key自增
	 * @param key
	 * @param num
	 * @return
	 */
	public long incr(String key, int num) {
		return redisTemplate.opsForValue().increment(key, num);
	}
	
	/**
	 * 设置生命周期
	 * @param key
	 * @param expire
	 * @return
	 */
    public boolean expire(String key, long expire) {
    	boolean result = Boolean.FALSE;
    	try {
    		result = redisTemplate.expire(key, expire, TimeUnit.SECONDS);
		} catch (Exception e) {
			log.error("redis handle error, key:{} ", key, e);
		}
    	return result;
    }
    
    /**
     * 获得剩余生命周期
     * @param key
     * @return
     */
    public long getExpire(String key) {
    	return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }
    
   /**
    * 往list头添加元素
    * @param key
    * @param value
    * @return
    */
	public long lpush(String key, String value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }  
	
    /**
     * 往list尾添加元素
     * @param key
     * @param value
     * @return
     */
    public long rpush(String key, String value) {
        return redisTemplate.opsForList().rightPush(key, value);
    } 
    
    /**
	 * 往list头删除元素
	 * @param key
	 * @return
	 */
	public String lpop(String key) {
	    return redisTemplate.opsForList().leftPop(key);
	}
	
	/**
	 * 往list尾删除元素
	 * @param key
	 * @return
	 */
	public String rpop(String key) {
	    return redisTemplate.opsForList().rightPop(key);
	}
	  
}
