package com.exmyth.hello.design.pattern.structural.bridge.v2;

import com.exmyth.hello.design.pattern.structural.bridge.Account;
import com.exmyth.hello.design.pattern.structural.bridge.DepositAccount;
import com.exmyth.hello.design.pattern.structural.bridge.SavingAccount;

public class Test {
    public static void main(String [] args){
        Bank bank1 = new ICBCBank(new DepositAccount());
        Account account1 = bank1.openAccount();
        account1.showAccount();

        Bank bank2 = new ICBCBank(new SavingAccount());
        Account account2 = bank2.openAccount();
        account2.showAccount();

        Bank bank3 = new ABCBank(new DepositAccount());
        Account account3 = bank3.openAccount();
        account3.showAccount();
    }
}