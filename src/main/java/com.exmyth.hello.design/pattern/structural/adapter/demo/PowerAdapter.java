package com.exmyth.hello.design.pattern.structural.adapter.demo;

/**
 * 电源适配器
 */
public class PowerAdapter implements DC5V {

    //通过组合引入交流电220V
    AC220V ac220V = new AC220V();

    @Override
    public int outPutDC5V() {
        int adapterInput = ac220V.outputAC220V();

        //变压器转换
        int adapterOutput = adapterInput/44;

        System.out.println("使用电源适配器，输入"+adapterInput+"最终转换为直流电"+adapterOutput+"V");
        return adapterOutput;
    }
}