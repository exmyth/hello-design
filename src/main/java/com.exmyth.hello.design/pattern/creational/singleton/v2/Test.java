package com.exmyth.hello.design.pattern.creational.singleton.v2;

public class Test {

    /*public static void main(String [] args){
        //这样写异常，因为构造方法私有
//        LazySingleton lazySingleton = new LazySingleton();
       LazySingleton lazySingleton = LazySingleton.getInstance();
       System.out.println(lazySingleton);
    }*/

    public static void main(String [] args){
        Thread thread1 = new Thread(new T());
        Thread thread2 = new Thread(new T());
        thread1.start();
        thread2.start();
        System.out.println("结束了！！！");
    }
}