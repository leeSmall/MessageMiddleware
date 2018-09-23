package com.study.demo.vo;

import java.util.UUID;

/**
 * 
 * @Description: 用户信息实体
 * @author leeSmall
 * @date 2018年9月18日
 *
 */
public class User {

    private final String userId;
    private final String userName;
    private final String email;
    private final String phoneNumber;

    public User(String userId, String userName, String email, String phoneNumber) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static User makeUser(String userName,String email,String phoneNumber){
        String userId = UUID.randomUUID().toString();
        return new User(userId,userName,email,phoneNumber);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
