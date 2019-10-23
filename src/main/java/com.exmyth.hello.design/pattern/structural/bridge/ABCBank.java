package com.exmyth.hello.design.pattern.structural.bridge;

/**
 * 创建中国农业银行
 */
public class ABCBank extends Bank{

    public ABCBank(Account account) {
        super(account);
    }

    @Override
    Account openAccount() {
        System.out.println("打开中国农业银行账号");
        return account;
    }
}