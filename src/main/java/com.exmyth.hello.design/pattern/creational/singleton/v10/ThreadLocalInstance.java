package com.exmyth.hello.design.pattern.creational.singleton.v10;

public class ThreadLocalInstance {
    /**
     * 预防外部类 实例化本类
     */
    private  ThreadLocalInstance(){

    }

   public static final ThreadLocal<ThreadLocalInstance> threadLocalInstance
           = new ThreadLocal<ThreadLocalInstance>(){
       /**
        * 重写初始化方法
        */
       @Override
       protected ThreadLocalInstance initialValue(){
           return new ThreadLocalInstance();
        }
   };

    /**
     * 获得实例
     * @return
     */
    public static ThreadLocalInstance getInstance(){
       return threadLocalInstance.get();
    }
}