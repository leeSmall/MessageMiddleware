package com.study.demo.service.busi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 
 * @Description: 发送短信的服务
 * @author leeSmall
 * @date 2018年9月18日
 *
 */
@Service
public class SendSms {

    private Logger logger = LoggerFactory.getLogger(SendSms.class);

    public void sendSms(String phoneNumber){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("-------------Already Send Sms to "+phoneNumber);
    }

}
