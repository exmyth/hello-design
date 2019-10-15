package com.exmyth.hello.design.principle.singleresponsibility;

public class Test {
    public static void main(String[] args) {
        WalkBird walkBird = new WalkBird();
        walkBird.birdMove("鸵鸟");

        FlyBird flyBird = new FlyBird();
        flyBird.birdMove("大雁");
    }
}