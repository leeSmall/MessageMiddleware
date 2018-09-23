package com.study.demo.service.busi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 
 * @Description: 发送邮件的服务
 * @author leeSmall
 * @date 2018年9月18日
 *
 */
@Service
public class SendEmail {
    private Logger logger = LoggerFactory.getLogger(SendEmail.class);

    public void sendEmail(String email){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("-------------Already Send email to "+email);
    }

}
