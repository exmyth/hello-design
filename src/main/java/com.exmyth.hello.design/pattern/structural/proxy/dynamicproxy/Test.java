package com.exmyth.hello.design.pattern.structural.proxy.dynamicproxy;


import com.exmyth.hello.design.pattern.structural.proxy.IOrderService;
import com.exmyth.hello.design.pattern.structural.proxy.Order;
import com.exmyth.hello.design.pattern.structural.proxy.OrderServiceImpl;

public class Test {
    public static void main(String [] args){
        Order order = new Order();
        order.setUserID(1);
        /**
         * new OrderServiceDynamicProxy(order) 该方法已经生成了一个新的代理类
         * 它的bind方法返回了原目标类，强转之后变成了原目标类。
         */
        IOrderService orderServiceDynamicProxy = (IOrderService) new OrderServiceDynamicProxy(new OrderServiceImpl()).bind();
        //注意，执行saveOrder方法，最终会执行invode方法。
        orderServiceDynamicProxy.saveOrder(order);
    }
}