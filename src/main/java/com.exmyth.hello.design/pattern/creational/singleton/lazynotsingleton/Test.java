package com.exmyth.hello.design.pattern.creational.singleton.lazynotsingleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Test {

      /**
     * 懒汉模式 防御反射1
     * @param args
     */
    public static void main(String [] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

//反射攻击
        Class objectClass = LazySingleton.class;
//        Class.forName(HangrySingleton.class.getName());
        //通过反射拿到构造器
        Constructor constructor = objectClass.getDeclaredConstructor();

        //设置权限为true，不设置的话，私有权限反射报错
        constructor.setAccessible(true);
        //调换了顺序，这样懒汉式单例类的对象为空判断失效，同样情况下饿汉和静态类不受影响，因为饿汉最类加载的时候就完成了对象的初始化，类的使用过程中对象是一直有值的
        //通过反射拿到对象
        LazySingleton instance2 = (LazySingleton) constructor.newInstance();
        //通过单例拿到对象
        LazySingleton instance = LazySingleton.getInstance();
        System.out.println(instance);
        System.out.println(instance2);
        System.out.println(instance==instance2);
    }
}