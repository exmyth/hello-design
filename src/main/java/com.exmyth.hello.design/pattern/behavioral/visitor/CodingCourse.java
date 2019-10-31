package com.exmyth.hello.design.pattern.behavioral.visitor;

public class CodingCourse extends Course {

    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}