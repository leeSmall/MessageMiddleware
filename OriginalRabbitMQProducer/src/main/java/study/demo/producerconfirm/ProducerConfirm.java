package study.demo.producerconfirm;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 
 * @Description: 生产者(发送方)确认同步模式
 * @author leeSmall
 * @date 2018年9月16日
 *
 */
public class ProducerConfirm {

    private final static String EXCHANGE_NAME = "producer_confirm";
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
        //将信道设置为发送方确认
        channel.confirmSelect();

        //发布消息的交换器上
        for(int i=0;i<2;i++){
            String msg = "Hello "+(i+1);
            channel.basicPublish(EXCHANGE_NAME,ROUTE_KEY,null,msg.getBytes());
            //等待RabbitMQ返回消息确认消息已送达RabbitMQ服务器
            if (channel.waitForConfirms()){
                System.out.println("发送方同步确认: "+ROUTE_KEY+":"+msg);
            }
        }


        // 关闭频道和连接
        channel.close();
        connection.close();
    }

}
