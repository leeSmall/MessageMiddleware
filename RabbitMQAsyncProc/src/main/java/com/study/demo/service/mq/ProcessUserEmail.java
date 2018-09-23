package com.study.demo.service.mq;

import com.study.demo.service.busi.SendEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @Description: RabbitMQ消息消费端监听邮件消息类
 * @author leeSmall
 * @date 2018年9月18日
 *
 */
@Component
public class ProcessUserEmail implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(ProcessUserEmail.class);

    @Autowired
    private SendEmail sendEmail;

    public void onMessage(Message message) {
        logger.info("accept message,ready process......");
        sendEmail.sendEmail(new String(message.getBody()));

    }
}
