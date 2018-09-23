package com.study.demo.service.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * 
 * @Description: RabbitMQ返回确认-RabbitMQ服务内部出错时回调
 * @author leeSmall
 * @date 2018年9月19日
 *
 */
@Service
public class SendReturnCallback implements RabbitTemplate.ReturnCallback {

    private Logger logger = LoggerFactory.getLogger(SendReturnCallback.class);

    public void returnedMessage(Message message, int replyCode,
                                String replyText, String exchange,
                                String routingKey) {
        logger.info("Returned replyText："+replyText);
        logger.info("Returned exchange："+exchange);
        logger.info("Returned routingKey："+routingKey);
        String msgJson  = new String(message.getBody());
        logger.info("Returned Message："+msgJson);
    }
}
