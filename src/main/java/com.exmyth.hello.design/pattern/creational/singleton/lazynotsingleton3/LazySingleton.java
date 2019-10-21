package com.exmyth.hello.design.pattern.creational.singleton.lazynotsingleton3;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
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
     * 这里写静态方法：因为外部类无法实例化创建出该类，
     * 只能通过该类的静态方法获取到该类。
     * @return
     */
   /* public static synchronized LazySingleton getInstance(){
        if(lazySingleton == null){
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }*/

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


    public static void main(String [] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

//反射攻击
        Class objectClass = LazySingleton.class;
//        Class.forName(HangrySingleton.class.getName());
        //通过反射拿到构造器
        Constructor constructor = objectClass.getDeclaredConstructor();

        //设置权限为true，不设置的话，私有权限反射报错
        constructor.setAccessible(true);

        //通过单例拿到对象
        LazySingleton instance = LazySingleton.getInstance();

        //在反射之前 获取flag对象并把其权限置成true
        Field field = instance.getClass().getDeclaredField("flag");
        field.setAccessible(true);
        field.set(instance,true);

        //通过反射拿到对象
        LazySingleton instance2 = (LazySingleton) constructor.newInstance();

        System.out.println(instance);
        System.out.println(instance2);
        System.out.println(instance==instance2);
    }
}