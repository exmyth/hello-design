package com.exmyth.hello.design.principle.liskovSubstitutation.positiveexample;

import java.util.HashMap;
import java.util.Map;

public abstract class Base {
    public void method(Map map){
        System.out.println("执行父类HashMap方法");
    }

    public abstract Map method2();
}