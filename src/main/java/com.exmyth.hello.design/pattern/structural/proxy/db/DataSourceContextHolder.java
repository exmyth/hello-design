package com.exmyth.hello.design.pattern.structural.proxy.db;

/**
 * 分库操作：
 * dbRouter上下文类 ,在执行dao层之前，如果我们设置了 setDBType设置了dbType为db1 或者 db0，dao层就会去连接对应的数据库。
 * db0和db1就是Spring容器中我们配置的beanID
 */
public class DataSourceContextHolder {
    //该变量可以存放dataSource的beanName
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<String>();

    public static void setDBType(String dbType){
        CONTEXT_HOLDER.set(dbType);
    }

    public static void clearDBType(String dbType){
        CONTEXT_HOLDER.remove();
    }

    public static String getDBType(){
        return (String) CONTEXT_HOLDER.get();
    }
}