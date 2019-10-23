package com.exmyth.hello.design.pattern.structural.bridge;

public class SavingAccount implements Account{

    @Override
    public Account openAccount() {
        System.out.println("打开活期账号");
        return new SavingAccount();
    }

    @Override
    public void showAccount() {
        System.out.println("这是活期账号");
    }
}