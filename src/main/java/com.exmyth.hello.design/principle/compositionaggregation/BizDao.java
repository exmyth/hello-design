package com.exmyth.hello.design.principle.compositionaggregation;

public class BizDao {
    private DBConnection dbConnection;

    public void setDbConnection(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void handleBiz(){
       dbConnection.getConnection();
    }
}