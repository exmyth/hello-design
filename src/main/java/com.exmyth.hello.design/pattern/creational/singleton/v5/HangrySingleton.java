package com.exmyth.hello.design.pattern.creational.singleton.v5;


import java.io.Serializable;

public class HangrySingleton implements Serializable {


    /**
     * 声明私有常量，当类初始化的时候就已经赋值了。饿汉式在类初始化的时候只加载一次。
     * 所以也不会存在多线程的问题。
     */
    private final static HangrySingleton hangrySingleton;

    static {
        hangrySingleton= new HangrySingleton();
    }

    /**
     * 声明私有构造方法
     */
    private HangrySingleton(){

    }

    /**
     * 防止序列化和反序列化对单例模式进行破坏
     */
    private Object readResolve(){
        return hangrySingleton;
    }
   

    /**
     * 提供对外接口，获得对象
     * @return
     */
    public static HangrySingleton getInstance(){
        return hangrySingleton;
    }
}