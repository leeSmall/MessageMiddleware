package study.demo.consumerconfirm;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 
 * @Description: 消费者自行确认生产者
 * @author leeSmall
 * @date 2018年9月15日
 *
 */
public class ConsumerConfirmProducer {

	//交换器
    private final static String EXCHANGE_NAME = "direct_cc_confirm_1";
    
    //路由键
    private final static String ROUTE_KEY = "error";

    public static void main(String[] args) throws IOException, TimeoutException,
            InterruptedException {

    	//1.创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        
        //设置要连接的RabbitMQ服务器的地址
        factory.setHost("127.0.0.1");
        
        //设置用户名 这里使用缺省的
        //factory.setUsername(..);
        
        //设置连接断开  这里使用缺省的
        //factory.setPort();
        
        //设置虚拟主机 这里使用缺省的
        //factory.setVirtualHost();


        //2.通过连接工厂创建一个连接
        Connection connection = factory.newConnection();

        //3.通过连接创建一个信道 信道是用来传送数据的
        Channel channel = connection.createChannel();

        //4.通过信道声明一个交换器 第一个参数时交换器的名字 第二个参数时交换器的种类
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        //发布消息的交换器上
        for(int i=0;i<10;i++){
        	//要发送的消息
            String message = "Hello world_"+(i+1);

            /**
             * 发送消息到交换器上
             * 参数1：交换器的名字
             * 参数2：路由键
             * 参数3：BasicProperties
             * 参数4：要发送的消息
             */
            channel.basicPublish(EXCHANGE_NAME,ROUTE_KEY,null,message.getBytes());
            System.out.println("Sent "+ROUTE_KEY+":"+message);

        }

        channel.close();
        connection.close();

    }

}
