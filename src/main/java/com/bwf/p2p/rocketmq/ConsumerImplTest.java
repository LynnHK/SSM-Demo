package com.bwf.p2p.rocketmq;

import java.util.List;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;

public class ConsumerImplTest implements MessageListenerConcurrently {

	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
//		System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + msgs);
		MessageExt ext = msgs.get(0);
		System.out.println(new String(ext.getBody()));
        //返回消费状态
        //CONSUME_SUCCESS 消费成功
        //RECONSUME_LATER 消费失败，需要稍后重新消费
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}

}
