package com.study.demo.vo;

import java.io.Serializable;

/**
 * 
 * @Description: 商品实体
 * @author leeSmall
 * @date 2018年9月19日
 *
 */
public class GoodTransferVo  implements Serializable {

    
	private static final long serialVersionUID = 7702481109435751937L;
	
	/**
	 * 商品id
	 */
	private String goodsId;
	
	/**
	 * 改变的库存量
	 */
    private int changeAmount;
    
	/**
	 * 入库或者出库
	 */
    private boolean inOrOut;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public int getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(int changeAmount) {
        this.changeAmount = changeAmount;
    }

    public boolean isInOrOut() {
        return inOrOut;
    }

    public void setInOrOut(boolean inOrOut) {
        this.inOrOut = inOrOut;
    }
}
