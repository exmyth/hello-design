package com.exmyth.hello.design.pattern.creational.singleton.v7;

public class StaticInnerClassSingleton {
    /**
     * 静态内部类
     * 自己容易犯的错误：静态内部类不要加（），不是方法。
     */
    private static class InnerClass{
        private static StaticInnerClassSingleton staticInnerClassSingleton = new StaticInnerClassSingleton();
    }

    public static StaticInnerClassSingleton getInstance(){
        //这里如何进行初始化呢？
        return InnerClass.staticInnerClassSingleton;
    }

    /**
     * 注意一定要写私有的构造函数，否则的话容易被外部类实例化该类
     */
    private StaticInnerClassSingleton(){
        if(InnerClass.staticInnerClassSingleton != null){
            throw new RuntimeException("单例构造器禁止反射！");
        }
    }
}