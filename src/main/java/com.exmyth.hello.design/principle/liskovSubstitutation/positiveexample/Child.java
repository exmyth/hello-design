package com.exmyth.hello.design.principle.liskovSubstitutation.positiveexample;

import java.util.HashMap;

public class Child extends  Base{
//    @Override
//    public void method(Map map) {
//        super.method(map);
//    }

    public void method(HashMap hashMap) {
        System.out.println("执行子类HashMap方法");
    }

//    @Override
//    public Object method2() {
//        return null;
//    }


    @Override
    public HashMap method2() {
        return null;
    }
}