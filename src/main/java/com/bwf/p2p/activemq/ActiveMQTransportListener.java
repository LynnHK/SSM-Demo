package com.bwf.p2p.activemq;

import java.io.IOException;

import org.apache.activemq.transport.TransportListener;

import lombok.extern.slf4j.Slf4j;

/**
 * 消息传输监听类
 * 这里就可以做消息传输过程中的一些监控及处理，比如常见的异常处理等
 */
@Slf4j
public class ActiveMQTransportListener implements TransportListener {

	/**
     * 对消息传输命令进行监控
     * @param o
     */
	public void onCommand(Object o) {
		// TODO Auto-generated method stub
		
	}

	/**
     * 监控到异常时触发
     * @param e
     */
	public void onException(IOException e) {
		log.error("onException -> 消息服务器连接错误......", e);
		
	}

	 /**
     * 当failover时触发
     */
	public void transportInterupted() {
		log.warn("transportInterupted -> 消息服务器连接发生中断...");
		
	}

	/**
     * 监控到failover恢复后进行触发
     */
	public void transportResumed() {
		log.info("transportResumed -> 消息服务器连接已恢复...");
		
	}

}
