package com.exmyth.hello.design.pattern.creational.singleton.v10;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Test {

    public static void main(String [] args){
        Thread thread1 = new Thread(new T());
        Thread thread2 = new Thread(new T());
        thread1.start();
        thread2.start();
        System.out.println("结束了！！！");
    }

}