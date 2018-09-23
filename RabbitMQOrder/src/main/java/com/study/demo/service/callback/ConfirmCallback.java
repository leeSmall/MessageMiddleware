package com.study.demo.service.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Service;

/**
 * 
 * @Description: RabbitMQ发送者确认
 * @author leeSmall
 * @date 2018年9月19日
 *
 */
@Service
public class ConfirmCallback implements RabbitTemplate.ConfirmCallback {
    private Logger logger = LoggerFactory.getLogger(ConfirmCallback.class);

    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            logger.info("消息确认发送给mq成功");
        } else {
            //处理失败的消息
            logger.info("消息发送给mq失败,考虑重发:"+cause);
        }
    }
}
