package com.study.demo.service.fanout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * 
 * @Description: RabbitMQ与Spring集成fanout消费者
 * @author leeSmall
 * @date 2018年9月17日
 *
 */
@Component
public class FanoutService_H3 implements MessageListener{
    private Logger logger = LoggerFactory.getLogger(FanoutService_H3.class);
    public void onMessage(Message message) {
        logger.info("Get message:"+new String(message.getBody()));
    }
}
