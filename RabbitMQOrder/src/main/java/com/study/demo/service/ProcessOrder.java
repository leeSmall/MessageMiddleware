package com.study.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 
 * @Description: 处理订单业务类
 * @author leeSmall
 * @date 2018年9月19日
 *
 */
@Service
public class ProcessOrder {
    private Logger logger = LoggerFactory.getLogger(ProcessOrder.class);

    @Autowired
    @Qualifier("mq")
    private IProDepot proDepot;

    public void processOrder(String goodsId,int amount){
        try {
            Thread.sleep(80);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("--------------------["+goodsId+"]订单入库完成，准备变动库存！");
        proDepot.processDepot(goodsId,amount);

    }

}
