package com.study.demo.mq.producer.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * 
 * @Description: Topic生产者发送消息到Topic
 * @author leeSmall
 * @date 2018年9月13日
 *
 */
@Component("topicSender")
public class TopicSender {

	@Autowired
	@Qualifier("jmsTopicTemplate")
	private JmsTemplate jmsTemplate;

	public void send(String queueName,final String message){
		jmsTemplate.send(queueName, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				Message msg = session.createTextMessage(message);
				return msg;
			}
		});
	}
}
