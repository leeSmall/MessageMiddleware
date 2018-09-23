package com.study.demo.service;

import com.study.demo.vo.GoodTransferVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @Description: 库存服务管理
 * @author leeSmall
 * @date 2018年9月19日
 *
 */
@Service
public class DepotManager {

    @Autowired
    private Depot depot;

    public void operDepot(GoodTransferVo goodTransferVo){
        if(goodTransferVo.isInOrOut()){
            depot.inDepot(goodTransferVo.getGoodsId(),goodTransferVo.getChangeAmount());
        }else{
            depot.outDepot(goodTransferVo.getGoodsId(),goodTransferVo.getChangeAmount());
        }
    }


}
