package com.exmyth.hello.design.principle.interfacesegration;

public class DogCaseOne implements IAnimalAction{

    @Override
    public void eat() {

    }

    /**
     * 注：这里是空方法，狗不会飞，所以明显设计的不合理，最好不要有太多的空方法
     */
    @Override
    public void fly() {

    }

    @Override
    public void swim() {

    }
}