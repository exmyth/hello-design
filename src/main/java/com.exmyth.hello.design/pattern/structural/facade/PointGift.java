package com.exmyth.hello.design.pattern.structural.facade;

/**
 * 礼物积分类，作为基类存在，其他服务类调取本类
 */
public class PointGift {
    /**
     * 通过构造器声明积分礼物，方便Test类进行测试
     * @param giftName
     */
    public PointGift(String giftName) {
        this.giftName = giftName;
    }

    private String giftName;//礼物名称

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }
}