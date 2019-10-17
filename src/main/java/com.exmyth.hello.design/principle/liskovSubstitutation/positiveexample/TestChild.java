package com.exmyth.hello.design.principle.liskovSubstitutation.positiveexample;

import java.util.HashMap;

public class TestChild {
    public static void main(String [] args){
        Child child = new Child();
        HashMap hashMap = new HashMap();
        child.method(hashMap);
    }
}