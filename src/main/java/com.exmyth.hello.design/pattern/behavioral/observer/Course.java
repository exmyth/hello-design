package com.exmyth.hello.design.pattern.behavioral.observer;

import java.util.Observable;

/**
 * @author exmyth
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
