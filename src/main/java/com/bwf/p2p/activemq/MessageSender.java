package com.bwf.p2p.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Component
public class MessageSender {

	/**
	 * 默认消息队列名称
	 */
	private String defaultQueueName = "default_queue";
	
	/**
	 * 积分消息队列名称
	 */
    private String integralQueueName = "integral_queue";
 
    /**
     * spring提供的队列模板
     */
    @Autowired
    private JmsTemplate jmsTemplate;
 
    /**
     * 发送到默认消息队列
     * @param message
     */
    public void defaultMessage(String message) {
        System.out.println("发送了一条消息到默认队列.......");
        send(message, 0);
    }
 
    /**
     * 发送到积分消息队列
     * @param message
     */
    public void integraMessage(String message) {
        System.out.println("发送了一条消息到积分队列.......");
        send(message, 1);
    }
 
    /**
     * 发送消息
     * @param message
     * @param type 0:默认队列 1：积分队列
     */
    public void send(final String message, int type) {
        try {
            String destination = type== 0 ? defaultQueueName : integralQueueName;
            jmsTemplate.send(destination, new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    TextMessage textMessage = session.createTextMessage(message);
                    return textMessage;
                }
            });
        } catch (Exception e) {
            log.error("发送消息失败", e);
        }
    }
	
}
