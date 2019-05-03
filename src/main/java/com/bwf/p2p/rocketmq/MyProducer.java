package com.bwf.p2p.rocketmq;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyProducer {
	
	private DefaultMQProducer defaultMQProducer;
	
    private String producerGroup;
    
    private String namesrvAddr;
    
    /**
     * Spring bean init-method
     */
    public void init() throws MQClientException {
    	
        // 参数信息
        log.info("DefaultMQProducer initialize!");
        log.info(producerGroup);
        log.info(namesrvAddr);

        // 初始化
        defaultMQProducer = new DefaultMQProducer(producerGroup);
        defaultMQProducer.setNamesrvAddr(namesrvAddr);
        defaultMQProducer.setInstanceName(String.valueOf(System.currentTimeMillis()));
        
        defaultMQProducer.start();

        log.info("DefaultMQProudcer start success!");

    }
    
    /**
     * Spring bean destroy-method
     */
    public void destroy() {
        defaultMQProducer.shutdown();
    }
    
    public DefaultMQProducer getDefaultMQProducer() {
        return defaultMQProducer;
    }
    
    public void setProducerGroup(String producerGroup) {
        this.producerGroup = producerGroup;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

}
