package com.exmyth.hello.design.pattern.structural.decorator.v2;

public class Test {
    public static void main(String [] args){
        ABatterCake aBatterCake;
        //给煎饼赋值
        aBatterCake = new BatterCake();
        aBatterCake = new EggDecorator(aBatterCake);
        aBatterCake = new EggDecorator(aBatterCake);
        aBatterCake = new SauseDecorator(aBatterCake);

        System.out.println(aBatterCake.getDescribe()+" 一共卖了"+aBatterCake.cost());
    }
}