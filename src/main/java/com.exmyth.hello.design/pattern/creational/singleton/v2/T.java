package com.exmyth.hello.design.pattern.creational.singleton.v2;

/**
 * 注：该类为线程类，调用LazySingleton
 */
public class T implements Runnable{

    /*@Override
    public void run() {
        LazySingleton lazySingleton = LazySingleton.getInstance();
        System.out.println(Thread.currentThread().getName()+"==="+lazySingleton);

    }*/

    @Override
    public void run() {
        LazyDoubleCheckSingleton lazyDoubleCheckSingleton = LazyDoubleCheckSingleton.getInstance();
        System.out.println(Thread.currentThread().getName()+"==="+lazyDoubleCheckSingleton);
    }
}