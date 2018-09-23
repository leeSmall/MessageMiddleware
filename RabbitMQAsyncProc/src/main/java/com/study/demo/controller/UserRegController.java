package com.study.demo.controller;

import com.study.demo.service.IUserReg;
import com.study.demo.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @Description: 用户注册控制器
 * @author leeSmall
 * @date 2018年9月18日
 *
 */
@Controller
public class UserRegController {

    private static final String SUCCESS = "suc";
    private static final String FAILUER = "failure";

    @Autowired
    @Qualifier("async")
    private  IUserReg userReg;

    @RequestMapping("/userReg")
    public String userReg(){
        return "index";
    }

    @RequestMapping("/saveUser")
    @ResponseBody
    public String saveUser(@RequestParam("userName")String userName,
                           @RequestParam("email")String email,
                           @RequestParam("phoneNumber")String phoneNumber){
        try {
            if (userReg.userRegister(User.makeUser(userName,email,phoneNumber)))
                return SUCCESS;
            else
                return FAILUER;
        } catch (Exception e) {
            return FAILUER;
        }
    }


}
