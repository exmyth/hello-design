package com.exmyth.hello.design.pattern.creational.prototype.v6;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test2 {
    public static void main(String [] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
           /* Date date1 = new Date();
            Pig pig1 = new Pig("peiqi",date1);
            Pig pig2 = (Pig) pig1.clone();
            System.out.println(pig1);
            System.out.println(pig2);


            //预期修改pig1的生日，没想到pig2的生日也改了
            pig1.getBirthday().setTime(0L);
            System.out.println(pig1);
            System.out.println(pig2);*/


           //如何破坏单例模式？克隆方式破坏单例模式
            HangrySingleton hangrySingleton = HangrySingleton.getInstance();//取得单例对象
            //反射打开方法的权限
            Method method = hangrySingleton.getClass().getDeclaredMethod("clone");
            method.setAccessible(true);
            //克隆方法是protected权限，只有本类或者派生的类中使用，所以需要打开权限
            HangrySingleton hangrySingleton2 = (HangrySingleton) method.invoke(hangrySingleton);
            System.out.println(hangrySingleton);
            System.out.println(hangrySingleton2);

    }
}