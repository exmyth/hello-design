package com.exmyth.hello.design.principle.compositionaggregation;

public class OracleConnection extends DBConnection{
    @Override
    public void getConnection() {
        System.out.println("打开oracle连接");
    }
}