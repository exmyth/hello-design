package com.exmyth.hello.design.pattern.behavioral.observer.v2;

import com.google.common.eventbus.Subscribe;

/**
 * @author maming.zhong
 * @date 2019-10-30 17:59
 * @description
 */
public class Teacher {
    @Subscribe
    public void subscribe(String str){
        System.out.println(str);
    }
}
