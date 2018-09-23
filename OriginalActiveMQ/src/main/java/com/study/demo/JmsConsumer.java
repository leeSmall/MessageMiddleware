package com.study.demo;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 
 * @Description: java原生ActiveMQ的API的使用-JMS消息消费者
 * @author leeSmall
 * @date 2018年9月13日
 *
 */
public class JmsConsumer {

    //默认连接用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    //默认连接密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    //默认连接地址
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;

    //编码过程参考JMS对象模型的几个要素
    public static void main(String[] args) {
    	//1.连接工厂:创建一个JMS连接
        ConnectionFactory connectionFactory;
        
        //2.JMS连接:客户端和服务器之间的一个连接
        Connection connection = null;

        //3.JMS会话:客户端和服务器会话的状态，建立在JMS连接之上的
        Session session;
        
        //4.JMS目的:消息队列
        Destination destination;

        //5.JMS消费者：接收和消费消息
        MessageConsumer messageConsumer;

        //创建一个ActiveMQ的连接工厂
        connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEURL);

        try {
        	//通过连接工厂创建一个JMS连接
            connection = connectionFactory.createConnection();
            
            //开启JMS连接
            connection.start();
            
            /*
			 * 通过JMS连接创建一个JMS会话
			 * 
			 * createSession参数取值说明：
			 *  第一个参数：为true表示启用事务 
			 *  第二个参数：消息的确认模式：
			 * 				AUTO_ACKNOWLEDGE 自动签收
			 * 				CLIENT_ACKNOWLEDGE 客户端自行调用
			 * 				ACKNOWLEDGE 方法签收 
			 * 				DUPS_OK_ACKNOWLEDGE 不是必须签收
			 * 消息可能会重复发送 在第二次重新传送消息的时候，消息头的JmsDelivered会被置为true标示当前消息已经传送过一次，
			 * 客户端需要进行消息的重复处理控制。
			 */
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            
            //通过JMS会话创建一个JMS目的，即消息队列
            destination = session.createQueue("firstMSG");

            //通过JMS会话和JMS目的创建一个JMS消费者，即消息消费者
            messageConsumer = session.createConsumer(destination);

            //读取消息
            while(true){
            	//使用receive方法消费一个消息，如果超过10s没有得到消息就跳过
                TextMessage textMessage = (TextMessage)messageConsumer.receive(10000);
                if(textMessage != null){
                    System.out.println("Accept msg : "+textMessage.getText());
                }else{
                    break;
                }

            }


        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
