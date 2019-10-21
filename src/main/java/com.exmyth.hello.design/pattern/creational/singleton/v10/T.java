package com.exmyth.hello.design.pattern.creational.singleton.v10;

/**
 * 注：该类为线程类，调用LazySingleton
 */
public class T implements Runnable{

    @Override
    public void run() {
/*        StaticInnerClassSingleton staticInnerClassSingleton = StaticInnerClassSingleton.getInstance();*/
        ThreadLocalInstance threadLocalInstance = ThreadLocalInstance.getInstance();
        System.out.println(Thread.currentThread().getName()+"==="+threadLocalInstance);
    }
}