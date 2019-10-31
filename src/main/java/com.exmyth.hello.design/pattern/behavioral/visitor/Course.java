package com.exmyth.hello.design.pattern.behavioral.visitor;

public abstract class Course {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    abstract void accept(IVisitor visitor);
}