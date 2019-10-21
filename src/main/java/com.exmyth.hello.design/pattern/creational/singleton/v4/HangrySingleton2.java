package com.exmyth.hello.design.pattern.creational.singleton.v4;

public class HangrySingleton2 {


    /**
     * 声明私有常量，当类初始化的时候就已经赋值了。饿汉式在类初始化的时候只加载一次。
     * 所以也不会存在多线程的问题。
     */
    private final static HangrySingleton2 hangrySingleton;

    static {
        hangrySingleton= new HangrySingleton2();
    }

    /**
     * 声明私有构造方法
     */
    private HangrySingleton2(){

    }

    /**
     * 提供对外接口，获得对象
     * @return
     */
    public HangrySingleton2 getInstance(){
        return hangrySingleton;
    }
}