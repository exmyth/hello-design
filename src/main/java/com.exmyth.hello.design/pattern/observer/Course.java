package com.exmyth.hello.design.pattern.observer;

import java.util.Observable;

/**
 * @author maming.zhong
 * @date 2019-10-30 17:25
 * @description
 */
public class Course extends Observable {
    private final String name;

    public Course(String name) {
        this.name = name;
    }

    public void submit(Question question) {
        System.out.println(question.getUserName() + ">" + getName() + ">" + question.getContent());
        setChanged();
        notifyObservers(question);
    }

    public String getName() {
        return name;
    }
}
