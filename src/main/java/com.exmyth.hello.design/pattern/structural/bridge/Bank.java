package com.exmyth.hello.design.pattern.structural.bridge;

/**
 * 该类要写成一个抽象类，因为可能有农业银行，工商银行等银行继承该类
 */
public abstract class Bank {
    /**
     * 声明子类，因为银行查看账号 需要引用到账号类
     */
    protected Account account;

    /**
     * 通过构造方法注入银行账号
     */
    public Bank(Account account) {
        this.account = account;
    }

    abstract Account openAccount();
}