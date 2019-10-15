package com.exmyth.hello.design.principle.demeter;

public class TestDemeter {
    public static void main(String [] args){
        //testNegated
        //BossNegated bossNegated = new BossNegated();
        //bossNegated.commandTecherCountCours();

        //testPositive
        BossPositive bossPositive = new BossPositive();
        bossPositive.commandTecherCountCours();
    }
}