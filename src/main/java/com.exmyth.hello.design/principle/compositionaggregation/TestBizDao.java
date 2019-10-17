package com.exmyth.hello.design.principle.compositionaggregation;

public class TestBizDao {
//    public static void main(String [] args){
//        BizDaoNegative bizDaoNegative = new BizDaoNegative();
//        bizDaoNegative.getDBConnection();
//    }

    public static void main(String [] args){
        BizDao bizDao = new BizDao();
        DBConnection dbConnection = new MySqlConnection();
//        bizDao.setDbConnection(dbConnection);
//        DBConnection dbConnection = new OracleConnection();
        bizDao.setDbConnection(dbConnection);
        bizDao.handleBiz();
    }
}