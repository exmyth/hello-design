package com.exmyth.hello.design.pattern.structural.proxy.dynamicproxy;
import com.exmyth.hello.design.pattern.structural.proxy.Order;
import com.exmyth.hello.design.pattern.structural.proxy.db.DataSourceContextHolder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 目的：抽奖信息和订单等不同的类都可以通过这一个动态代理进行复用，不用每一个都写一个静态代理。
 * 这就是静态代理和动态代理的区别
 * 动态代理是自动生成的，静态代理需要显式的来描述和coding
 */
public class OrderServiceDynamicProxy implements InvocationHandler {
    //目标对象
    public Object target;

    //通过构造方法传入目标对象
    public OrderServiceDynamicProxy(Object target) {
        this.target = target;
    }

    /**
     * 主方法， 调用前置方法，主要方法，以及后置方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //取得目标对象，argObject是目标类，也就是静态代理demo中的订单类
        Object argObject = args[0];
        beforeMethod(argObject);
        Object object = method.invoke(target,args);
        afterMethod();
        return object;
    }

    public Object bind(){
        //得到目标对象的class类
        Class cls = target.getClass();
        //这里边有三个参数，classLoader,复数的interface，它的类型是class,第三个是invoccationHandler 因为本类本身实现了InvocationHandler，所以把本类自己传过去即可。
        return Proxy.newProxyInstance(cls.getClassLoader(),cls.getInterfaces(),this);
    }

    /**
     * 前置方法，用来取模运算
     * @param obj
     */
    private void beforeMethod(Object obj){
        int userID = 0;
        System.out.println("动态代理 before code");
        if(obj instanceof Order){//如果该对象属于Order类
            Order order = (Order) obj;//强转成Order 类
            userID = order.getUserID();
        }
        int dbRouter = userID%2;
        System.out.println("动态代理分配到 【db"+dbRouter+"】数据库进行处理数据！");
        DataSourceContextHolder.setDBType("db"+String.valueOf(dbRouter));
    }

    /**
     * 后置方法
     */
    private void afterMethod(){
        System.out.println("动态代理 after code");
    }

}