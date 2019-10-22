package com.exmyth.hello.design.pattern.structural.decorator.v1;

/**
 * 创建煎饼类
 */
public class BatterCake {

    /**
     * 获取加的种类
     * @return
     */
    protected String getDescribe(){
        return "煎饼";
    }

    /**
     * 计算所需要的钱
     * @return
     */
    protected int cost(){
        return 8;
    }

}