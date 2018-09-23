package com.study.demo.mq.consumer.topic;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


/**
 * 
 * @Description: Topic消息监听器
 * @author leeSmall
 * @date 2018年9月13日
 *
 */
@Component
public class TopicReceiver1 implements MessageListener {


	public void onMessage(Message message) {
		try {
			String textMsg = ((TextMessage)message).getText();
			System.out.println("TopicReceiver1 accept msg : "+textMsg);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
}
