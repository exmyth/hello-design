package com.exmyth.hello.design.pattern.creational.prototype.v3;

/**
 * 继承A类，直接调用clone接口
 */
public class B extends A{

    public static void main(String [] args){
        B b = new B();
        try {
            b.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            System.out.println("处理异常");
        }
    }
}