package com.exmyth.hello.design.pattern.behavioral.visitor;

public class FreeCourse extends Course {
    @Override
    void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}