package com.exmyth.hello.design.pattern.adapter.demo;

public class AC220V {
    public int outputAC220V(){
        int outputELE = 220;
        System.out.println("输出交流电"+outputELE+"V");
        return outputELE;
    }
}