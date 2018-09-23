package com.study.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 动脑学院-Mark老师
 * 创建日期：2017/10/26
 * 创建时间: 22:37
 */
@Controller
public class DepotController {

    @RequestMapping("/depotIndex")
    public String userReg(){
        return "index";
    }
}
