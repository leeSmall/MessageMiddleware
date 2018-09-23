package com.study.demo.service.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.study.demo.service.busi.SendSms;

/**
 * 
 * @Description: RabbitMQ消息消费端监听sms消息类
 * @author leeSmall
 * @date 2018年9月18日
 *
 */
@Component
public class ProcessUserSms implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(ProcessUserSms.class);

    @Autowired
    private SendSms sendSms;

    public void onMessage(Message message) {
        logger.info("accept message,ready process......");
        sendSms.sendSms(new String(message.getBody()));

    }
}
