package com.exmyth.hello.design.pattern.creational.singleton.v4;

public class HangrySingleton {


    /**
     * 声明私有常量，当类初始化的时候就已经赋值了。饿汉式在类初始化的时候只加载一次。
     * 所以也不会存在多线程的问题。懒汉式不用声明final，因为它不是在类加载的时候就初始化好的。
     */
    private final static HangrySingleton hangrySingleton = new HangrySingleton();

    /**
     * 声明私有构造方法
     */
    private HangrySingleton(){

    }

    /**
     * 提供对外接口，获得对象
     * @return
     */
    public HangrySingleton getInstance(){
        return hangrySingleton;
    }
}