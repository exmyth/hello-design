package com.exmyth.hello.design.pattern.behavioral.observer.v2;

import com.google.common.eventbus.EventBus;

/**
 * @author maming.zhong
 * @date 2019-10-30 18:00
 * @description
 */
public class Test {
    public static void main(String[] args) {
        EventBus bus = new EventBus();
        bus.register(new Teacher());
        bus.post("message");
    }
}
