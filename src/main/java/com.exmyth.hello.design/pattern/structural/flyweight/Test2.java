package com.exmyth.hello.design.pattern.structural.flyweight;

public class Test2 {
    public static void main(String [] args){
        Integer a = 100;
        Integer b = Integer.valueOf(100);
        System.out.println("a==b"+ (a==b));

        Integer c = 1000;
        Integer d = Integer.valueOf(1000);
        System.out.println("c==d"+ (c==d));
    }
}