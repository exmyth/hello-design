package com.exmyth.hello.design.pattern.structural.proxy;

public class OrderDaoImpl implements IOrderDao{
    @Override
    public int insertOrder(Order order) {
        System.out.println("新增一条订单！");
        return 1;
    }
}