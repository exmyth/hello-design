package com.exmyth.hello.design.pattern.structural.proxy;

public class OrderServiceImpl implements IOrderService {
    private IOrderDao orderDao;

    @Override
    public int saveOrder(Order order) {
        //Spring会自己注入，这里我们直接new了
        orderDao = new OrderDaoImpl();
        System.out.println("Service层调用dao层添加Order");
        return orderDao.insertOrder(order);
    }
}