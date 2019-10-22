package com.exmyth.hello.design.pattern.creational.prototype.v5;

import java.util.Date;

public class Test2 {
    public static void main(String [] args){
        try {
            Date date1 = new Date();
            Pig pig1 = new Pig("peiqi",date1);
            Pig pig2 = (Pig) pig1.clone();
            System.out.println(pig1);
            System.out.println(pig2);


            //预期修改pig1的生日，没想到pig2的生日也改了
            pig1.getBirthday().setTime(0L);
            System.out.println(pig1);
            System.out.println(pig2);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            System.out.println("出现异常");
        }
    }
}