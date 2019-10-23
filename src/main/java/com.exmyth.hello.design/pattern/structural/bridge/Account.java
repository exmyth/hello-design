package com.exmyth.hello.design.pattern.structural.bridge;

/**
 * 银行账号接口，有存款账号和活期账号 两种账号
 */
public interface Account {
    //获得账号
    Account openAccount();
    //查看账号类型，是活期账号还是 定期账号
    void showAccount();
}