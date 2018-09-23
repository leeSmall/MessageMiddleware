package com.study.demo.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 
 * @Description: 库存数据服务
 * @author leeSmall
 * @date 2018年9月19日
 *
 */
@Service
public class Depot {

    private static Logger logger = LoggerFactory.getLogger(Depot.class);

    private ConcurrentHashMap<String,Integer> goodsData =
            new ConcurrentHashMap<String, Integer>();

    @PostConstruct
    public void initDepot(){
        goodsData.put("001",1000);
        goodsData.put("002",500);
        goodsData.put("003",600);
        goodsData.put("004",700);
    }


    //增加库存
    public void inDepot(String goodsId,int addAmout){
        logger.info("+++++++++++++++++增加商品："+goodsId+"库存,数量为："+addAmout);
        int newValue = goodsData.compute(goodsId, new BiFunction<String, Integer, Integer>() {
            public Integer apply(String s, Integer integer) {
                return integer == null ? addAmout : integer + addAmout;
            }
        });
        logger.info("+++++++++++++++++商品："+goodsId+"库存,数量变为："+newValue);
    }

    //减少库存
    public void outDepot(String goodsId,int reduceAmout){
        logger.info("-------------------减少商品："+goodsId+"库存,数量为："+reduceAmout);
        int newValue = goodsData.compute(goodsId, new BiFunction<String, Integer, Integer>() {
            public Integer apply(String s, Integer integer) {
                return integer == null ? 0 : integer - reduceAmout;
            }
        });
        logger.info("-------------------商品："+goodsId+"库存,数量变为："+newValue);
    }

    public int getGoodsAmount(String goodsId){
        return goodsData.get(goodsId);
    }
}
