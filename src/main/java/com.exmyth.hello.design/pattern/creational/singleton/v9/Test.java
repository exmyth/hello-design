package com.exmyth.hello.design.pattern.creational.singleton.v9;

public class Test {


    public static void main(String [] args){
        ContainerSingleton.putInstance("key",new Object());
        Object containerSingleton = ContainerSingleton.getInstance("key");
        System.out.println(containerSingleton.getClass().getName());
    }
}