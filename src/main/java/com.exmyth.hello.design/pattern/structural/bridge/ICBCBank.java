package com.exmyth.hello.design.pattern.structural.bridge;

public class ICBCBank extends Bank{
    public ICBCBank(Account account) {
        super(account);
    }

    @Override
    Account openAccount() {
        System.out.println("打开中国工商银行");
        return account;
    }
}