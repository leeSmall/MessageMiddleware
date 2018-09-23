
package com.study.demo.mq.consumer.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

/**
 * 
 * @Description: 队列消息监听器
 * @author leeSmall
 * @date 2018年9月13日
 *
 */
@Component
public class QueueReceiver1 implements MessageListener {

	/*@Autowired
	private ReplyTo replyTo;*/

	public void onMessage(Message message) {
		try {
			String textMsg = ((TextMessage)message).getText();
			System.out.println("QueueReceiver1 accept msg : "+textMsg);
			//do my 业务工作
//			replyTo.send(textMsg,message);
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
}
