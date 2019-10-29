package com.exmyth.hello.design.pattern.structural.proxy.staticProxy;


import com.exmyth.hello.design.pattern.structural.proxy.IOrderService;
import com.exmyth.hello.design.pattern.structural.proxy.Order;
import com.exmyth.hello.design.pattern.structural.proxy.OrderServiceImpl;
import com.exmyth.hello.design.pattern.structural.proxy.db.DataSourceContextHolder;

public class OrderServiceStaticProxy {
    //spring中会注入，这里我new一下
    private IOrderService orderService = new OrderServiceImpl();

    /**
     * 添加前置方法和后置方法
     * @param order
     * @return
     */
    public int saveOrder(Order order){
        beforeMethod(order);
        int a = orderService.saveOrder(order);
        afterMethod();
        return a;
    }
    /**
     * 这里参照spring aop的做法，增加了前置通知方法  方法的增强
     * @param order
     */
    private void beforeMethod(Order order){
        System.out.println("静态代理  前置方法");

        /**
         * 这里添加分库方法，根据user取模，根据余数进行分库
         */
        int userID = order.getUserID();
        int dbRouter = userID%2;

        //todo设置datasource，记住，dbType一定要和我们xml中配置的key相同
        DataSourceContextHolder.setDBType("db"+String.valueOf(dbRouter));

        System.out.println("静态代理分配到 【db"+dbRouter+"】数据库进行处理数据！");
    }

    /**
     * 这里参照spring aop的做法，增加了后置通知方法　　方法的增强
     */
    private void afterMethod(){
        System.out.println("静态代理  后置方法");
    }
}