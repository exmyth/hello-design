package com.exmyth.hello.design.pattern.adapter.demo;

public class Test {
    public static void main(String [] args){
        DC5V dc5V = new PowerAdapter();
        dc5V.outPutDC5V();
    }
}