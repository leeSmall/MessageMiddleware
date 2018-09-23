package study.demo.producerconfirm;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 
 * @Description: 发送方确认消费者
 * @author leeSmall
 * @date 2018年9月16日
 *
 */
public class ProducerConfirmConsumer {

    private static final String EXCHANGE_NAME = "producer_confirm";

    public static void main(String[] argv) throws IOException, TimeoutException {
    	//1.创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置要连接的RabbitMQ服务器的地址
        factory.setHost("127.0.0.1");
        
        //2.通过连接工厂创建一个连接
        Connection connection = factory.newConnection();
        
        //3.通过连接创建一个信道 信道是用来传送数据的
        Channel channel = connection.createChannel();
        
        //4.通过信道声明一个交换器 第一个参数时交换器的名字 第二个参数时交换器的种类
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        //5.声明队列
        String queueName = "producer_confirm";
        channel.queueDeclare(queueName,false,false,
                false,null);

        //6.声明一个只消费错误日志的路由键error
        String routingKey = "error";
        
        //7.队列通过路由键绑定到交换器上
        channel.queueBind(queueName,EXCHANGE_NAME,routingKey);
        System.out.println("Waiting message.......");

        // 8.创建队列消费者 设置一个监听器监听消费消息 
        final Consumer consumerB = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println( "Received ["+ envelope.getRoutingKey() + "] "+message);
            }
        };
        //9.消费者自动确认：autoAck参数为true
        channel.basicConsume(queueName, true, consumerB);
    }

}
