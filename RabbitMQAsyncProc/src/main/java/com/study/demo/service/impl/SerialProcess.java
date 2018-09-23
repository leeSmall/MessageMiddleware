package com.study.demo.service.impl;

import com.study.demo.service.IUserReg;
import com.study.demo.service.busi.SaveUser;
import com.study.demo.service.busi.SendEmail;
import com.study.demo.service.busi.SendSms;
import com.study.demo.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 
 * @Description: 串行的用户注册
 * @author leeSmall
 * @date 2018年9月18日
 *
 */
@Service
@Qualifier("serial")
public class SerialProcess implements IUserReg {

    @Autowired
    private SaveUser saveUser;
    @Autowired
    private SendEmail sendEmail;
    @Autowired
    private SendSms sendSms;

    public boolean userRegister(User user) {
        try {
            saveUser.saveUser(user);
            sendEmail.sendEmail(user.getEmail());
            sendSms.sendSms(user.getPhoneNumber());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
