package com.bwf.p2p.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import lombok.extern.slf4j.Slf4j;

/**
 * 消费者监听类
 * 具体的消息消费类，可以在这实现具体的业务逻辑处理
 */
@Slf4j
public class ConsumerMessageListener implements MessageListener  {

	/**
     * 监听发送到消息队列的文本消息
     * @param message
     */
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("接收到的消息内容是：" + textMessage.getText());
        } catch (JMSException e) {
            log.error("消息解析失败", e);
        }
	}

}
