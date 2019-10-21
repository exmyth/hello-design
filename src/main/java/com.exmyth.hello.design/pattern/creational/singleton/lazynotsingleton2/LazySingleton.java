package com.exmyth.hello.design.pattern.creational.singleton.lazynotsingleton2;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class LazySingleton {
    /*
    属性私有，其他外部类，无法调用该属性，安全
     */
    private static LazySingleton lazySingleton = null;
    private static boolean flag = true;


    /**
     * 构造方法私有，其他类无法实例化该类
     */
    private LazySingleton(){
        if(flag){
            flag = false;
        }else{
            throw new RuntimeException("单例构造器禁止反射！");
        }
    }

    /**
     * 换一种写法，
     *
     * @return
     */
      public static LazySingleton getInstance(){
          synchronized (LazySingleton.class){
              if(lazySingleton == null){
                  lazySingleton = new LazySingleton();
              }
          }
          return lazySingleton;
    }


    public static void main(String [] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

//反射攻击
        Class objectClass = LazySingleton.class;
//        Class.forName(HangrySingleton.class.getName());
        //通过反射拿到构造器
        Constructor constructor = objectClass.getDeclaredConstructor();

        //设置权限为true，不设置的话，私有权限反射报错
        constructor.setAccessible(true);
        //通过反射拿到对象
        LazySingleton instance2 = (LazySingleton) constructor.newInstance();
        //通过单例拿到对象
        LazySingleton instance = LazySingleton.getInstance();
        System.out.println(instance);
        System.out.println(instance2);
        System.out.println(instance==instance2);
    }
}