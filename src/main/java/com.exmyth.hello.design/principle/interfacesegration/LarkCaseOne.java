package com.exmyth.hello.design.principle.interfacesegration;

public class LarkCaseOne implements IAnimalAction{
    @Override
    public void eat() {

    }

    @Override
    public void fly() {

    }

    /**
     * 很明显，百灵鸟不会游泳，此处为空方法，设计不合理
     */
    @Override
    public void swim() {

    }
}