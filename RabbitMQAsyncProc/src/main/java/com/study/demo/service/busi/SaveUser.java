package com.study.demo.service.busi;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.study.demo.vo.User;

/**
 * 
 * @Description: 保存用户数据到数据库
 * @author leeSmall
 * @date 2018年9月18日
 *
 */
@Service
public class SaveUser {

    private ConcurrentHashMap<String,User> userData =
            new ConcurrentHashMap<String, User>();

    public void saveUser(User user){
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userData.putIfAbsent(user.getUserId(),user);
    }

    public User getUser(String userId){
        return userData.get(userId);
    }


}
