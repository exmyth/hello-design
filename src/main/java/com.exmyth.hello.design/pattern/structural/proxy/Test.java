package com.exmyth.hello.design.pattern.structural.proxy;


import com.exmyth.hello.design.pattern.structural.proxy.staticProxy.OrderServiceStaticProxy;

public class Test {

    public static void main(String [] args){
        Order order = new Order();
        order.setUserID(1);
        OrderServiceStaticProxy orderServiceStaticProxy = new OrderServiceStaticProxy();
        orderServiceStaticProxy.saveOrder(order);

        order.setUserID(2);
        orderServiceStaticProxy.saveOrder(order);

    }
}