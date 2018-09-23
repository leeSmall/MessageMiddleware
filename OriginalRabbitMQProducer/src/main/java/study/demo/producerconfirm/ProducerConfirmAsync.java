package study.demo.producerconfirm;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;



/**
 * 
 * @Description: 生产者(发送方)确认异步模式
 * @author leeSmall
 * @date 2018年9月16日
 *
 */
public class ProducerConfirmAsync {

    private final static String EXCHANGE_NAME = "producer_confirm";

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

        //将信道设置为发送方确认
        channel.confirmSelect();

        //信道被关闭监听器 用于RabbitMQ服务器断线重连
        //channel.addShutdownListener();

        /**
         * 生产者异步确认监听
         * 参数deliveryTag代表了当前channel唯一的投递
         * 参数multiple:false
         * 
         */
        channel.addConfirmListener(new ConfirmListener() {
        	//RabbitMQ服务器确认收到消息
            public void handleAck(long deliveryTag, boolean multiple)
                    throws IOException {
                System.out.println("RabbitMQ服务器确认收到消息Ack deliveryTag="+deliveryTag
                        +"multiple:"+multiple);
            }

            //RabbitMQ服务器由于自己内部出现故障没有收到消息
            public void handleNack(long deliveryTag, boolean multiple)
                    throws IOException {
                System.out.println("RabbitMQ服务没有收到消息Ack deliveryTag="+deliveryTag
                        +"multiple:"+multiple);
            }
        });

        //生产者异步返回监听 这里和发布消息时的mandatory参数有关
        //参数mandatory：mandatory=true，投递消息时无法找到一个合适的队列,把消息返回给生产者,mandatory=false 丢弃消息(缺省)
        channel.addReturnListener(new ReturnListener() {
            public void handleReturn(int replyCode, String replyText,
                                     String exchange, String routingKey,
                                     AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String msg = new String(body);
                System.out.println("replyText:"+replyText);
                System.out.println("exchange:"+exchange);
                System.out.println("routingKey:"+routingKey);
                System.out.println("msg:"+msg);
            }
        });


        //声明一组路由键
        String[] routingKeys={"error","info","warning"};
        //发送消息到交换器上
        for(int i=0;i<3;i++){
            String routingKey = routingKeys[i%3];
            // 发送的消息
            String message = "Hello World_"+(i+1)+("_"+System.currentTimeMillis());
            
            //通过路由键把消息发送到交换器上
            //参数mandatory: mandatory=true，投递消息时无法找到一个合适的队列,把消息返回给生产者,
            //              mandatory=false 丢弃消息(缺省)
            channel.basicPublish(EXCHANGE_NAME, routingKey, false,
                    null, message.getBytes());
            System.out.println("----------------------------------------------------");
            System.out.println(" Sent Message: [" + routingKey +"]:'"+ message + "'");
            //sleep一下让程序不快速结束 可以看到RabbitMQ服务器的响应
            Thread.sleep(1000);
        }

        // 关闭信道和连接
        channel.close();
        connection.close();
    }


}
