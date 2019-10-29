package com.exmyth.hello.design.pattern.structural.proxy;

/**
 * 建立订单实体类
 */
public class Order {
    private Object orderInfo;
    //之所以选择integer类型，是为了方便OrderServiceStaticProxy静态代理类进行分库
    private Integer userID;

    public Object getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(Object orderInfo) {
        this.orderInfo = orderInfo;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}