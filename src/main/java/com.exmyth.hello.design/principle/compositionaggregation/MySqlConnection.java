package com.exmyth.hello.design.principle.compositionaggregation;

public class MySqlConnection extends DBConnection{
    @Override
    public void getConnection() {
        System.out.println("打开MySql连接");
    }
}