package com.exmyth.hello.design.pattern.behavioral.mediator;

public class User {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User (String name) {
        this.name = name;
    }

    public void sendMessage (String message) {
        StudyGroup.showMessage(this, message);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}