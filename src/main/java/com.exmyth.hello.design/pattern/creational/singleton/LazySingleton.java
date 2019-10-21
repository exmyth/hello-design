package com.exmyth.hello.design.pattern.creational.singleton;

public class LazySingleton {
    /*
    属性私有，其他外部类，无法调用该属性，安全
     */
    private static LazySingleton lazySingleton = null;

    /**
     * 构造方法私有，其他类无法实例化该类
     */
    private LazySingleton(){
    }

    /**
     * 这里写静态方法：因为外部类无法实例化创建出该类，
     * 只能通过该类的静态方法获取到该类。
     * @return
     */
    public static LazySingleton getInstance(){
        if(lazySingleton == null){
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }
}