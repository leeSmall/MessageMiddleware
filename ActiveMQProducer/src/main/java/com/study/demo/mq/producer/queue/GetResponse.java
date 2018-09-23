package com.study.demo.mq.producer.queue;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 动脑学院-Mark老师
 * 创建日期：2017/11/02
 * 创建时间: 22:19
 * 接收消费者应答的类
 */
@Component
public class GetResponse implements MessageListener {

    public void onMessage(Message message) {
        try {
            String textMsg = ((TextMessage)message).getText();
            System.out.println("GetResponse accept response : "+textMsg);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
