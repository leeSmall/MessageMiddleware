package com.study.demo.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.study.demo.service.DepotManager;
import com.study.demo.vo.GoodTransferVo;

/**
 * 
 * @Description: 消息机制处理库存
 * @author leeSmall
 * @date 2018年9月19日
 *
 */
@Service
public class ProcessDepot  implements ChannelAwareMessageListener {

    private static Logger logger = LoggerFactory.getLogger(ProcessDepot.class);

    @Autowired
    private DepotManager depotManager;

    private static Gson gson = new Gson();

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            String msg = new String(message.getBody());
            logger.info(">>>>>>>>>>>>>>接收到消息:"+msg);
            GoodTransferVo goodTransferVo = gson.fromJson(msg,GoodTransferVo.class);
            try {
                depotManager.operDepot(goodTransferVo);
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),
                        false);
                logger.info(">>>>>>>>>>>>>>库存处理完成，应答Mq服务");
            } catch (Exception e) {
                logger.error(e.getMessage());
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),
                        false,true);
                logger.info(">>>>>>>>>>>>>>库存处理失败，拒绝消息，要求Mq重新派发");
                throw e;
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }


}
