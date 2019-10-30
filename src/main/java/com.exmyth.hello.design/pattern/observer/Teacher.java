package com.exmyth.hello.design.pattern.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author maming.zhong
 * @date 2019-10-30 17:31
 * @description
 */
public class Teacher implements Observer {
    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        Course course = (Course)o;
        Question question = (Question)arg;
        System.out.println(name + "接收到"+course.getName()+"消息" + question);
    }
}
