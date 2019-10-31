package com.exmyth.hello.design.pattern.behavioral.chainofresponsibility;

/**
 * @author maming.zhong
 * @date 2019-10-31 20:52
 * @description
 */
public abstract class Approver {
    protected Approver approver;
    public void nextApprover(Approver approver){
        this.approver = approver;
    }

    abstract void deploy(Course course);
}
