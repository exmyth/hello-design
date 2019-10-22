package com.exmyth.hello.design.pattern.structural.decorator.v1;

public class Test {
    public static void main(String [] args){
        BatterCake batterCake = new BatterCake();
        System.out.println(batterCake.getDescribe()+"销售价格为 "+batterCake.cost());
        BatterCake batterCakeWithEgg = new BatterCakeWithEgg();
        System.out.println(batterCakeWithEgg.getDescribe()+"销售价格为 "+batterCakeWithEgg.cost());
        BatterCake  batterCakeWithEggSausage = new BatterCakeWithEggSausage();
        System.out.println(batterCakeWithEggSausage.getDescribe()+"销售价格为 "+batterCakeWithEggSausage.cost());
    }
}