package com.study.demo.tools;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.study.demo.vo.User;

/**
 * 
 * @Description: 统计花费时间工具类
 * @author leeSmall
 * @date 2018年9月18日
 *
 */
@Aspect
@Service
public class StatTime {

    private Logger logger = LoggerFactory.getLogger(StatTime.class);
    private User user;

    public StatTime() {
        logger.info("************Aop开启");
    }

    @Pointcut("execution(* com.study.demo.service.impl.*.*Register(..))")
    public void stat(){}

    @Around("stat()&&args(user)")
    public Object statTime(ProceedingJoinPoint proceedingJoinPoint,User user){
        this.user = user;
        long start = System.currentTimeMillis();
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed(new Object[]{this.user});
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        logger.info("************spend time : "+(System.currentTimeMillis()-start)+"ms");
        return result;

    }

}
