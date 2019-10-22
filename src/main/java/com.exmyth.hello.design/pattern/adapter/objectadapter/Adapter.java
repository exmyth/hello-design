package com.exmyth.hello.design.pattern.adapter.objectadapter;

public class Adapter implements Target {
    Adaptee adaptee = new Adaptee();

    @Override
    public void request() {
        adaptee.adapteeRequest();
    }
}