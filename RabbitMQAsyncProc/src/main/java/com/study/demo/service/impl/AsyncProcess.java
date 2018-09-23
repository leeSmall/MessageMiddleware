package com.study.demo.service.impl;

import com.study.demo.service.IUserReg;
import com.study.demo.service.busi.SaveUser;
import com.study.demo.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


/**
 * 
 * @Description: RabbitMQ实现的异步用户注册
 * @author leeSmall
 * @date 2018年9月18日
 *
 */
@Service
@Qualifier("async")
public class AsyncProcess implements IUserReg{

    private Logger logger = LoggerFactory.getLogger(AsyncProcess.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private SaveUser saveUser;


    public boolean userRegister(User user) {
        try {
            saveUser.saveUser(user);
            rabbitTemplate.send("user-reg-exchange","email",
                    new Message(user.getEmail().getBytes(),new MessageProperties()));
            rabbitTemplate.send("user-reg-exchange","sms",
                    new Message(user.getEmail().getBytes(),new MessageProperties()));
        } catch (AmqpException e) {
            logger.error(e.toString());
            return  false;
        }

        return true;
    }
}
